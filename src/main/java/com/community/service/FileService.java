package com.community.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.community.mapper.FileMapper;
import com.community.vo.FileVO;

@Service
public class FileService {

	private String resourcesPath = "C:\\Community\\resuorces\\";
	private String imagePath = resourcesPath + "images\\";
	private String defaultImage = imagePath + "default.png";

	private FileMapper filemapper;

	@Autowired
	public FileService(FileMapper filemapper) {
		this.filemapper = filemapper;
	}

	public List<String> uploadImages(List<MultipartFile> imagelist) throws Exception {

		UUID uuid;
		String fileType, fileName;
		List<String> tempimagelist = new ArrayList<>();

		if (Files.notExists(Paths.get(imagePath))) {
			Files.createDirectories(Paths.get(imagePath));
		}

		for (int i = 0; i < imagelist.size(); i++) {
			uuid = UUID.randomUUID();
			fileType = FilenameUtils.getExtension(imagelist.get(i).getOriginalFilename());
			fileName = uuid + "." + fileType;
			tempimagelist.add(i, fileName);
			imagelist.get(i).transferTo(new File(imagePath + fileName));
		}

		return tempimagelist;
	}

	@SuppressWarnings("unchecked")
	public int registImage(Map<String, Object> params) throws Exception {
		int board_no = (int) params.get("bno");
		String board_type = (String) params.get("btype");
		List<Map<String, String>> imagelist = (List<Map<String, String>>) params.get("imagelist");

		return filemapper.registImage(board_no, board_type, imagelist);
	}

	public byte[] getImageView(String image_id) throws IOException {

		String imageUrl = imagePath + image_id;
		Path getPath = Paths.get(imageUrl);

		return IOUtils
				.toByteArray(Files.exists(getPath) ? new FileInputStream(imageUrl) : new FileInputStream(defaultImage));
	}

	public List<FileVO> getFileList(String board_type, int board_no) throws Exception {

		return filemapper.getFileList(board_type, board_no);
	}
}
