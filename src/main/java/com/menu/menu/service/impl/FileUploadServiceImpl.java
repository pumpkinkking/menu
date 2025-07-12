package com.menu.menu.service.impl;

import com.menu.menu.exception.BusinessException;
import com.menu.menu.service.FileUploadService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${app.upload.path}")
    private String uploadPath;

    @Override
    public String initializeUpload(Long userId, String fileName) {
        // 生成唯一上传ID
        String uploadId = UUID.randomUUID().toString();
        // 创建临时目录
        File tempDir = new File(uploadPath + File.separator + "temp" + File.separator + uploadId);
        if (!tempDir.exists() && !tempDir.mkdirs()) {
            throw new BusinessException("临时目录创建失败");
        }
        return uploadId;
    }

    @Override
    public void uploadChunk(String uploadId, int chunkNumber, MultipartFile chunkFile) {
        try {
            File chunk = new File(uploadPath + File.separator + "temp" + File.separator + uploadId + File.separator + chunkNumber);
            chunkFile.transferTo(chunk);
        } catch (IOException e) {
            throw new BusinessException("分片上传失败: " + e.getMessage());
        }
    }

    @Override
    public String completeUpload(String uploadId) {
        try {
            File tempDir = new File(uploadPath + File.separator + "temp" + File.separator + uploadId);
            File[] chunks = tempDir.listFiles();
            if (chunks == null || chunks.length == 0) {
                throw new BusinessException("分片文件不存在");
            }

            // 合并分片
            String fileName = System.currentTimeMillis() + ".jpg";
            File targetFile = new File(uploadPath + File.separator + fileName);
            try (java.io.FileOutputStream out = new java.io.FileOutputStream(targetFile)) {
                for (int i = 0; i < chunks.length; i++) {
                    File chunk = new File(tempDir.getPath() + File.separator + i);
                    try (java.io.FileInputStream in = new java.io.FileInputStream(chunk)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }
                    }
                    chunk.delete();
                }
            }
            tempDir.delete();

            // 压缩图片
            File compressedFile = new File(uploadPath + File.separator + "compressed_" + fileName);
            Thumbnails.of(targetFile)
                    .size(400, 400)
                    .outputQuality(0.8f)
                    .toFile(compressedFile);

            // 替换原文件
            targetFile.delete();
            compressedFile.renameTo(targetFile);

            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new BusinessException("文件合并失败: " + e.getMessage());
        }
    }

    @Override
    public Map<String, String> completeMenuUpload(String uploadId, int thumbnailWidth, int thumbnailHeight) {
        try {
            File tempDir = new File(uploadPath + File.separator + "temp" + File.separator + uploadId);
            File[] chunks = tempDir.listFiles();
            if (chunks == null || chunks.length == 0) {
                throw new BusinessException("分片文件不存在");
            }

            // 合并分片（原图）
            String originalFileName = System.currentTimeMillis() + "_original.jpg";
            File originalFile = new File(uploadPath + File.separator + originalFileName);
            try (java.io.FileOutputStream out = new java.io.FileOutputStream(originalFile)) {
                for (int i = 0; i < chunks.length; i++) {
                    File chunk = new File(tempDir.getPath() + File.separator + i);
                    try (java.io.FileInputStream in = new java.io.FileInputStream(chunk)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }
                    }
                    chunk.delete();
                }
            }
            tempDir.delete();

            // 生成缩略图
            String thumbnailFileName = System.currentTimeMillis() + "_thumbnail.jpg";
            File thumbnailFile = new File(uploadPath + File.separator + thumbnailFileName);
            Thumbnails.of(originalFile)
                    .size(thumbnailWidth, thumbnailHeight)
                    .outputQuality(0.8f)
                    .toFile(thumbnailFile);

            // 返回原图和缩略图URL
            Map<String, String> result = new HashMap<>();
            result.put("originalUrl", "/uploads/" + originalFileName);
            result.put("thumbnailUrl", "/uploads/" + thumbnailFileName);
            return result;
        } catch (IOException e) {
            throw new BusinessException("菜单图片处理失败: " + e.getMessage());
        }
    }
}