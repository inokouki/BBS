<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集画面</title>
	<link rel="stylesheet" type="text/css" href="css/edit.css" />
</head>
<body>

<c:if test="${ loginUser.departmentId == 1}">

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<h3><c:out value="${message}"/></h3>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="edit" method="post"> <br />
	<label for="login_id">ログインIDを入力してください(半角英数字:6文字以上、20文字以下)</label> <br />
	<input name="login_id" id="login_id"/> <br />
	<br />

	<label for="password">パスワードを入力してください(半角文字:6文字以上、255文字以下)</label> <br />
	<input name="password" id="password"/> <br />
	<br />

	<label for="checkpassword">確認のため、パスワードをもう一度入力してください</label> <br />
	<input name="checkpassword" type="password" id="checkpassword"/> <br />
	<br />

	<label for="name">名前(10文字以下)</label> <br />
	<input name="name" id="name"/> <br />
	<br />

	支店<select name="branch_id">
	<option value="0">選択してください</option>
	<c:forEach items="${branches}" var="branch">
		<option value="${branch.id}">${branch.name}</option>
	</c:forEach>
	</select><br /><br />

	部署・役職<select name="department_id">
	<option value="0">選択してください</option>
	<c:forEach items="${departments}" var="department">
		<option value="${department.id}">${department.name}</option>
	</c:forEach>
	</select><br /><br />

	<input type="submit" value="変更" /> <br /><br />
</form>

<a href="admin" >戻る</a>

</c:if>

<c:if test="${ loginUser.departmentId != 1 }">
アクセス権限がありません。3秒後にホーム画面に戻ります。
	<meta http-equiv="refresh"content="3; url=./">
</c:if>

</body>
</html>