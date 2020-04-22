package com.githup.liming495.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

/**
 * HTTP请求工具
 *
 * @author Guppy
 */
public class HttpRequestUtil {
    private static final Logger logger = Logger.getLogger(HttpRequestUtil.class);
    private static String Encoding = StandardCharsets.UTF_8.name();

    /**
     * 原生字符串发送get请求
     * @param url   发送请求的URL
     * @param headerParam   发送请求的Header参数
     * @param param 请求参数
     * @return  响应报文
     */
    public static String doGet(String url, Map<String, String> headerParam, String param) {
        BasicConfigurator.configure();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String urlNameString;
        if(!ObjectUtils.isEmpty(param)) {
            urlNameString = url + "?" + param;
        } else {
            urlNameString = url;
        }
        HttpGet httpGet = new HttpGet(urlNameString);
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectTimeout(35000)
                .setConnectionRequestTimeout(35000)
                .setSocketTimeout(60000).build();
        httpGet.setHeader("Content-type", "application/json");
        httpGet.setHeader("DataEncoding", "UTF-8");
        // 设置通用的请求属性
        if (!ObjectUtils.isEmpty(headerParam)) {
            for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity);
            logger.info("请求成功");
            return result;
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException", e);
            e.printStackTrace();
        } finally {
            closeObject(httpResponse);
            closeObject(httpClient);
        }
        return null;
    }

    /**
     * 原生字符串发送post请求
     * @param url   发送请求的URL
     * @param headerParam   发送请求的Header参数
     * @param param 请求参数
     * @return  响应报文
     */
    public static String doPost(String url, Map<String, String> headerParam, String param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectTimeout(35000)
                .setConnectionRequestTimeout(35000)
                .setSocketTimeout(60000).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("DataEncoding", "UTF-8");
        // 设置通用的请求属性
        if (!ObjectUtils.isEmpty(headerParam)) {
            for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse httpResponse = null;
        try {
            httpPost.setEntity(new StringEntity(param));
            httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException", e);
            e.printStackTrace();
        } finally {
            closeObject(httpResponse);
            closeObject(httpClient);
        }
        return null;
    }

    /**
     * 原生字符串发送put请求
     *
     * @param url   发送请求的URL
     * @param headerParam   发送请求的Header参数
     * @param param 请求参数
     * @return  响应报文
     */
    public static String doPut(String url, Map<String, String> headerParam, String param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(35000).
                setConnectionRequestTimeout(35000).
                setSocketTimeout(60000).build();
        httpPut.setConfig(requestConfig);
        httpPut.setHeader("Content-type", "application/json");
        httpPut.setHeader("DataEncoding", "UTF-8");
        // 设置通用的请求属性
        if (!ObjectUtils.isEmpty(headerParam)) {
            for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse httpResponse = null;
        try {
            httpPut.setEntity(new StringEntity(param));
            httpResponse = httpClient.execute(httpPut);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException", e);
            e.printStackTrace();
        } finally {
            closeObject(httpResponse);
            closeObject(httpClient);
        }
        return null;
    }

    /**
     * 发送delete请求
     *
     * @param url   发送请求的URL
     * @param headerParam   发送请求的Header参数
     * @param param 请求参数
     * @return  响应报文
     */
    public static String doDelete(String url, Map<String, String> headerParam, String param) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectTimeout(35000)
                .setConnectionRequestTimeout(35000)
                .setSocketTimeout(60000).build();
        httpDelete.setConfig(requestConfig);
        httpDelete.setHeader("Content-type", "application/json");
        httpDelete.setHeader("DataEncoding", "UTF-8");
        // 设置通用的请求属性
        if (!ObjectUtils.isEmpty(headerParam)) {
            for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                httpDelete.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpDelete);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            logger.error("ClientProtocolException", e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException", e);
            e.printStackTrace();
        } finally {
            closeObject(httpResponse);
            closeObject(httpClient);
        }
        return null;
    }

    /**
     * 获取HeaderFields
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是的形式。
     * @return HeaderFields
     */
    public static Map<String, List<String>> getHeaderFields(String url, String param){
        PrintWriter out = null;
        Map<String, List<String>> headerFields = null;
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            headerFields = conn.getHeaderFields();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            closeObject(out);
        }
        return headerFields;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param headerParam   发送请求的Header参数
     * @param param 请求参数，请求参数应该是的形式。
     * @param ssl 支持ssl
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String, String> headerParam, String param, boolean ssl) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString;
            if(!ObjectUtils.isEmpty(param)) {
                urlNameString = url + "?" + param;
            } else {
                urlNameString = url;
            }
            // 打开和URL之间的连接
            URLConnection conn = getConn(urlNameString, headerParam, ssl);

            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("GET推送结果：" + result);
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param headerParam   发送请求的Header参数
     * @param param 请求参数，请求参数应该是的形式。
     * @param ssl 支持ssl
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, String> headerParam, String param, boolean ssl) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            // 打开和URL之间的连接
            URLConnection conn = getConn(url, headerParam, ssl);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            if (!ObjectUtils.isEmpty(param)) {
                System.out.println(param);
                //1.获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                //2.中文有乱码的需要将PrintWriter改为如下
                //out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8")
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            }

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            closeObject(out);
            closeObject(in);
        }
        System.out.println("post推送结果：" + result);
        return result;
    }

    /**
     * 关闭对象 CloseableHttpResponse，CloseableHttpClient，PrintWriter，BufferedReader
     * @param o 对象
     */
    private static void closeObject(Object o){
        if (null != o) {
            if (o instanceof CloseableHttpResponse) {
                CloseableHttpResponse httpResponse = (CloseableHttpResponse) o;
                try {
                    httpResponse.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (o instanceof CloseableHttpClient) {
                CloseableHttpClient httpClient = (CloseableHttpClient) o;
                try {
                    httpClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (o instanceof PrintWriter) {
                PrintWriter out = (PrintWriter) o;
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (o instanceof BufferedReader) {
                BufferedReader in = (BufferedReader) o;
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static URLConnection getConn(String url, Map<String, String> headerParam, boolean ssl) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        if (ssl) {
            HttpsURLConnection.setDefaultHostnameVerifier(new HttpsIgnore().new NullHostNameVerifier());
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        }
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        // 设置通用的请求属性
        if (!ObjectUtils.isEmpty(headerParam)) {
            for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        return conn;
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // TODO Auto-generated method stub
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    }};

    /**
     * post请求 ，请求数据放到body里
     * @param url   统一资源定位符
     * @param bodyData 请求数据
     * @return  返回数据
     * @throws Exception    异常
     */
    public static String doPostBodyData(String url, String bodyData) throws Exception {
        String result = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = getHttpPost(url, null); // 请求地址
            httpPost.setEntity(new StringEntity(bodyData, Encoding));
            httpClient = getHttpClient();
            // 得到返回的response.
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = getResult(entity, Encoding);
        } catch (Exception e) {
            throw e;
        } finally {
            // 关闭httpClient
            if (null != httpClient) {
                httpClient.close();
            }
            // 关闭response
            if (null != response) {
                EntityUtils.consume(response.getEntity()); // 会自动释放连接
                response.close();
            }
        }
        return result;
    }

    private static HttpPost getHttpPost(String url, Object o) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type", "application/json; charset=utf-8");
        return post;
    }

    private static CloseableHttpClient getHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        //需要通过以下代码声明对https连接支持
        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,
                new TrustSelfSignedStrategy())
                .build();
        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslcontext,hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslConnectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        cm.setDefaultMaxPerRoute(50);
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    private static String getResult(HttpEntity entity, String encoding) throws IOException {
        return EntityUtils.toString(entity, encoding);
    }
}
