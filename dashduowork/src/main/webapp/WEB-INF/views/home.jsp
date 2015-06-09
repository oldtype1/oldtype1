<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<head>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Home</a>
		</div>

		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav"></ul>
			
			<ul class="nav navbar-nav navbar-right">
				<!-- <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li> -->
				<li><a href="#">MyPage</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">MyPageMenu??
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">MyProfile</a></li>
						<li><a href="#">MyReserve</a></li>
						<li><a href="#">MyInn</a></li>
						<li class="divider"></li>
						<li><a href="#">etc..</a></li>
						<li class="divider"></li>
						<li><a href="#">Logout</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>
</head>
<body>
<h1>
	ISO-8859 >> UTF-8
</h1>
	<form class="navbar-form navbar-left" role="search">
		<input type="text" class="form-control" placeholder="State">
		<select class="form-control" id="select">
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select>
		<button type="submit" class="btn btn-default">검색</button>
	</form>
</body>
</html>
