document.write("<script src='/js/function/util/time.js'></script>");
document.write("<script src='/js/function/board/board.js'></script>");
let params = new URLSearchParams(window.location.search);

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

	$(".btn_link").html("<a href='" + window.location.origin +
		"/board/regist?btype=" + params.get('btype') + "'>글쓰기</a>");

});