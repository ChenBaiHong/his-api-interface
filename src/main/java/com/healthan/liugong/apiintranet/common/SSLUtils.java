package com.healthan.liugong.apiintranet.common;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SSLUtils {

	private static Logger logger= LoggerFactory.getLogger(SSLUtils.class);
	
	private static final String KEYSTORE_TYPE ="PKCS12";

//	private static final String KEYSTORE_PATH = "D:\\jobs\\lzsrkjkxxpt_client.p12";

	private static final String KEYSTORE_PASSWORD = "e3b3Y6YD7YjqAAaP";

	/**
	 * 设置信任自签名证书
	 * @return
	 */
	public static SSLContext custom(){
//		String keyStorePath =KEYSTORE_PATH;
		String keyStorepass=KEYSTORE_PASSWORD;
		SSLContext sc = null;
		FileInputStream instream = null;
		KeyStore trustStore = null;
		try {
			trustStore = KeyStore.getInstance(KEYSTORE_TYPE);
			//直接读取项目下的目录
			instream = new FileInputStream(ResourceUtils.getFile("classpath:lzsrkjkxxpt_client.p12"));
			trustStore.load(instream, keyStorepass.toCharArray());
			// 相信自己的CA和所有自签名的证书
			sc = SSLContexts.custom().loadTrustMaterial(new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
					return true;
				}
			}).loadKeyMaterial(trustStore, KEYSTORE_PASSWORD.toCharArray()).build();
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | KeyManagementException |UnrecoverableKeyException e) {
			logger.error(e.getMessage());
		}finally {
			try {
				instream.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return sc;
	}

	public static CloseableHttpClient getCloseableHttpClient(){
		SSLContext sslcontext = custom();
		// 设置协议http和https对应的处理socket链接工厂的对象
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslcontext))
				.build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		HttpClients.custom().setConnectionManager(connManager);

		//创建自定义的httpclient对象
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager).build();
		return httpclient;
	}
}
