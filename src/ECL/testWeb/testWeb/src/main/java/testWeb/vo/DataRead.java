package testWeb.vo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataRead {
    public static void main(String[] args) {
        try {
            // 设置要运行的 JSP 页面的 URL
            URL url = new URL("http://localhost:8080/testWeb/menu.jsp");

            // 打开 HTTP 连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 发送 GET 请求
            connection.setRequestMethod("GET");

            // 获取响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // 处理响应内容，这里可以根据需要进行处理
            System.out.println(content.toString());

            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}