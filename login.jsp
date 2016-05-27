<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
	<link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body>
<div class="main-contents">
<h4>バリストライドグループ 掲示板システム</h4>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<h3><c:out value="${message}" /></h3>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="login" method="post"><br />
	<h2><label for="loginid">ログインID</label></h2>
	<h2><input name="loginid" id="loginid" /></h2> <br />

	<h2><label for="password">パスワード</label></h2>
	<input name="password" type="password" id="password" />
	<br /><br />

	<input type="submit" value="ログイン" />
</form>
</div>
</body>
</html>