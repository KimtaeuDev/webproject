<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.warp {
	width: 800px;
	height: 100% auto;
	background-color: white;
}

dt {
	float: left;
	background-color: red;
}

dd {
	background-color: blue;
}
</style>
<link rel="stylesheet" href="/css/summernote/summernote-lite.css">
</head>
<body>
	<div class="warp">
		<input type="text" class="title" />
		<div class="summernote"></div>
	</div>
	<div class="uploadDiv">
		<img class="image_preview" src="">
		<div class="image_list">
			<dl></dl>
		</div>
	</div>
	<button class="upload_board">upload</button>
	<button class="test">test</button>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</body>
<script src="/js/views/board/regist.js"></script>
</html>