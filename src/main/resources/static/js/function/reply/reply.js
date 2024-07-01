// 댓글 생성
const createReply = (reply_data) => {
	
	let result = 0;
	
	$.ajax({
		url : '/reply/createreply.do',
		type : 'post',
		async : false,
		data: JSON.stringify(reply_data),
		contentType: 'application/json',
		success : function(bno) {
			result = bno;
		},
		error : function() {
			result = 0;
		}

	});
	
	return result;
	
};

// 댓글 리스트 조회
const readReplyList = (bno, btype, order, page) => {
	
	let reply;
	let enable = false;
	
 	$.ajax({
		url : '/reply/readreplylist.do',
		type : 'get',
		dataType : 'json',
		async : false,
		data : {
			bno : bno,
			btype : btype,
			order : order,
			page : page,
		},
		success : function(data) {
			reply = data;
			enable = true;
	    },
		error : function() {
			enable = false;
		}
	});
	
 	return {
		enable:enable,
		list:reply.list,
		paging:reply.paging
	};
 	
};

// 댓글 수정
const updateReply = (reply_data) => {
	
	$.ajax({
		url : '/reply/updatereply.do',
		type : 'post',
		async : false,
		data: JSON.stringify(reply_data),
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

// 댓글 삭제
const deleteReply = (btype, bno, rno) => {
	
	let result = false;
	
	$.ajax({
		url : '/reply/deletereply.do',
		type : 'get',
		dataType : 'json',
		async : false,
		data : {
			btype : btype,
			bno : bno,
			rno : rno,
		},
		success : function(data) {
			result = data;
	    },
		error : function() {
			result = 0;
		}

	});
	
	return result;
	
};