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
    <version>2.0.6-RELEASE</version>
</dependencies>
```


## 2. 功能说明
+ 二进制数组组件
    + byte[] 和 object之间转换
    + byte[] 和 string(hexString)之间的转换
+ 公共组件
    + 获取栈信息
+ 数据压缩组件
    + gzip 方式
    + deflate方式
    + snappy方式 
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
    + Http Delete操作
    + Http Put 操作
+ 加密组件
    + md5加密、文件加密
    + aes加密/解密
    + base64加密/解密
    + des加密/解密
    + sha1
    + sha256
+ 日期时间组件
    + LocalDateTime的操作集
    + Instant的操作集
+ 基本类型转化组件
    + string 与 int 互转
    + string 与 long 互转
    + string 与 boolean 互转
+ 配置组件
    + 获取配置信息
+ 重复尝试组件
+ 邮件Email组件
    + 发送电子邮件（附件）
+ 文件组件
    + 读
    + 写
    + 文件增/删除
+ Json序列化组件
    + json序列化
+ 随机字符串组件
    + 随机数生成
    + 随机字符串
    + 随机数字字符串
    + 随机指定字符串
+ 反射组件
    + 获取类属性
    + 获取类方法
    + 设置属性
    + 方法调用
+ 流组件
    + stream转换
+ 同城游用户校验组件
    + 校验是否用户登录
+ Zip组件
    + zip解压/压缩
    
## 3. 相关配置说明
+   邮件配置
    + 配置文件名
        email.properties(存放路径自定义)
    + 内容
          
            
          mail.type=smtp  简单邮件传输协议
          mail.smtp.auth=true   是否授权
          mail.smtp.host=smtp.qq.com 服务器地址
          mail.smtp.port=465   端口
          mail.sender.username=shushoufu@qq.com 发送用户名
          mail.sender.password=nbnostkhpvtebjbi 密码/授权码
          mail.debug=true 是否debug
    
+   同城游用户管理组件配置
    + 配置文件路径
        {user.dir}/config/tcManager.properties
    + 配置文件内容
        
            tcManager.uri=http://bizsyssvc.ct108.org:2047/TcManageCenter.asmx