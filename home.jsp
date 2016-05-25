<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム</title>
	<link rel="stylesheet" type="text/css" href="css/home.css" />
</head>
<body>

<c:if test="${loginUser.available == 1 }">

<div class="main-contents">

<div class="header">
	<c:if test="${ empty loginUser }">
		<a href="login">ログイン</a>
	</c:if>
	<c:if test="${ not empty loginUser }">
		<h1>ログインしました。${loginUser.name}さん、こんにちは。</h1>
		<h2><a href="admin">ユーザー管理(総務人事担当のみ)</a></h2>
		<h2><a href="post">新規投稿</a></h2>
		<br />
	</c:if>
</div>

		<form action="./" method="get">
			<label for="searchCategory">[カテゴリー検索]→</label>
			<input name="searchCategory" id="searchCategory" /> <br />

			[投稿日検索](例:xxxx-xx-xx{西暦-月-日})→ <input name="searchTimeBefore" id="searchTimeBefore" />
			<label for="searchTimeBefore">から</label>
			<input name="searchTimeAfter" id="searchTimeAfter" />
			<label for="searchTimeAfter">まで(両方入力する)</label> <br />
			<input type="submit" value="検索" /> <br />
		</form>

		<br /><br /><br />

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" /></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<c:if test="${ empty searches }">

<div class="contribution">
	<c:forEach items="${contributions}" var="message">
		<div class="contribution">
			<div class="contribution">
				<div class="contribution">
					<span class="title">[件名]:<c:out value="${message.title}" /><br /></span>
					<span class="text">[本文]<br /><c:out value="${message.text}" /><br /></span>
					<span class="created">[投稿日時]:<c:out value="${message.created}" /></span>
					<span class="userId">[投稿者]:<c:out value="${message.userId}" /></span>
					<span class="id">投稿number:<c:out value="${message.id}"/></span><br />

					<c:if test="${loginUser.departmentId == 2 }">
					<form action="./" method="post">
						<input type="submit" value="投稿の削除"
						onClick="return confirm('投稿の削除を行いますか？')">
						<input name="delete" type="hidden" id="delete" value="${message.id}">
					</form>
					</c:if>

					<c:if test="${(loginUser.departmentId == 3 || loginUser.departmentId == 4 ||
						loginUser.departmentId == 5) }">
					<form action="./" method="post">
						<input type="submit" value="投稿の削除"
						onClick="return confirm('投稿の削除を行いますか？')">
						<input name="delete" type="hidden" id="delete" value="${message.id}">
					</form>
					</c:if>

					<br />
				</div>
			</div>

			<div class="comment">
				<c:forEach items="${comments}" var="comment">
					<div class="comment">
						<c:if test="${ comment.contributionId == message.id }" >
							<span class="id">[コメント]:<c:out value="${comment.text}"/></span>
							<br />
						</c:if>
					</div>
				</c:forEach>
			</div>

			<form action="./" method="post" > <br />
				コメント(500文字以下)<br />
				<textarea name="comment" cols="100" rows="5" class="text"></textarea>
				<input name="contribution_id" type="hidden" id="contribution_id" value="${message.id}"/>
				<br />
				<input type="submit" value="コメントする">
			</form>
			<br />
			<br />
		</div>
	</c:forEach>
	<br />
	<br />
</div>
</c:if>

<c:if test="${ not empty searches }">

<div class="contribution">
	<c:forEach items="${searches}" var="message">
		<div class="contribution">
			<div class="contribution">
				<div class="contribution">
					<span class="title">[件名]:<c:out value="${message.title}" /><br /></span>
					<span class="text">[本文]<br /><c:out value="${message.text}" /><br /></span>
					<span class="created">[投稿日時]:<c:out value="${message.created}" /></span>
					<span class="userId">[投稿者]:<c:out value="${message.userId}" /></span>
					<span class="id">投稿number:<c:out value="${message.id}"/></span>
					<br />
					<br />
				</div>
			</div>

			<div class="comment">
				<c:forEach items="${comments}" var="comment">
					<div class="comment">
						<c:if test="${ comment.contributionId == message.id }" >
							<span class="id">[コメント]:<c:out value="${comment.text}"/></span>
							<br />
						</c:if>
					</div>
				</c:forEach>
			</div>

			<form action="./" method="post" > <br />
				コメント(500文字以下)<br />
				<textarea name="comment" cols="100" rows="5" class="text"></textarea>
				<input name="contribution_id" type="hidden" id="contribution_id" value="${message.id}"/>
				<br />
				<input type="submit" value="コメントする">
			</form>
			<br />
			<br />
		</div>
	</c:forEach>
	<br />
	<br />
</div>
</c:if>
</div>

</c:if>

<c:if test="${loginUser.available == 0 }">
	アカウントが停止されています。
	<meta http-equiv="refresh"content="1; url=login">
</c:if>

</body>
</html>
