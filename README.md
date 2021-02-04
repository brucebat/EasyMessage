# EasyMessage

## 简介
用于集成一些常见的发送信息方式：
- [x] 钉钉机器人
    - [x] 消息发送限流（基于Guava实现）
    - [x] 支持文本、markdown、link、actionCard等全部格式的消息发送
- [ ] 邮件
    - [ ] 支持纯文本邮件发送
    - [ ] 支持html格式邮件发送
- [ ] 企业微信机器人
- [ ] 飞书机器人

## 定位
本工具定位为常见消息推送服务的调用实现，每种消息推送的限流/内容聚合处理需要使用方自行处理。

## 使用方式

```xml
<dependency>
  <groupId>com.brucebat</groupId>
  <artifactId>spring-boot-starter-message</artifactId>
  <version>1.2.0-RELEASE</version>
</dependency>
```

## 致谢

非常感谢JetBrain的支持，提供了免费使用开发工具的机会

![JetBrains](images/jetbrains-variant-2.png)

[来自EasyMessage](https://www.jetbrains.com/?from=EasyMessage)