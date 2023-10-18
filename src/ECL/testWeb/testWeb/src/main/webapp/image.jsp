<%@ page import="testWeb.vo.Records"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>列表显示</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f1f1f1;
	margin: 0;
	padding: 20px;
}

h1 {
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 10px;
	text-align: center;
	border: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
}

.item {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.item img {
	width: 150px;
	height: auto;
	margin-bottom: 10px;
}
</style>
</head>
<body>

	<table>
		<%
		List<Records> list = (List<Records>)session.getAttribute("list");
		for (Records records : list) {
	%>
		<tr>
			<td class="item"><img src="<%= records.getImageUrl() %>">
			</td>
		</tr>
		<% } %>
	</table>
</body>
</html>
