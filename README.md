# Tools

## 项目介绍

这是一个工具类项目，包含多种实用工具，如加密解密工具、日期工具、HTTP请求工具、字符串工具等。该项目旨在为开发者提供一系列常用的工具类，以简化开发过程。

## 目录结构

*   `.gitignore`
*   `LICENSE`
*   `README.md`
*   `pom.xml`
*   `src/main/java/com/githup/liming495/App.java`
*   `src/main/java/com/githup/liming495/constants/C.java`
*   `src/main/java/com/githup/liming495/exceptions/ControllerException.java`
*   `src/main/java/com/githup/liming495/exceptions/DAOException.java`
*   `src/main/java/com/githup/liming495/exceptions/FacadeException.java`
*   `src/main/java/com/githup/liming495/exceptions/RuntimeIOException.java`
*   `src/main/java/com/githup/liming495/exceptions/ServiceException.java`
*   `src/main/java/com/githup/liming495/exceptions/SystemConfigurationException.java`
*   `src/main/java/com/githup/liming495/secret/Cipher.java`
*   `src/main/java/com/githup/liming495/secret/SM2.java`
*   `src/main/java/com/githup/liming495/secret/SM2Utils.java`
*   `src/main/java/com/githup/liming495/secret/SM3.java`
*   `src/main/java/com/githup/liming495/secret/SM3Digest.java`
*   `src/main/java/com/githup/liming495/secret/SM4.java`
*   `src/main/java/com/githup/liming495/secret/SM4Context.java`
*   `src/main/java/com/githup/liming495/secret/SM4Utils.java`
*   `src/main/java/com/githup/liming495/secret/Util.java`
*   `src/main/java/com/githup/liming495/utils/Base64Utils.java`
*   `src/main/java/com/githup/liming495/utils/ClassUtils.java`
*   `src/main/java/com/githup/liming495/utils/CodeUtils.java`
*   `src/main/java/com/githup/liming495/utils/CollectionUtils.java`
*   `src/main/java/com/githup/liming495/utils/CookieUtils.java`
*   `src/main/java/com/githup/liming495/utils/DateFormat.java`
*   `src/main/java/com/githup/liming495/utils/DateStyle.java`
*   `src/main/java/com/githup/liming495/utils/DateUtils.java`
*   `src/main/java/com/githup/liming495/utils/EmojiUtils.java`
*   `src/main/java/com/githup/liming495/utils/Equation.java`
*   `src/main/java/com/githup/liming495/utils/HttpRequestUtil.java`
*   `src/main/java/com/githup/liming495/utils/HttpsIgnore.java`
*   `src/main/java/com/githup/liming495/utils/IPUtils.java`
*   `src/main/java/com/githup/liming495/utils/JSONUtils.java`
*   `src/main/java/com/githup/liming495/utils/Merger.java`
*   `src/main/java/com/githup/liming495/utils/ObjectUtils.java`
*   `src/main/java/com/githup/liming495/utils/RequestUtils.java`
*   `src/main/java/com/githup/liming495/utils/SecurityUtils.java`
*   `src/main/java/com/githup/liming495/utils/SnowflakeIdWorker.java`
*   `src/main/java/com/githup/liming495/utils/StringUtils.java`
*   `src/test/java/com/githup/liming495/AppTest.java`

## 安装与配置

请确保已经安装了Java环境，并且配置好了Maven。然后通过以下命令进行安装：

```bash
mvn clean install
```

## 使用示例

### 加密解密工具

```java
import com.githup.liming495.secret.SM2Utils;

public class Main {
    public static void main(String[] args) throws Exception {
        // 生成密钥对
        SM2Utils.generateKeyPair();

        // 加密
        String publicKey = "publicKey";
        String data = "data";
        String encryptedData = SM2Utils.encrypt(publicKey.getBytes(), data.getBytes());

        // 解密
        String privateKey = "privateKey";
        byte[] decryptedData = SM2Utils.decrypt(privateKey.getBytes(), encryptedData.getBytes());
    }
}
```

### 日期工具

```java
import com.githup.liming495.utils.DateUtils;

public class Main {
    public static void main(String[] args) throws Exception {
        // 获取当前日期
        String currentDate = DateUtils.nowToString();

        // 格式化日期
        String formattedDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }
}
```

### HTTP请求工具

```java
import com.githup.liming495.utils.HttpRequestUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        // 发送GET请求
        String url = "http://example.com";
        String response = HttpRequestUtil.doGet(url, null, null);
    }
}
```

### 字符串工具

```java
import com.githup.liming495.utils.StringUtils;

public class Main {
    public static void main(String[] args) throws Exception {
        // 判断字符串是否为空
        boolean isEmpty = StringUtils.isEmpty("string");

        // 字符串拼接
        String result = StringUtils.join("Hello", " ", "World");
    }
}
```

## 贡献指南

欢迎提交 Pull Request 或报告 Issues。请确保代码风格与项目保持一致，并提供详细的提交信息。

## 许可证

该项目遵循 MIT 许可证。详情请查看 [LICENSE](LICENSE) 文件。