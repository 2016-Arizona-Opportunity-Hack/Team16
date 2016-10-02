package org.mdn.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
@PropertySource("classpath:application.properties")
public class FileUploadController {

	@Autowired
	Environment env;

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {

		name = System.nanoTime() + name;
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = env.getProperty("rootdir");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				return new ResponseEntity<String>("Success", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<String>("Error: No File", HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/get/{filename}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(HttpServletResponse response, @PathVariable("filename") String name)
			throws IOException {

		String rootPath = env.getProperty("rootdir");
		File dir = new File(rootPath + File.separator + "tmpFiles");

		File file = new File(dir.getAbsolutePath() + File.separator + name);

		if (file.exists()) {
			byte[] content = org.apache.commons.io.FileUtils.readFileToByteArray(file);
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());

			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			MediaType type = MediaType.valueOf(mimeType);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(type);
			headers.setContentLength((int) file.length());
			return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
