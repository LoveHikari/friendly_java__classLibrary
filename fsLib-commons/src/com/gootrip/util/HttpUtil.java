package com.gootrip.util;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class HttpUtil {
//region 使用Get方式获取数据

    /**
     * 使用Get方式获取数据
     *
     * @param url     请求地址，URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
     * @param cookie  cookie
     * @param charset 编码方式
     * @return html源代码
     */
    public static String sendGet(String url, String cookie, String charset) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("cookie", cookie);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), charset));
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
        return result;
    }

    /**
     * 使用Get方式获取数据
     *
     * @param url     请求地址，URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
     * @param charset 编码方式
     * @return html源代码
     */
    public static String sendGet(String url, String charset) {
        return sendGet(url, "", charset);
    }

    /**
     * 使用Get方式获取数据
     *
     * @param url 请求地址，URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
     * @return html源代码
     */
    public static String sendGet(String url) {
        return sendGet(url, "", "utf-8");
    }
//endregion

//region POST请求，字符串形式数据

    /**
     * POST请求，字符串形式数据
     *
     * @param url     请求地址
     * @param param   请求数据
     * @param cookie  cookie
     * @param charset 编码方式
     * @return html源代码
     */
    public static String sendPost(String url, String param, String cookie, String charset) throws UnsupportedEncodingException {
        String[] ps = param.split("&");
        Map<String,String> map = new HashMap();
        for (String p : ps){
            String[] ss = p.split("=");
            map.put(ss[0],ss[1]);
        }
        return sendPost(url, map, null, cookie, charset);
    }

    /**
     * POST请求，字符串形式数据
     *
     * @param url     请求地址
     * @param param   请求数据
     * @param charset 编码方式
     * @return html源代码
     */
    public static String sendPost(String url, String param, String charset) throws UnsupportedEncodingException {
        return sendPost(url, param, "", charset);
    }

    /**
     * POST请求，字符串形式数据
     *
     * @param url   请求地址
     * @param param 请求数据
     * @return html源代码
     */
    public static String sendPost(String url, String param) throws UnsupportedEncodingException {
        return sendPost(url, param, "", "utf-8");
    }
//endregion

//region POST请求，Map形式数据

    /**
     * POST请求，Map形式数据
     *
     * @param url        请求地址
     * @param param      请求数据
     * @param headerItem 请求头
     * @param cookie     cookie
     * @param charset    编码方式
     * @return html源代码
     */
    public static String sendPost(String url, Map<String, String> param, Map<String, String> headerItem, String cookie, String charset) throws UnsupportedEncodingException {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String buffer = getFormData(param, headerItem, charset);
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            setHeaderValue(conn, headerItem);
            conn.setRequestProperty("cookie", cookie);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(buffer);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求，Map形式数据
     *
     * @param url     请求地址
     * @param param   请求数据
     * @param charset 编码方式
     * @return html源代码
     */
    public static String sendPost(String url, Map<String, String> param, String charset) throws UnsupportedEncodingException {
        return sendPost(url, param,null, "", charset);
    }

    /**
     * POST请求，Map形式数据
     *
     * @param url   请求地址
     * @param param 请求数据
     * @return html源代码
     */
    public static String sendPost(String url, Map<String, String> param) throws UnsupportedEncodingException {
        return sendPost(url, param,null, "", "utf-8");
    }
//endregion

    /**
     * 获得请求参数
     *
     * @param param      请求参数列表
     * @param headerItem 请求头
     * @param charset    编码
     * @return 请求参数字符串
     * @throws UnsupportedEncodingException
     */
    private static String getFormData(Map<String, String> param, Map<String, String> headerItem, String charset) throws UnsupportedEncodingException {
        StringBuffer buffer = new StringBuffer();
        if (param != null && !param.isEmpty()) {
            if (headerItem==null || !headerItem.containsKey("content-type")){
                if (headerItem.get("content-type") == "multipart/form-data") {
                    String boundary = Long.toString(System.currentTimeMillis());//元素分割标记
                    headerItem.put("content-type", "multipart/form-data; boundary=---------------------------" + boundary);

                    for (Map.Entry<String, String> entry : param.entrySet()) {
                        buffer.append("-----------------------------" + boundary);
                        buffer.append("\r\n");
                        buffer.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"");
                        buffer.append("\r\n");
                        buffer.append("\r\n");
                        buffer.append(entry.getValue());
                    }
                    buffer.append("-----------------------------" + boundary + "--");
                    buffer.append("\r\n");
                } else if (headerItem.get("content-type").contains("json")) {
                    Gson gson = new Gson();
                    buffer.append(gson.toJson(param));
                } else {
                    for (Map.Entry<String, String> entry : param.entrySet()) {
                        buffer.append(entry.getKey()).append("=")
                                .append(URLEncoder.encode(entry.getValue(), charset))
                                .append("&");
                    }
                    buffer.deleteCharAt(buffer.length() - 1);
                }
            }else {
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    buffer.append(entry.getKey()).append("=")
                            .append(URLEncoder.encode(entry.getValue(), charset))
                            .append("&");
                }
                buffer.deleteCharAt(buffer.length() - 1);
            }


        }
        return buffer.toString();
    }

    /**
     * 设置请求头
     *
     * @param conn       url 连接
     * @param headerItem 请求头
     */
    private static void setHeaderValue(URLConnection conn, Map<String, String> headerItem) {
        // 设置通用的请求属性
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.setRequestProperty("content-type", "text/html");
        if (headerItem != null) {
            for (Map.Entry<String, String> entry : headerItem.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }
}

