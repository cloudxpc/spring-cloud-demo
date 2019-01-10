[< index](https://cloudxpc.github.io/spring-cloud-demo)

# Spring Cloud Consul

Consul是来自HashiCorp公司的产品，在微服务架构中扮演服务注册中心，与之类似的还有Eureka, ZooKeeper等。

Consul运行模式有两种，一种是Server，一种是Client。这两种模式应该都是属于服务注册中心一端，不要被Client字面混淆。
Server模式主要扮演服务中心，Client模式多偏向集群节点。这点不是很确定，需进一步研究。

Spring Cloud添加了对Consul的支持。Maven依赖：
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```
如果没有引入web依赖，没有controller对外提供api，服务启动后不会进行自动注册。
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
Consul会对/health进行健康检查，如果没有引actuator包，会被标记为critical。
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
之后启动就会自动注册到Consul中心了。服务名称依然是以`spring.application.name`为主。
```yaml
spring:
  application:
    name: consul-client
```
如果需要指定Consul的地址：
```yaml
spring:
  cloud:
    consul:
      host: localhost
      port: 8500
```
服务注册时，`ConsulAutoRegistration`类的`normalizeForDns`方法会对`instanceId`做标准化处理，因此名称会被替换为`xxx-xxx-xxx`的样式，应该是域名解析相关的规则。

如果类中绑定了`ConsulDiscoveryProperties`对象会导致注册到Consul时的Address是null，这里非常奇怪。