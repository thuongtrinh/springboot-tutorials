package com.txt.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.txt.dto.MyFile;

@Controller
public class BaseController {

	@GetMapping({ "/downUpFile", "/" })
	public String downloadFile(Model model) {
		model.addAttribute("myFile", new MyFile());
		return "home";
	}

	@RequestMapping(value = "/download1", method = RequestMethod.GET)
	public void downloadFile1(HttpServletResponse response, Model model) {
		try {
			File file = ResourceUtils.getFile("classpath:file/demo1.txt");
			byte[] data = FileUtils.readFileToByteArray(file);

			String fileName = file.getName();
			
			/*String extension = "";
			if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
				extension = fileName.substring(fileName.lastIndexOf(".") + 1);
			}
			System.out.println(extension);*/

			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setContentLength(data.length);

			InputStream is = new BufferedInputStream(new ByteArrayInputStream(data));
			FileCopyUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		/*
		try {
			File file = ResourceUtils.getFile("classpath:file/demo1.txt");
			byte[] data22 = FileUtils.readFileToByteArray(file);
			InputStream is = new BufferedInputStream(new ByteArrayInputStream(data22));
			FileOutputStream fileOS = new FileOutputStream("E:/file_name.txt");

			byte data[] = new byte[1024];
			int byteContent;
			while ((byteContent = is.read(data, 0, 1024)) != -1) {
				fileOS.write(data, 0, byteContent);
			}

			is.close();
			fileOS.flush();
		} catch (IOException e) { // handles IO
			e.printStackTrace();
		}*/
	}

	@GetMapping(value = "/download2")
	public ResponseEntity<InputStreamResource> downloadFile2(HttpServletRequest request, Model model) {
		HttpHeaders responseHeader = new HttpHeaders();
		try {
			File file = ResourceUtils.getFile("classpath:file/demo1.txt");
			byte[] data = FileUtils.readFileToByteArray(file);

			// set miniType return
			responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			responseHeader.set("Content-disposition", "attachment; filename=" + file.getName());
			responseHeader.setContentLength(data.length);

			InputStream is = new BufferedInputStream(new ByteArrayInputStream(data));
			InputStreamResource isr = new InputStreamResource(is);
			return new ResponseEntity<InputStreamResource>(isr, responseHeader, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<InputStreamResource>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/uploadFileDemo", method = RequestMethod.POST)
	public String uploadFile(Model model, MyFile myFile) {
		String path = "";
		try {
			MultipartFile multipartFile = myFile.getMultipartFile();
			String fileName = multipartFile.getOriginalFilename();
			File file = new File(this.getFolderUpload(), fileName);
			multipartFile.transferTo(file);

			path = file.getPath();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Upload failed");
		}

		model.addAttribute("message", "Upload success");
		model.addAttribute("description", myFile.getDescription() + ", directory: " + path);
		return "result";
	}

	private File getFolderUpload() {
		File folderUpload = new File(System.getProperty("user.home") + "/Uploads");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}

		return folderUpload;
	}
}
