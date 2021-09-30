package com.example.frigeriofranco.pruebaAWSS3.service;


import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Repository
public interface AWSS3Service {

    String uploadFile(MultipartFile file);

    List<String> getFilesFromAWSS3();

    InputStream downloadFile(String key);
}
