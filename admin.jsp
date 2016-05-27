<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<link rel="stylesheet" type="text/css" href="css/admin.css" />
</head>
<body>

<h1>ユーザー管理</h1><br /><br />

<c:if test="${ loginUser.departmentId == 1}">

<h2><a href="signup">ユーザー新規登録</a>
<a href="./" >ホーム</a>
<br /><br /><br /></h2>

<div class="admin">
	<c:forEach items="${users}" var="message">
		<div class="admin">
			<div class="admin">
				<div class="admin">
					<c:if test="${ message.departmentId  == 1}">
						<h3>↓↓↓操作厳禁↓↓↓</h3>
					</c:if>

					<span class="loginid">[ログインID]<c:out value="${message.loginId} " /></span>
					<span class="name">,[名称]<c:out value="${message.name} " /></span>

					<form action="edit" method="get">
						<input type="submit" value="ユーザー情報の編集" >
						<input name="editid" type="hidden" id="editid" value="${message.id}">
					</form>

					<c:if test="${ message.available  == 1}">
					<form action="admin" method="post">
						<input type="submit" value="アカウントの[停止]"
						onClick="return confirm('このアカウントは現在使用可能です。アカウントの[停止]を行いますか？')">
						<input name="stop" type="hidden" id="stop" value="${message.id}">
					</form>
					</c:if>

					<c:if test="${ message.available  == 0}">
					<form action="admin" method="post">
						<input type="submit" value="アカウントの[復活]"
						onClick="return confirm('このアカウントは現在停止中です。アカウントの[復活]を行いますか？')">
						<input name="stop" type="hidden" id="stop" value="${message.id}">
					</form>
					</c:if>

					<form action="admin" method="post">
						<input type="submit" value="アカウントの削除"
						onClick="return confirm('アカウントの削除を行いますか？')">
						<input name="delete" type="hidden" id="delete" value="${message.id}">
					</form>

					<c:if test="${ message.departmentId  == 1}">
						<h3>↑↑↑操作厳禁↑↑↑</h3>
					</c:if>
					<br />
					<br />
					<br />
				</div>
			</div>
		</div>
	</c:forEach>
</div>

</c:if>

<c:if test="${ loginUser.departmentId != 1 }">
アクセス権限がありません。3秒後にホーム画面に戻ります。
	<meta http-equiv="refresh"content="3; url=./">
</c:if>

</body>
</html>