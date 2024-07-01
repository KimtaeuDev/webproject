//게시판 이름 조회
const readBoardName = (btype) => {

	let result;

	$.ajax({
		url: '/board/readboardname.do',
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

// 게시글 생성
const createBoard = (title, content, btype) => {
	
	let result = false;
	
	let board_data ={
		board_title : title,
		board_content : content,
		board_btype : btype
	}
	$.ajax({
		url : '/board/createboard.do',
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

// 게시글 조회
const readBoard = (bno) => {

	let board, result = false;
	
	$.ajax({
		url : '/board/readboard.do',
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

// 게시글 갱신
const updateBoard = (title, content, btype) => {
	
	let result = false;
	
	let board_data ={
		board_title : title,
		board_content : content,
		board_btype : btype
	}
	$.ajax({
		url : '/board/updateboard.do',
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

// 게시글 삭제
const deleteBoard = (btype, bno) => {
	
	let result;

	$.ajax({
		url: '/board/deleteboard.do',
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

const updateBoardGoods = (goods, count) => {
	
	//인증 필요
		$.ajax({
		url : '/board/updateboardgoods.do',
		type : 'get',
		dataType : 'json',
		async : false,
		data : {
			goods : goods,
			count : count,
		},
		success : function(result) {
 			if(result.enable == true){
				 $(".board_goods > ." + goods).html() += count;
			}
	    },
		error : function() {
			enable = false;
		}
	});
	
};

// 게시글 리스트 DB조회
const readBoardList = (btype, keytype, keyword, page) => {

	let result;

	$.ajax({
		url: '/board/readboardlist.do',
		type: 'get',
		dataType: 'json',
		async: false,
		data: {
			btype: btype,
			keytype: keytype,
			keyword: keyword,
			page: page
		},
		success: function(data) {
			result = data;
		},
		error: function() {
			result = 0;
			alert("error");
		}

	});

	return {
		list : result.list,
		paging : result.paging
	};

};