<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="testWeb.vo.DataRetriever" %>
<%@ page import="testWeb.vo.Records" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>menu</title>

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
    
    

</head>

<body class="gray-bg top-navigation">

    <div id="wrapper">
        <div id="page-wrapper" class="gray-bg">
            <div class="row border-bottom white-bg">
                <nav class="navbar navbar-static-top" role="navigation">
                  
                    <div class="navbar-collapse collapse" id="navbar">
                        <ul class="nav navbar-nav">
                            <li class="active">
                                <a aria-expanded="false" role="button" href="welcome.jsp"> HOME</a>
                            </li>
                            
                            <li >
                                <a aria-expanded="false" role="button" id="dataReadButton" >DATAREAD </a>
                            </li>
                            
                            <li >
                                <a aria-expanded="false" role="button" id="openGuiButton" href="runObjectDetection.jsp" >DETECTION </a>
                            </li>
                            
                            <li class="dropdown">
                                <a aria-expanded="false" role="button" href="image.jsp" class="dropdown-toggle" data-toggle="dropdown"> VIEW<span class="caret"></span></a>
                                <ul role="menu" class="dropdown-menu">
                                  
                                </ul>
                            </li>                           
                        </ul>
                        <ul class="nav navbar-top-links navbar-right">
                            <li>
                                <a href="login.jsp">
                                    <i class="fa fa-sign-out"></i> LOG OUT
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        
            
            <div class="wrapper wrapper-content">
                <div class="container">
                    <div class="row">

                        <div class="col-lg-12">
                            <div class="ibox float-e-margins">
                                <div class="ibox-title">
                                    <h5>RECORD</h5>
                                    <div class="ibox-tools">
                                        <a class="collapse-link">
                                            <i class="fa fa-chevron-up"></i>
                                        </a>
                                       
                                        <a class="close-link">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                    
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>RecordID</th>
                                                    <th>Time Consuming</th>
                                                    <th>Speed</th>
                                                    <th>Distance</th>
                                                    <th>Direction</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <%
										    	List<Records> list = (List<Records>)session.getAttribute("list");
										      
										            for(Records records:list){
										    %>
										        <tr>
										            <td><%=records.getRecordid()%>
										            </td>
										            <td><%=records.getTime()%>
										            </td>
										            <td><%=records.getSpeed()%>
										            </td>
										            <td><%=records.getDistance()%>
										            </td>
										            <td><%=records.getDirection()%>
										            </td>
										        </tr>
										    <%
										            }
										        
										            
										            
										    %>
                                              
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>

                </div>

            </div>
           

        </div>
    </div>

	<script>
        // JavaScript函数，用于打开物体检测GUI
        function openObjectDetectionGUI() {
           
            window.open('runObjectDetection', '_blank');
        }

        // 给按钮添加点击事件监听器
        document.getElementById("openGuiButton").addEventListener("click", openObjectDetectionGUI);
    </script>

	<script>
        document.getElementById("dataReadButton").addEventListener("click", function() {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "dataread", true);

            // 监听请求状态变化
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4) {
                    // 请求已完成，执行页面跳转
                    window.location.href = "menu.jsp";
                }
            };

            xhr.send();
        });
    </script>
    
    
    
    
    
    <!-- 全局js -->
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.7"></script>


   
    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>


    <!-- Flot -->
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>

    <!-- ChartJS-->
    <script src="js/plugins/chartJs/Chart.min.js"></script>

    <!-- Peity -->
    <script src="js/plugins/peity/jquery.peity.min.js"></script>

    <!-- Peity demo -->
  

    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <!--统计代码，可删除-->

</body>

</html>
