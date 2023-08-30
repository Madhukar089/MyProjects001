package com.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.contract.DocumentContract;
import com.contract.ObjectMapperContract;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DocumentService implements DocumentContract {

	private static final Logger LOGGER = Logger.getLogger(DocumentService.class);

	@Autowired
	private ObjectMapperContract customObjectMapperWrapper;

	public boolean uploadDocument(MultipartFile file, HttpServletRequest req) {
		byte[] bytes;
		try {
			bytes = file.getBytes();

			String path = req.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "views"
					+ File.separator + "Documents" + File.separator + file.getOriginalFilename();
			LOGGER.info("The path is : " + path);

			FileOutputStream fos = new FileOutputStream(path);
			fos.write(bytes);
			fos.close();
			LOGGER.info("File uploaded successfully");
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void getFile(String filename, HttpServletRequest request, HttpServletResponse response) {

		try {
			String filePath = request.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "views"
					+ File.separator + "Documents" + File.separator + filename;

			File file = new File(filePath);

			if (file.exists()) {
				String contentType = request.getServletContext().getMimeType(filename);
				response.setContentType(contentType);
				response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");

				InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
				OutputStream outputStream = response.getOutputStream();

				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outputStream.flush();
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public MultipartFile convertFileToMultipartFile(File file) throws IOException {

		LOGGER.info("The File is : " + file);

		FileInputStream fileInputStream = new FileInputStream(file);

		// Create a FileItem from the FileInputStream
		FileItem fileItem = new DiskFileItem("file", // Field name
				"application/octet-stream", // Content type
				false, // Whether to store in memory
				file.getName(), // File name
				(int) file.length(), // File size
				null // File repository (null for default)
		);

		LOGGER.info("File converted : " + fileItem);
		// Copy the file content to the FileItem
		try (InputStream inputStream = fileInputStream; OutputStream outputStream = fileItem.getOutputStream()) {
			IOUtils.copy(inputStream, outputStream);
		}
		LOGGER.info("File converted : " + fileItem);
		// Create a MultipartFile from the FileItem
		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
		LOGGER.info("File converted : " + multipartFile);
		return multipartFile;
	}

	public MultipartFile extractFile(MultipartHttpServletRequest request) {
		MultipartFile file = request.getFile("file");
		return file;
	}

	public <T> T extractModel(MultipartHttpServletRequest request, Class<T> type) throws JsonProcessingException {
		String dynamicFormModelJson = request.getParameter("dynamicFormModel");
		T t = customObjectMapperWrapper.readValue(dynamicFormModelJson, type);
		return t;
	}

}
