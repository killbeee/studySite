package com.myProject.myPj.common.file.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.myProject.myPj.common.file.service.FileService;
import com.myProject.myPj.user.UserService;
import com.myProject.myPj.vo.FileVo;

@Controller
public class FileController {
    @Autowired
	UserService userService;
    @Autowired
   	FileService fileService;
    @Value("${custom.imgPath}")
 	 private String fileStorePath;
    @PostMapping("/summerNoteImg")
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
        	String uploadFileId = fileService.store(file,File.separator+"summerNoteImg");
            return ResponseEntity.ok().body("/outSource/summerNoteImg/" + uploadFileId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/summerNoteImg/{fileId}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable String fileId) {
        try {
            FileVo uploadedFile = fileService.selectSummerNoteSave(fileId);
            HttpHeaders headers = new HttpHeaders();
            
            
            String fileName = uploadedFile.getTransFileName();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            String sentence = fileId;
            String[] parts = sentence.split("\\.");
            String fileType = "image/"+parts[parts.length-1];
            headers.setContentType(MediaType.valueOf(fileType));

            
            return ResponseEntity.ok().headers(headers).body("/outSource/summerNoteImg/" + fileName);
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

}
