<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>运行物体检测GUI……</title>
</head>
<body>
    <h1>正在运行物体检测GUI……</h1>

    <%

        // 设置预测和标签文件夹的路径
        String predictFolderPath = "E:\\xxq\\runs\\detect\\predict9";
        String labelsFolderPath = "E:\\xxq\\runs\\detect\\predict9\\labels";

        // 创建并显示 ObjectDetectionGUIch 窗口
        testWeb.vo.ObjectDetectionGUIch objectDetectionGUI = new testWeb.vo.ObjectDetectionGUIch(predictFolderPath, labelsFolderPath);
        objectDetectionGUI.setVisible(true);
    %>
</body>
</html>