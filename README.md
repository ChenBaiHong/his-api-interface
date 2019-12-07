部署项目：
1、进入当前用户名目录下
cd ~
2、从git拉取代码
git clone  https://api-intranet
3、进入拉取代码
cd ~/api-intranet
4、发布服务
sh build.sh 环境变量(dev,test,pro) 打包与否(1：打包，2：不打包)

sh build.sh dev 1