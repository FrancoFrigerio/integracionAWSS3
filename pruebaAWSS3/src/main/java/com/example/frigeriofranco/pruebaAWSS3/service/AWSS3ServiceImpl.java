package com.example.frigeriofranco.pruebaAWSS3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Service
public class AWSS3ServiceImpl implements AWSS3Service{

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;


    @Override
    public String uploadFile(MultipartFile file) {
     File mainFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName= "";
        try {
            FileOutputStream stream = new FileOutputStream(mainFile);
                stream.write(file.getBytes());
                fileName = System.currentTimeMillis()+"-"+mainFile.getName();
            PutObjectRequest request = new PutObjectRequest(bucketName,fileName,mainFile);
            amazonS3.putObject(request);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return fileName;
    }

    @Override
    public List<String> getFilesFromAWSS3() {
        return null;
    }

    @Override
    public InputStream downloadFile(String key) {
        return null;
    }
}
