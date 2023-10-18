<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
	.gray-bg {
			background-color: #f3f3f4;
		}
</style>
<!-- 引入 Bootstrap 样式文件 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

<style>
.container {
	width: 400px;
	margin-top: 100px;
}
</style>
</head>
<body class="gray-bg">
	<div class="container">
		<form method="post" action="./login"
			class="border border-secondary p-4 rounded">
			<h2 class="mb-4">Login</h2>
			<div class="mb-3">
				<label for="username" class="form-label">Username:</label> <input
					type="text" name="username" id="username" class="form-control">
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">Password:</label> <input
					type="password" name="password" id="password" class="form-control">
			</div>
			<button type="submit" name="submit" class="btn btn-primary">Submit</button>
			<a href="./register.jsp" class="ms-2">Click here to register</a>
		</form>
	</div>

	<!-- 引入 Bootstrap JavaScript 文件 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
