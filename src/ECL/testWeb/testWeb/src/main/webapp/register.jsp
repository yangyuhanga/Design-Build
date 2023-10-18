<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
    	.gray-bg {
			background-color: #f3f3f4;
		}
       
    </style>
    <!-- 引入 Bootstrap 样式文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="gray-bg">
    <div class="container mt-5">
        <form method="post" action="./register" class="border border-secondary p-4 rounded">
        	
	            <h2 class="mb-4">Register</h2>
	            <div class="mb-3">
	                <label for="userinfoid" class="form-label">userinfoid:</label>
	                <input type="text" name="userinfoid" id="userinfoid" class="form-control">
	            </div>
	            <div class="mb-3">
	                <label for="username" class="form-label">username:</label>
	                <input type="text" name="username" id="username" class="form-control">
	            </div>
	            <div class="mb-3">
	                <label for="gender" class="form-label">Gender (M/F):</label>
	                <input type="text" name="gender" id="gender" class="form-control">
	            </div>
	            <div class="mb-3">
	                <label for="password" class="form-label">Password(At least 5 characters):</label>
	                <input type="password" name="password" id="password" class="form-control">
	            </div>
	            <div class="mb-3">
	                <label for="email" class="form-label">email:</label>
	                <input type="text" name="email" id="email" class="form-control">
	            </div>
            
            <button type="submit" name="submit" class="btn btn-primary">Submit</button>
            <a href="./login.jsp" class="ms-2">Click here to login</a>
        </form>
    </div>

    <!-- 引入 Bootstrap JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
