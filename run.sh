#!/bin/sh

#BUILD_ID=DONTKILLME #后台执行

#查询jar包pid
pid=$(ps -ef|grep jkstest-0.0.1-SNAPSHOT.jar|grep -v grep | awk '{print $2}')

#copy jar 到启动目录
cp -r /var/lib/jenkins/workspace/d/target/jkstest-0.0.1-SNAPSHOT.jar  /home/soul/lgy/jksjar

# 关闭已经启动的jar进程 shell中利用 -n 来判定字符串非空
function stop(){
if [ -n "$pid" ]
  then
  	echo "pid进程 :$pid"
  	kill -9 $pid
 else
    echo "进程没有启动"
fi
}
stop

sleep 5s

#发布jar服务
function start(){
  cd /home/soul/lgy/jksjar
  nohup java -jar jkstest-0.0.1-SNAPSHOT.jar &
}

start
