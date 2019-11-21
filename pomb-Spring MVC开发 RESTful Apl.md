
### 介绍
1. 用URL描述资源
2. 使用HTTP方法描述行为。使用HTTP状态码来表示不同的结果
3. 使用json交互数据
4. RESTful只是一种风格，并不是强制的标准


### 编写第一个Restful api

1. 编写一个针对RestfulAPI的测试用例
引入jar
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
```






2. 使用注解声明RestfulAPI

3. 在RestfulAPI中传递参数





### 异步处理REST服务

#### 为啥要异步处理
1. 同步处理
HTTP请求 Tomcat 主线程处理 跟HTTP回响应

但是当请求过多 Tomcat 的主线程就处理不过来的


2. 异步处理

HTTP请求 主线程调用副线程 副线程处理完 在返回主线程 

服务器吞吐量会有明显的提升








