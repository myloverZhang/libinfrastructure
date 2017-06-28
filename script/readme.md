### 1. Maven接入
#### 1.1. 仓库源
修改setting.xml
```
<mirrors>
    <mirror>
        <id>ctdcmaven</id>
        <name>ctdc maven</name>
        <url>http://192.168.1.3:8081/nexus/content/repositories/maven-big-data-public/</url>
        <mirrorOf>*</mirrorOf>
    </mirror>
</mirrors>
```

#### 1.2. POM.xml
```
<dependencies>
    <groupId>ct.dc.infrastructure</groupId>
    <artifactId>libinfrastructure</artifactId>
    <version>1.2.3-RELEASE</version>
</dependencies>
```


## 2. 功能说明
+ IP组件
    + 获取IPv4
    + ip与long之间互转
    + ip与int之间互转
+ 字符串组件
    + 判断是否为空
    + 判断是否为数字
    + 判断是否为电子邮件
    + 判断是否为身份证ID
+ Http通讯组件
    + Http GET操作
    + Http Post操作
+ 加密组件
    + md5加密
    + aes加密/解密
    + base64加密/解密
+ 日期时间组件
    + LocalDateTime的操作集
    + Instant的操作集
+ 类型转化组件
    + string 与 int 互转
    + string 与 long 互转
    + string 与 boolean 互转
    + inputstream 转化成 stringbuffer
    + inputseream 与 bytes 互转
    + string 与 bytes 互转
    + json序列化
    + json反序列化
    + 16进制 与 2进制 换转
+ 压缩组件
    + gzip压缩
    + gzip解压
+ 其他通用组件
    + 获取错误堆栈
    + 根据ClassName获取实例对象
+ 配置组件
    + 获取配置信息
+ 重复尝试组件
