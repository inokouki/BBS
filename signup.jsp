<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー登録</title>
</head>
<body>
<form action="signup" method="post"> <br />
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

	<label for="branch_id">支店(番号)</label> <br />
	<input name="branch_id" id="branch_id"></input> <br/>
	<br />

	<label for="department_id">部署・役職(番号)</label> <br />
	<input name="department_id" id="department_id"></input> <br/>
	<br />

	<input type="submit" value="登録" /> <br />
	<br />
	<a href="/home">戻る</a>
</form>
</body>
</html>