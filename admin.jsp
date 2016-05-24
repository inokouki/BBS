<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
</head>
<body>

<c:if test="${ loginUser.departmentId == 1}">
ユーザー管理画面です。<br />

<a href="signup">ユーザー新規登録</a>
<br />
<br />

<div class="admin">
	<c:forEach items="${users}" var="message">
		<div class="admin">
			<div class="admin">
				<div class="admin">
					<span class="available">[使用可能かどうか(1:使用可能)(0:使用不可)]:
					<c:out value="${message.available} " /></span><br />
					<span class="department">[部署/役職(1は本部なので変更厳禁!!)]:
					<c:out value="${message.departmentId}" /></span><br />
					<span class="name">[名称]:<c:out value="${message.name} " /></span><br />
					<span class="loginid">[ログインID]:<c:out value="${message.loginId} " /></span><br />

					<form action="edit" method="post">
						<input type="submit" value="ユーザー情報の編集" >
						<input name="editid" type="text" id="editid" value="${message.id}">
					</form>

					<form action="admin" method="post">
						<input type="submit" value="アカウントの[停止]又は[復活]"
						onClick="return confirm('アカウントの[停止]又は[復活]を行いますか？')">
						<input name="stop" type="hidden" id="stop" value="${message.id}">
					</form>
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