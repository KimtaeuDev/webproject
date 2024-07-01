<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<head>
<meta charset="UTF-8">
<script></script>
<style>
html, body{
	width:96vw;
	height:100vh;
}
</style>
<script>
const test1 = () => {
	alert(readBoardName("free"));
};
//게시판 이름 조회
const readBoardName = (btype) => {

	let result;

	$.ajax({
		url: '/test/readboardname.do',
		type: 'get',
		dataType: 'text',
		async: false,
		data: {
			btype: btype,
		},
		success: function(bname) {
			result = bname + " 게시판";
		},
		error: function() {
			alert("error");
		}
	});

	return result;

};

const test2 = () => {
	alert(createBoard($(".title").val(), $(".note-editable").text(), "free"));
};
// 게시글 생성
const createBoard = (title, content, btype) => {
	
	let result = false;
	
	let board_data ={
		board_title : title,
		board_content : content,
		board_btype : btype
	}
	$.ajax({
		url : '/test/createboard.do',
		type : 'post',
		async : false,
		data: JSON.stringify(board_data),
		contentType: 'application/json',
		success : function(bno) {
			result = bno > 0 ? bno : 0;
		},
		error : function() {
			result = 0;
		}

	});
	
	return result;
};

const test3 = () => {
	let board = readBoard(351);
	
	alert(board.writer + " / " + board.title + " / " + board.content);
};
// 게시글 조회
const readBoard = (bno) => {

	let board, result = false;
	
	$.ajax({
		url : '/test/readboard.do',
		type : 'get',
		dataType : 'json',
		async : false,
		data : {
			bno : bno,
		},
		success : function(data) {
 			board = data;
 			result = 1;
	    },
		error : function() {
			result = 0;
		}
	});
	
	return {
		result : result,
		title : board.board_title,
		writer : board.board_writer,
		regdate : board.board_regdate,
		content : board.board_content,
		good : board.board_good,
		bad : board.board_bad
 	};
	
};

const test4 = () => {
	alert(updateBoard($(".title").val(), $(".note-editable").text(), "free"));
};
// 게시글 갱신
const updateBoard = (title, content, btype) => {
	
	let result = false;
	
	let board_data ={
		board_title : title,
		board_content : content,
		board_btype : btype
	}
	$.ajax({
		url : '/test/updateboard.do',
		type : 'post',
		async : false,
		data: JSON.stringify(board_data),
		contentType: 'application/json',
		success : function(data) {
			result = data;
		},
		error : function() {
			result = 0;
		}

	});
	
	return result;
};

const test5 = () => {
	alert(deleteBoard("free", 351));
};

// 게시글 삭제
const deleteBoard = (btype, bno) => {
	
	let result;

	$.ajax({
		url: '/test/deleteboard.do',
		type: 'post',
		dataType: 'text',
		async: false,
		data: {
			btype: btype,
			bno: bno,
		},
		success: function(data) {
			result = data;
		},
		error: function() {
			result = 0;
		}
	});

	return result;
	
};
</script>

</head>
<body>
	<button onclick="test1();">1</button>
	<br>
	<button onclick="test2();">2</button>
	<br>
	<input class="title" value="test title">
	<pre class="note-editable">test content</pre>
	<br>
	<button onclick="test3('free');">3</button>
	<br>
	<button onclick="test4('free');">4</button>
	<br>
	<button  onclick="test5('free');">5</button>
</body>
</html>