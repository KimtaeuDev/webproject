<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/board/detail.css">
<link rel='stylesheet' href='/css/board/view.css'>
<head>
<meta charset="UTF-8">
</head>
<body>
	<header class="page_header">
		page header
		<%--<jsp:include page="" />--%>
	</header>
	<nav class="page_nav">
		page nav
		<%--<jsp:include page="" />--%>
	</nav>
	<aside class="page_aside">
		page aside left
		<%--<jsp:include page="" />--%>
	</aside>
	<main class="page_main">
		<section>

			<%-- 게시판 종류 --%>
			<header class="board_name"></header>

			<%-- 게시판 본문 --%>
			<article class="board_article">
			
				<div class="board_head">
					<div class="title"></div>
					<div class="writer"></div>
					<div class="regdate"></div>
				</div>
				
				<div class="board_detail">
					<pre class="content"></pre>
					<div class="board_goods">
						<div class="good"></div>
						Good
						<div class="bad"></div>
						Bad
					</div>
				</div>
				
			</article>

			<%-- 댓글 목록 및 작성--%>
			<article class="board_reply">
			
				<%-- 댓글 목록 --%>
				<div class="list">
					<table border='1'>
						<thead>
							<tr>
								<th>작성자</th>
								<th>내용</th>
								<th>좋아요</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>

				<div class="paging"></div>
				
				<%-- 댓글 작성--%>
				<div class="add_reply">
					
					<div class='user_info'>
						<div class='uid'>testuser</div>
					</div>
					<div class='reply_editor'>
					
						<div class='content_wrap'>
							<textarea class='content'></textarea>
						</div>
						
						<div class='tools'></div>
						
					</div>
					<div class='reply_regist'>
						<div>댓글작성</div>
					</div>
					
				</div>
				
			</article>
			
			<%-- 게시글 목록 --%>
			<article class="board_list">
				<div class="list">
					<table border='1'>
						<thead>
							<tr>
								<th>번호</th>
								<th>작성자</th>
								<th>제목</th>
								<th>조회수</th>
								<th>좋아요</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="paging"></div>
				<div class="btn_link"></div>
			</article>

		</section>
	</main>
	<aside class="page_aside">
		page aside right
		<%--<jsp:include page="" />--%>
	</aside>
	<footer class="page_footer">
		page footer
		<%--<jsp:include page="" />--%>
	</footer>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
<script src="/js/views/board/DetailPage.js"></script>
</html>