package com.community.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.community.service.FileService;
import com.community.vo.FileVO;

@Controller
public class FileController {

	static Logger log = LoggerFactory.getLogger(FileController.class);
	private FileService fileservice;

	@Autowired
	public FileController(FileService fileservice) {
		this.fileservice = fileservice;
	}

	@RequestMapping(value = { "/file/uploadimage.do" }, method = RequestMethod.POST)
	@ResponseBody
	public List<String> uploadImage(@RequestParam("uploadFile") List<MultipartFile> imagelist) throws Exception {

		return fileservice.uploadImages(imagelist);

	}

	@RequestMapping(value = { "/file/registimage.do" }, method = RequestMethod.POST)
	@ResponseBody
	public int registImage(@RequestBody Map<String, Object> params) throws Exception {

		return fileservice.registImage(params);

	}

	@RequestMapping(value = "/imageview.do", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImageView(@RequestParam("id") String image_id) throws Exception {

		return fileservice.getImageView(image_id);
	}

	@RequestMapping(value = { "/file/getfilelist.do" }, method = RequestMethod.POST)
	@ResponseBody
	public List<FileVO> getFileList(@RequestParam("btype") String board_type, @RequestParam("bno") int board_no)
			throws Exception {

		return fileservice.getFileList(board_type, board_no);

	}
}
