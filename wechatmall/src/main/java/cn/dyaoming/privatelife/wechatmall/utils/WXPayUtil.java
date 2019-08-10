package cn.dyaoming.privatelife.wechatmall.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WXPayUtil {

    public static final String FAIL = "FAIL";
    public static final String SUCCESS = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";
    public static final String MICROPAY_URL = "https://api.mch.weixin.qq.com/pay/micropay";
    public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    public static final String REVERSE_URL = "https://api.mch.weixin.qq.com/secapi/pay/reverse";
    public static final String CLOSEORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    public static final String REFUNDQUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
    public static final String DOWNLOADBILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    public static final String REPORT_URL = "https://api.mch.weixin.qq.com/payitil/report";
    public static final String SHORTURL_URL = "https://api.mch.weixin.qq.com/tools/shorturl";
    public static final String AUTHCODETOOPENID_URL = "https://api.mch.weixin.qq.com/tools/authcodetoopenid";
    public static final String SANDBOX_MICROPAY_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/micropay";
    public static final String SANDBOX_UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
    public static final String SANDBOX_ORDERQUERY_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/orderquery";
    public static final String SANDBOX_REVERSE_URL = "https://api.mch.weixin.qq.com/sandboxnew/secapi/pay/reverse";
    public static final String SANDBOX_CLOSEORDER_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/closeorder";
    public static final String SANDBOX_REFUND_URL = "https://api.mch.weixin.qq.com/sandboxnew/secapi/pay/refund";
    public static final String SANDBOX_REFUNDQUERY_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/refundquery";
    public static final String SANDBOX_DOWNLOADBILL_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/downloadbill";
    public static final String SANDBOX_REPORT_URL = "https://api.mch.weixin.qq.com/sandboxnew/payitil/report";
    public static final String SANDBOX_SHORTURL_URL = "https://api.mch.weixin.qq.com/sandboxnew/tools/shorturl";
    public static final String SANDBOX_AUTHCODETOOPENID_URL = "https://api.mch.weixin.qq.com/sandboxnew/tools/authcodetoopenid";


    public static Map<String, String> xmlToMap(String strXML)
            throws Exception {
        Map data = new HashMap();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
        Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int idx = 0; idx < nodeList.getLength(); idx++) {
            Node node = nodeList.item(idx);
            if (node.getNodeType() == 1) {
                Element element = (Element) node;
                data.put(element.getNodeName(), element.getTextContent());
            }
        }
        try {
            stream.close();
        } catch (Exception localException) {
        }
        return data;
    }

    public static String mapToXml(Map<String, String> data)
            throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key : data.keySet()) {
            String value = (String) data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty("indent", "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString();
        try {
            writer.close();
        } catch (Exception localException) {
        }
        return output;
    }

    /*public static String generateSignedXml(Map<String, String> data, String key)
            throws Exception {
        return generateSignedXml(data, key, WXPayConstants.SignType.MD5);
    }*/

    public static String generateSignedXml(Map<String, String> data, String key)
            throws Exception {
        String sign = generateSignature(data, key);
        data.put("sign", sign);
        return mapToXml(data);
    }

    public static boolean isSignatureValid(String xmlStr, String key)
            throws Exception {
        Map data = xmlToMap(xmlStr);
        if (!data.containsKey("sign")) {
            return false;
        }
        String sign = (String) data.get("sign");
        return generateSignature(data, key).equals(sign);
    }

   /* public static boolean isSignatureValid(Map<String, String> data, String key)
            throws Exception {
        return isSignatureValid(data, key);
    }*/

    public static boolean isSignatureValid(Map<String, String> data, String key)
            throws Exception {
        if (!data.containsKey("sign")) {
            return false;
        }
        String sign = (String) data.get("sign");
        return generateSignature(data, key).equals(sign);
    }

   /* public static String generateSignature(Map<String, String> data, String key)
            throws Exception {
        return generateSignature(data, key, WXPayConstants.SignType.MD5);
    }*/

    public static String generateSignature(Map<String, String> data, String key)
            throws Exception {
        Set keySet = data.keySet();
        String[] keyArray = (String[]) keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray)
            if (!k.equals("sign")) {
                if (((String) data.get(k)).trim().length() > 0)
                    sb.append(k).append("=").append(((String) data.get(k)).trim()).append("&");
            }
        sb.append("key=").append(key);
        //TODO 紧使用MD5签名
//        if (WXPayConstants.SignType.MD5.equals(signType)) {
        return MD5(sb.toString()).toUpperCase();
//        }
//        if (WXPayConstants.SignType.HMACSHA256.equals(signType)) {
//            return HMACSHA256(sb.toString(), key);
//        }

//        throw new Exception("Invalid sign_type: MD5");
    }

    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    public static String MD5(String data)
            throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString(item & 0xFF | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    public static String HMACSHA256(String data, String key)
            throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString(item & 0xFF | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }


    public String requestWithoutCert(String strUrl, Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs)
            throws Exception {
        String UTF8 = "UTF-8";
        String reqBody = WXPayUtil.mapToXml(reqData);
        URL httpUrl = new URL(strUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(connectTimeoutMs);
        httpURLConnection.setReadTimeout(readTimeoutMs);
        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(reqBody.getBytes(UTF8));

        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        String resp = stringBuffer.toString();
        if (stringBuffer != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return resp;
    }

   /* public String requestWithCert(String strUrl, Map<String, String> reqData, int connectTimeoutMs, int readTimeoutMs)
            throws Exception {
        String UTF8 = "UTF-8";
        String reqBody = WXPayUtil.mapToXml(reqData);
        URL httpUrl = new URL(strUrl);
        char[] password = this.config.getMchID().toCharArray();
        InputStream certStream = this.config.getCertStream();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(certStream, password);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(connectTimeoutMs);
        httpURLConnection.setReadTimeout(readTimeoutMs);
        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(reqBody.getBytes(UTF8));

        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        String resp = stringBuffer.toString();
        if (stringBuffer != null)
            try {
                bufferedReader.close();
            } catch (IOException localIOException) {
            }
        if (inputStream != null)
            try {
                inputStream.close();
            } catch (IOException localIOException1) {
            }
        if (outputStream != null)
            try {
                outputStream.close();
            } catch (IOException localIOException2) {
            }
        if (certStream != null) {
            try {
                certStream.close();
            } catch (IOException localIOException3) {
            }

        }

        return resp;
    }*/
}