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

			[投稿日検索](例:1993-11-04{西暦-月-日})→ <input name="searchTimeBefore" id="searchTimeBefore"/>
			<label for="searchTimeBefore">から</label>
			<input name="searchTimeAfter" id="searchTimeAfter" />
			<label for="searchTimeAfter">まで(両方入力する)</label> <br />
			<input type="submit" value="検索" />
		</form>

		<form action="./" method="post">
			<input type="submit" type="hidden" value="検索リセット">
		</form>

		<br /><br /><br />

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

<c:if test="${ empty searches }">

<div class="contribution">
	<c:forEach items="${contributions}" var="contribution">
		<div class="contribution">
			<div class="contribution">
				<div class="contribution">
					<span class="title">[件名]:<c:out value="${contribution.title}" /><br /></span>
					<span class="text">[本文]<br /><c:out value="${contribution.text}" /><br /></span>
					<span class="created">[投稿日時]:<c:out value="${contribution.created}" /></span>
					<span class="userId">[投稿者]:<c:out value="${contribution.userId}" /></span>
					<span class="id">[投稿number]:<c:out value="${contribution.id}"/></span>
					<span class="category">[投稿カテゴリー]:<c:out value="${contribution.category}"/></span><br />

					<c:if test="${loginUser.departmentId == 2 }">
					<form action="./" method="post">
						<input type="submit" value="投稿の削除"
						onClick="return confirm('投稿の削除を行いますか？')">
						<input name="delete" type="hidden" id="delete" value="${contribution.id}">
					</form>
					</c:if>

					<c:if test="${loginUser.departmentId == 3}" >
							<form action="./" method="post">
								<input type="submit" value="投稿の削除"
									onClick="return confirm('投稿の削除を行いますか？')">
								<input name="delete" type="hidden" id="delete" value="${contribution.id}">
								<input name="conname" type="hidden" id="conname" value="${contribution.userId}">
								<input name="loginbranchid" type="hidden" id="loginbranchid" value="${loginUser.branch_Id}">
							</form>

					</c:if>

					<br />
					<br />
				</div>
			</div>

			<div class="comment">
				<c:forEach items="${comments}" var="comment">
					<div class="comment">
						<c:if test="${ comment.contributionId == contribution.id }" >
							<span class="id">[コメント]:<c:out value="${comment.text}"/></span>
							<br />
						</c:if>
					</div>
				</c:forEach>
			</div>

			<form action="./" method="post" > <br />
				コメント(500文字以下)<br />
				<textarea name="comment" cols="100" rows="5" class="text"></textarea>
				<input name="contribution_id" type="hidden" id="contribution_id" value="${contribution.id}"/>
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
	<c:forEach items="${searches}" var="contribution">
		<div class="contribution">
			<div class="contribution">
				<div class="contribution">
					<span class="title">[件名]:<c:out value="${contribution.title}" /><br /></span>
					<span class="text">[本文]<br /><c:out value="${contribution.text}" /><br /></span>
					<span class="created">[投稿日時]:<c:out value="${contribution.created}" /></span>
					<span class="userId">[投稿者]:<c:out value="${contribution.userId}" /></span>
					<span class="id">[投稿number]:<c:out value="${contribution.id}"/></span>
					<span class="category">[投稿カテゴリー]:<c:out value="${contribution.category}"/></span><br />

					<c:if test="${loginUser.departmentId == 2 }">
					<form action="./" method="post">
						<input type="submit" value="投稿の削除"
						onClick="return confirm('投稿の削除を行いますか？')">
						<input name="delete" type="hidden" id="delete" value="${contribution.id}">
					</form>
					</c:if>

					<c:if test="${loginUser.departmentId == 3}" >
							<form action="./" method="post">
								<input type="submit" value="投稿の削除"
									onClick="return confirm('投稿の削除を行いますか？')">
								<input name="delete" type="hidden" id="delete" value="${contribution.id}">
								<input name="conname" type="hidden" id="conname" value="${contribution.userId}">
								<input name="loginbranchid" type="hidden" id="loginbranchid" value="${loginUser.branch_Id}">
							</form>

					</c:if>

					<br />
					<br />
				</div>
			</div>

			<div class="comment">
				<c:forEach items="${comments}" var="comment">
					<div class="comment">
						<c:if test="${ comment.contributionId == contribution.id }" >
							<span class="id">[コメント]:<c:out value="${comment.text}"/></span>
							<br />
						</c:if>
					</div>
				</c:forEach>
			</div>

			<form action="./" method="post" > <br />
				コメント(500文字以下)<br />
				<textarea name="comment" cols="100" rows="5" class="text"></textarea>
				<input name="contribution_id" type="hidden" id="contribution_id" value="${contribution.id}"/>
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

<form action="login" method="get">
	<input name="logout" type="hidden" id="logout" value="true" />
	<input type="submit" value="ログアウト">
</form>


</c:if>

<c:if test="${loginUser.available == 0 }">
	アカウントが停止されています。
	<meta http-equiv="refresh"content="3; url=login">
</c:if>



</body>
</html>
