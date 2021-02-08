# EasyMessage

## 简介
用于集成一些常见的发送信息方式：
- [x] 钉钉机器人
    - [x] 支持文本、markdown、link、actionCard等全部格式的消息发送
    - [x] 支持配置文件或动态传入钉钉机器人accessToken和签名
- [x] 邮件
    - [x] 支持纯文本邮件发送
      - [x] 支持无附件纯文本邮件
    - [x] 支持html格式邮件发送
      - [x] 支持无附件富文本邮件
- [ ] 企业微信机器人
- [ ] 飞书机器人

## 定位
本工具定位为常见消息推送服务的调用实现，每种消息推送的限流/内容聚合处理需要使用方自行处理。

## 使用方式
### 1. 引入依赖
当前 `Easy-Message` 使用Java语言编写，主要提供SpringBoot-starter方式进行接入。在使用 `Easy-Message` 之前需要引入对应的工具依赖，这里只展示maven依赖的引入，其他依赖方式可以参考：https://search.maven.org/artifact/com.brucebat/spring-boot-starter-message/1.2.0-RELEASE/jar。
```xml
<dependency>
  <groupId>com.brucebat</groupId>
  <artifactId>spring-boot-starter-message</artifactId>
  <version>1.2.0-RELEASE</version>
</dependency>
```

### 2. 推送API
#### a.钉钉机器人消息推送
`Easy-Message` 支持了钉钉机器人消息中所有的消息类型推送，但是未提供限流措施，这里需要使用者自行控制消息发送的频率。具体推送示例如下：
```java
@SpringBootTest
public class Test {
    
    @Resource
    private DingTalkService dingTalkService;

    @Test
    public void testDingTalk() {
        try {
            Target target = new Target();
            // 设置消息接收人电话
            target.setAtMobiles(Collections.singletonList("xxx"));
            // 这里推送Markdown消息
            MarkdownMessage message = MarkdownMessage.build("测试使用", "> This is a test.", target);
            boolean success = dingTalkService.send(message, "accessToken", true, "encryptKey");
            System.out.println(success);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```   

#### b.邮件消息推送
`Easy-Message` 支持了纯文本消息和富文本消息的邮件推送，目前暂不支持含附件的邮件推送。具体推送示例如下：

```java
@SpringBootTest
public class Test {
    
   @Resource
   private MailService mailService;
   @Test
   public void testMail() {
      try {
         MailMessage message = new MailMessage();
         message.setToAddress(Collections.singletonList("xxxx@gmail.com"));
         message.setMailType(MailTypeEnum.HTML.getType());
         message.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                 "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                 "    <head>\n" +
                 "        <meta http-equiv=\"Content-Type\" charset=UTF-8\" />\n" +
                 "        <title>Test Mail</title>\n" +
                 "    </head>\n" +
                 "    <body>\n" +
                 "    <!-- 最外层table-->\n" +
                 "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" style=\"\">\n" +
                 "        <tr>\n" +
                 "            <td align=\"center\" valign=\"top\">\n" +
                 "                <!-- 定宽table-->\n" +
                 "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"\" style=\"\">\n" +
                 "                    <tr>\n" +
                 "                        <td align=\"center\" valign=\"\">this is a test mail</td>\n" +
                 "                    </tr>\n" +
                 "                </table>\n" +
                 "            </td>\n" +
                 "        </tr>\n" +
                 "    </table>\n" +
                 "    </body>\n" +
                 "</html>");
         message.setTitle("测试邮件");
         mailService.sendMail(message, "xxxx@gmail.com", "password");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}

```

## 致谢

非常感谢JetBrain的支持，提供了免费使用开发工具的机会

![JetBrains](images/jetbrains-variant-2.png)

[来自EasyMessage](https://www.jetbrains.com/?from=EasyMessage)