两个app间的aidl进程间通信，来自
https://blog.csdn.net/c10WTiybQ1Ye3/article/details/78098706

创建.aidl文件
在service中实现aidl定义的接口
将接口暴露给客户端

demo 1：需要运行两个app：aidlcilent 和 app
app 模块作为服务端
供
aidlclient 模块客户端调用

demo 2 ：只运行aidlclient
aidlclient和MyService新开辟的进程com.bagguo.aidlclient:remote通信