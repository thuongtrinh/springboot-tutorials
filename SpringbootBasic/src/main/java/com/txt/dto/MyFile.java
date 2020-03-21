package com.txt.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class MyFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private MultipartFile multipartFile;
	private String description;

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
