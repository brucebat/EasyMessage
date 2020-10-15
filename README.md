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

## 使用方式

```xml
<dependency>
  <groupId>com.brucebat</groupId>
  <artifactId>spring-boot-starter-message</artifactId>
  <version>1.0.0-RELEASE</version>
</dependency>
```