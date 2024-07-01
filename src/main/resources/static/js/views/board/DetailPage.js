document.write("<script src='/js/function/util/time.js'></script>");
document.write("<script src='/js/function/util/view.js'></script>");
document.write("<script src='/js/function/board/board.js'></script>");
document.write("<script src='/js/function/reply/reply.js'></script>");
let params = new URLSearchParams(window.location.search);

// --------------------- 본문 ------------------------ //
$(document).ready(function() {
	
	let board = readBoard(params.get('no'));
	
	if(board.result == true){
		$(".board_head div.title").html(board.title);
		$(".board_head div.writer").html(board.writer);
		$(".board_head div.regdate").html(getCreated(board.regdate));
		$(".board_detail pre.content").html(board.content);
		$(".board_detail div.good").html(board.good);
		$(".board_detail div.bad").html(board.bad);
	}
	
});

$(document).on("click", ".board_goods .good", function(){
	updateBoardGoods("good", 1);
});

$(document).on("click", ".board_goods .bad", function(){
	updateBoardGoods("bad", -1);
});


// --------------------- 댓글 리스트 & 등록 ------------------------ //

var reply_order = "asc";


// 댓글 리스트 html 작성
const setReplyList = (list) => {

	let html = "";
		 
	list.forEach(function(li){
			
		html += "<a href='CreateRereplyForm(" + li.reply_no + ")'><tr";
		
		if(li.reply_dept == 1){
			html += " class='reply'>";
		}else if(li.reply_dept == 2){
			html += " class='rereply'>";
		}
		
		html += "<td class='writer'>" + li.reply_writer + "</td>"
			  + "<td class='content'>" + li.reply_content + "</td>"
			  + "<td class='goods'>" + li.reply_good + "</td>"
			  + "<td class='time'>" + getCreated(li.reply_regdate) + "</td>"
			  + "</tr></a>";
		
	});
	
	return html;
	
};

// 댓글 리스트 페이징
const setReplyPaging = (paging) => {
	
	if(paging.lastPage < 1){
		return "";
	}

	let html = "<ul>";
	
	if(paging.prev == true){
		html += "<a href='loadReplys(1);'><<</a>";
		html += "<a href='loadReplys(" + paging.firstPage - 1 + ");'></a>";
	}
	
	for(let i = paging.firstPage; i <= paging.lastPage; i++){
		if(i == paging.curPage){
			html += "<b>" + i +"</b>";
		}else{
			html += "<a href='loadReplys(" + i + ");'></a>";
		}
			
	}
	
	if(paging.next == true){
		html += "<a href='loadReplys(" + paging.lastPage + 1 + ");'>></a>";
		html += "<a href='loadReplys(" + paging.endPage + ");'>>></a>";
	}
	
	return html;
	
};

// 댓글 리스트 불러오기
const loadReplys = (page) => {
	
	let reply = readReplyList(
		params.get("no"),
		params.get("btype"), 
		reply_order, 
		page
	);
	
	$(".board_reply").find("tbody").html(setReplyList(reply.list));
	$(".board_reply").find(".paging").html(setReplyPaging(reply.paging));
	
};



const CreateReplyForm = () => {
	let html = "";
	// 로그인 확인
	// 비 로그인 상태
	// if(){};
	// 로그인 상태
/*	html = "<div class='userinfo_add_reply' style='display: inline-block; width:15%; height:150px; background-color:white;'><div class='uid'>" + "testuser" + "</div></div>";
	html += "<div class='input_add_reply' style='display:inline-block; width:70%; height:150px; background-color:white;'><textarea class='reply_text type='text' style='width:90%; height:90%; resize:none; background-color:blue;'></textarea></div>";
	html += "<div class='btn_add_reply' style='display: inline-block; width:15%; height:150px; background-color:white;'>댓글작성</div>";*/
	//$(".board_reply div.add_reply").html(html);
};

const CreateRereplyForm = (rno) => {
	
	$(".board_reply tbody .add_reply").remove();
	
};


// 댓글 클릭
$(document).on("click", ".btn_add_reply", function() {

	$(this).prevAll("dt.uploaded_image").html();
});

// 댓글 작성
$(document).on("click", ".btn_add_reply", function() {
	alert("작성");
	//rno, dept, writer(서버), content 
	

	let rno = 0;
	let dept = rno == 0 ? 1 : 2;

	let reply_data = {
		"board_no": params.get("no"),
		"board_type": params.get("btype"),
		"reply_group": rno,
		"reply_dept": dept,
		"reply_writer": "test_writer",
		"reply_content": $(".note-editable").html()
	}

	registReply(reply_data);
	
});


$(document).ready(function() {
	CreateReplyForm();
	loadReplys(1);
	
});

// --------------------- 게시글 리스트 ------------------------ //
//게시글 리스트 html 구현
const setBoardList = (list) => {

	if (list.length == 0) return;

	let html = "";
	let copyparams = new URLSearchParams(window.location.search);

	list.forEach(function(li) {

		copyparams.set("no", li.board_no);	
		let thisview = window.location.origin + "/board/list?" + copyparams;
		let title = li.board_title.length > 20 ? title.substr(0, 20) + "..." : li.board_title;
		
		html += "<tr><td class='bno'>" + li.board_no + "</td>"
			+ "<td class='writer'>" + li.board_writer + "</td>"
			+ "<td class='title'><a href='" + thisview + "'>" + title + " [" + li.reply_count + "]" + "</a></td>"
			+ "<td class='views'>" + li.board_views + "</td>"
			+ "<td class='goods'>" + li.board_good + "</td>"
			+ "<td class='time'>" + getCreated(li.board_regdate) + "</td></tr>";

	});

	return html;

};

//게시글 페이징 html 구현
const setBoardPaging = (paging) => {

	let html = ""; 

	if (paging.lastPage < 1) {
		return "";
	}

	let thisview = window.location.origin + "/board/list?";
	
	let copyparams = new URLSearchParams(window.location.search);
	
	copyparams.delete("no");
	
	if (paging.prev == true) {
		copyparams.set("page", 1);
		html += "<a href='" + thisview + copyparams + "'><<</a>";

		copyparams.set("page", paging.firstPage - 1);
		html += "<a href='" + thisview + copyparams + "'><</a>";
	}

	for (let i = paging.firstPage; i <= paging.lastPage; i++) {
		if (i == params.get("page")) {
			html += "<b>" + i + "</b>";
		} else {
			copyparams.set("page", i);
			html += "<a href='" + thisview + copyparams + "'>" + i + "</a>";
		}

	}

	if (paging.next == true) {
		copyparams.set("page", paging.lastPage + 1);
		html += "<a href='" + thisview + copyparams + "'>></a>";

		copyparams.set("page", paging.endPage);
		html += "<a href='" + thisview + copyparams + "'>>></a>";
	}

	return html;

};

$(document).ready(function() {

	$(".board_name").html(readBoardName(params.get("btype")));
			
	let board = readBoardList(
		params.get("btype"), 
		params.get("keytype"), 
		params.get("keyword"), 
		params.get("page")
	);
		
	$(".board_list").find("tbody").html(setBoardList(board.list));
	$(".board_list").find(".paging").html(setBoardPaging(board.paging));

});