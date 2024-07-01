const uploadImage = (files, editor) => {
	
	let resultlist = [];
	let formData = new FormData();
	
	for (let i = 0; i < files.length; i++) {
		formData.append("uploadFile", files[i]);
	}
	
	$.ajax({
		data : formData,
		type : "post",
		url : "/file/uploadimage.do",
		cache : false,
		contentType : false,
		processData : false,
		async : false,
		success : function(url) {
			for (let i = 0; i < url.length; i++) {
				$(editor).summernote('editor.insertImage', imageServer + url[i]);
				resultlist.push({
					"origin" : files[i].name,
					"uploaded" : url[i]
				});
			}
			
		}
	});
	
	return resultlist;
};