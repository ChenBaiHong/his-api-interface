#!/usr/bin/env bash

declare version=1.0
declare appName=api-intranet

function mvnBuild(){

    result=0

    #设置mvn打包的jvm大小
    export MAVEN_OPTS=-Xmx256m

    echo =================================
    echo  自动化部署脚本启动
    echo =================================

    echo 开始git pull
    git pull

    echo 开始删除jar
    rm -rf ~/"${appName}"/"${appName}"-"$version".jar
    echo 开始build文件
    mvn clean package -Dmaven.test.skip=true > ~/mvn.log

    echo 拷贝编译完成文件 ~"${appName}"
    cp ~/"${appName}"/target/"${appName}"-"$version".jar ~/"${appName}"

    echo "mvn package"
    #判断是否打包成功，成功才继续
    cat ~/mvn.log | grep 'BUILD SUCCESS' > /dev/null
    if [ $? -eq 0 ];then
        echo 'mvn Build Success! Then Go To Copy!'
        result=1
    else
        echo 'mvn Build Fail! Ask Your JavaGuys To Check it out!'
        result=0
    fi

    echo 删除编译的文件夹
    rm -rf ~/"${appName}"/target

    return $result
}

function toStart(){
  profile=$1
  kill -9 $(netstat -nlp | grep :8089 | awk '{print $7}' | awk -F"/" '{ print $1 }')
  nohup java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms2g -Xmx2g -Xmn256m -Xss256k -XX:SurvivorRatio=8 "${appName}"-${version}.jar --spring.profiles.active="$profile" >/dev/null  &
}

    profile=$1
    needBuild=$2
    build=1

    echo "needBuild is $needBuild"

    if [ $needBuild = "1" ]
    then
      mvnBuild
      build=$?
    fi

    if [ $build -eq 0 ]
       then
         echo "打包失败，发布中断"
    else
        if [ $needBuild = "1" ]
        then
          echo "打包完成"
        else
          echo "不打包，直接发布"
        fi
        toStart $1
    fi

