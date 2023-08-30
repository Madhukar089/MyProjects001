package com.contract;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface DocumentContract {

	public boolean uploadDocument(MultipartFile multipartFile, HttpServletRequest req);

	public void getFile(String filename, HttpServletRequest request, HttpServletResponse response);

	public MultipartFile convertFileToMultipartFile(File file) throws IOException;

	public MultipartFile extractFile(MultipartHttpServletRequest request);

	public <T> T extractModel(MultipartHttpServletRequest request, Class<T> type) throws JsonProcessingException;

}
