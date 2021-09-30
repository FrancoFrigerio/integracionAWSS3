package com.example.frigeriofranco.pruebaAWSS3.controller;


import com.example.frigeriofranco.pruebaAWSS3.service.AWSS3Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/storage")
public class PruebaController {

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;


    @Autowired
    private AWSS3Service awss3Service;


    @PostMapping(value = "/upload")
    public void uploadFile(@RequestPart(value = "file")MultipartFile file,HttpServletResponse response) throws IOException {
        String name = awss3Service.uploadFile(file);
        String message = "File: ".concat(file.getName()).concat("uploaded success");
        Map<String,String> body = new HashMap<>();
            body.put("Message","message");
            body.put("url",endpointUrl.concat("/").concat(bucketName).concat("/").concat(name));
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
    }

}
