<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>welcome</title>
    
    <style>
    	h1 {text-align:center}
    	.box{
            color:#38b0de;
        }
       
}
        
    </style>

  
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.7" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="row">
        <div class="col-sm-12">
        
            
	        <div>
	
	            <h1 class="logo-name">WELCOME</h1>
	
	        </div>
	        
	        
	        
	            
            
            <div class="wrapper wrapper-content">
                <div class="row animated fadeInRight">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                           
                            <div class="" id="ibox-content">

                                <div id="vertical-timeline" class="vertical-container light-timeline">
                                    
                                    <div class="vertical-timeline-block">
                                        <div class="vertical-timeline-icon navy-bg">
                                            <i class="fa fa-briefcase"></i>
                                        </div>

                                        <div class="vertical-timeline-content">
                                        	<div class="box">
                                            
	                                            <%String username = (String)session.getAttribute("username"); %>
											    <%String robotid = (String)session.getAttribute("robotid"); %>
											    <h3><b>Hello <%= username %></b></h3>
											    <br>
											    <h3>your robotid is <%=robotid %></h3>
	                                            
                                        	</div>
                                        </div>
                                    </div>


                                    <div class="vertical-timeline-block">
                                        <div class="vertical-timeline-icon blue-bg">
                                            <i class="fa fa-file-text"></i>
                                        </div>

                                        <div class="vertical-timeline-content">
                                            <h3>Our Function...</h3>
                                            <br>
                                            <h4>Record the robot's exploration time,speed,distance...</h4>
                                            
                                        </div>
                                    </div>

                                    <div class="vertical-timeline-block">
                                        <div class="vertical-timeline-icon lazur-bg">
                                            <i class="fa fa-coffee"></i>
                                        </div>

                                        <div class="vertical-timeline-content">
                                            <h3>More Function...</h3>
                                            <br>
                                            <h4>Take photos while exploration...</h4>
                                  
                                        </div>
                                    </div>
                                    
                                    <div align="center">
                                    	<a href="menu.jsp" class="btn btn-primary btn-lg"> <b>VIEW MORE</b></a>
                                    
                                    </div>

                                    
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <!-- 自定义js -->
    <script src="js/content.js?v=1.0.0"></script>


    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <!--统计代码，可删除-->
</body>

</html>
