# NexusPHP论坛助手

## 介绍

NexusPHP论坛助手是一个管理多个NexusPHP站点论坛的工具，使用这个工具可以方便查看多个站点论坛中的新帖。

## 安装方法

### 使用Docker安装

1. 安装[docker](https://docker.io)

2. 拉取镜像，创建容器，运行

    输入以下命令

        docker run -d -p 8080:8080 --name nfh jiwenzhuo8718/nexusphp_forum_helper:v1

3. 访问[http://localhost:8080/nfh/index.html](http://localhost:8080/nfh/index.html)

### 使用tomcat部署

1. 安装Java16 + tomcat 9

2. 下载[release](https://github.com/Ji-Wen-ZHuo/nexusphp_forum_helper/releases)中的war包，复制到tomcat的webapp目录下

3. 启动tomcat并访问[http://localhost:8080/nfh/index.html](http://localhost:8080/nfh/index.html)

## 使用说明

在站点设置栏目中添加站点

|站点属性|说明|
|--|--|
|站点名称|别名，不影响使用，唯一值|
|站点地址|填论坛地址，例如[https://xxxx.com/forums.php](https://xxxx.com/forums.php)|
|Cookie|以谷歌浏览器为例，打开站点论坛，F12-网络-[Ctrl+R]，在录制的请求中找到forum.php，可以在请求标头中找到cookie，cookie可以用来登陆站点，请勿将cookie泄露给任何人|

## 技术栈

|类型|技术栈|
|--|--|
|后端|[Spring MVC](https://spring.io)，[MyBatis](https://blog.mybatis.org)|
|前端|[layui](https://github.com/sentsin/layui)，[jquery](https://jquery.com)|
|部署|[docker](https://docker.io)|

## 更新说明

### v1.1

使用mysql作为持久化层替换原来文件作为持久化层的方案
