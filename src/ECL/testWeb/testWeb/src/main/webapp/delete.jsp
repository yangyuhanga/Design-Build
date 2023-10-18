<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="testWeb.vo.Records"%>
<%@ page import="testWeb.vo.DataRetriever"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
    background: #d7c7e9;
    align-items: center;
    text-align: center;
}
</style>
</head>
<body>
<script>
//JavaScript函数，用于删除记录
function deleteRecord(recordId) {
    var xhr = new XMLHttpRequest();
    var url = "DeleteServlet?recordId=" + recordId; // 构建正确的URL

    xhr.open("POST", url, true);

    // 设置请求头
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // 监听请求状态变化
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 请求已完成，刷新当前页面以显示最新数据
            window.location.reload();
        }
    };

    // 发送删除请求，传递要删除的记录ID
    xhr.send();
}
</script>



    <table align="center" width="700" border="1" height="180" bordercolor="white" cellpadding="1" cellspacing="1">
        <tr bgcolor="white">
            <td align="center" colspan="7">
                <h2>Record</h2>
            </td>
        </tr>
        <tr align="center" bgcolor="#e1ffc1">
            <td><b>RecordID</b></td>
            <td><b>Time Consuming</b></td>
            <td><b>Speed</b></td>
            <td><b>Distance</b></td>
            <td><b>Direction</b></td>
            <td><b>DELETE</b></td>
        </tr>
        <%
            List<Records> list = DataRetriever.retrieveDataFromDatabase(); // 使用 DataRetriever 类来获取数据

            if(list == null || list.size() < 1){
                out.print("error");
            } else {
                for (Records records : list) {
        %>
      <tr align="center">
    <td><%= records.getRecordid() %></td>
    <td><%= records.getTime() %></td>
    <td><%= records.getSpeed() %></td>
    <td><%= records.getDistance() %></td>
    <td><%= records.getDirection() %></td>
    <td><input type="submit" value="delete" data-recordid="<%= records.getRecordid() %>"></td>
</tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>

