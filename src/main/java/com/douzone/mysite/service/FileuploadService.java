package com.douzone.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileuploadService {
	// 저장
	private static final String SAVE_PATH = "/uploads";
	private static final String URL = "/uploads/images";
	
	public String restore(MultipartFile multipartFile) {
		String url = "";
		try {

			if (multipartFile.isEmpty()) {
				return url;
			}

			String origianlFileName = multipartFile.getOriginalFilename();
			// 확장자 분리 작업
			String extName = origianlFileName.substring(origianlFileName.lastIndexOf('.') + 1);
			String saveFileName = generateSaveFileName(extName);
			long filesize = multipartFile.getSize();

			System.out.println("##########" + origianlFileName);
			System.out.println("##########" + extName);
			System.out.println("##########" + saveFileName);
			System.out.println("##########" + filesize);

			byte[] fileData = multipartFile.getBytes();
			OutputStream os =  new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			
			url = URL + "/" + saveFileName;
			
		} catch (IOException ex) {
			new RuntimeException("upload fail");
		}
		return url;
	}

	private void writeFile(MultipartFile multipartFile, String saveFileName) {

	}

	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calender = Calendar.getInstance();

		fileName += calender.get(Calendar.YEAR);
		fileName += calender.get(Calendar.MONTH);
		fileName += calender.get(Calendar.DATE);
		fileName += calender.get(Calendar.HOUR);
		fileName += calender.get(Calendar.MINUTE);
		fileName += calender.get(Calendar.SECOND);
		fileName += calender.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}
}
