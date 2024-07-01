import upload from "/js/file/image.js";

// load image by server url
let imageServer = window.location.origin + "/imageview.do?id=";

// load board_type by param
const searchParams = new URLSearchParams(window.location.search);

// for input image array
var imagelist = [];

// Check imagelist when change
const checkImageList = () => {

	let html = "";
	for(let i in imagelist){
		html += "<dt class='uploaded_image'>" + imagelist[i].origin + "</dt><dd class='delete_image'><a>X</a></dd>";
	}
	$(".image_list").html(html);
	
};

// Remove Image
const removeImage = (imageName) => {
	
	let images = $(".note-editable").find("img");
	let imageSrc = imagelist.filter((element) => element.origin === imageName)[0].uploaded;
	
	for(let i = 0; i < images.length; i++){
		if(images.eq(i).attr("src").indexOf(imageSrc) != -1){
			images.eq(i).remove();
		}
	}
	
	imagelist = imagelist.filter((element) => element.origin !== imageName);
	checkImageList();
	
	if($(".image_preview").attr("src").indexOf(imageSrc) != -1){
		$(".image_preview").attr("src", "");
	}
};

// Details Upload to Server
const registBoard = () => {
	
	let result = false;
	let writer = "test_writer";
	let title = $(".title").val() + "";
	let content = $(".note-editable").html() + "";
	
	let board_data ={
		"board_writer" : writer,
		"board_title" : title,
		"board_content" : content,
		"board_type" : searchParams.get("btype")
	}
	$.ajax({
		url : '/board/registboard.do',
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

//Images upload to Server
const registImage = (bno) => {
	
	let result = false;
	
	let params = {
		"bno" : bno,
		"btype" : btype, 
		"imagelist" : imagelist 
	};
	
	$.ajax({
	    url: '/file/registimage.do',
	    method: 'post',
	    dataType : 'json',
	    async : false,
	    data: JSON.stringify(params),
	    contentType:'application/json',
	    success: (regimg) => {
			result = regimg > 0 ? true : false;
	    },
		error : function() {
			result = false;
		}
		
	});
	
	return result;
};

//
const checkImagesBeforeUpload = () => {

	let images = $(".note-editable").find("img");
	
	for(let i = 0; i < images.length; i++){
		
		let src = images.eq(i).attr("src");
		let isOurUrl = src.includes(imageServer);
		let isOnList = imagelist.filter((element) => element.uploaded === src.replace(imageServer, ""));
		//let isOnList = imagelist.indexOf(images.eq(i).attr("src").replace(imageServer, ""));
		
		if(isOurUrl == true && isOnList.length == 0){
			alert("첨부파일 목록에서 삭제된 이미지는 첨부할 수 없습니다.");
			return false;
		}

	}
	
	return true;
}

//Event Code Load
$(document).ready(function() {
	
	$('.summernote').summernote({
		height : 300,
		minHeight : 600,
		maxHeight : null,
		focus : true,
		lang : "ko-KR",
		callbacks : {
			onImageUpload : function(files) {
				imagelist.push.apply(upload.uploadImage(files, this));
				checkImageList();
			}
		}
	});
	
});

// 이미지 미리보기
$(document).on("click", ".uploaded_image", function(){
	let imageSrc = imagelist.filter((element) => element.origin === $(this).html())[0].uploaded;
	$(".image_preview").attr("src", imageServer + imageSrc);
});

// 이미지 삭제
$(document).on("click", ".delete_image", function(){
	let imageName = $(this).prevAll("dt.uploaded_image").html();
	removeImage(imageName);
});

// 게시글 업로드
$(document).on("click", ".upload_board", function(){
	let bno = checkImagesBeforeUpload() == true ? registBoard() : false;
	if(bno > 0){
		if(imagelist.length > 0){
			if(!registImage(bno)){
				alert("이미지 전송 실패");
				return;
			}
		}
		alert("게시글 등록 완료");
		location.replace("/board/list?btype=" + btype);
	}else{
		alert("게시글 전송 실패");
		return;
	}

});
