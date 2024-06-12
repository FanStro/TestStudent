package com.itheima.controller;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: big-event
 * @description: FileUploadController
 * @author: 樊福蕰
 * @create: 2024-05-21 16:51
 **/
@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String basePath = "D:\\studentsss\\big_event\\src\\assets\\image";
        File dest = new File(basePath, filename);
        file.transferTo(dest);
        String fileUrl = getFileUrl(filename, basePath);
        return Result.success(fileUrl);
    }
    private String getFileUrl(String filename, String basePath) {
        // 返回文件的访问 URL，这可能是一个相对路径、绝对路径或完整的 URL
        // 注意：这只是一个示例，实际实现将取决于您的应用程序和文件存储方式
        return basePath+"\\" + filename; // 假设您有一个代理服务或 Web 服务器可以处理此路径
    }
}

