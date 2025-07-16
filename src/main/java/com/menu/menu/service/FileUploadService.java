package com.menu.menu.service;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    /**
     * 初始化文件上传
     * @param userId 用户ID
     * @param fileName 文件名
     * @return 上传ID
     */
    String initializeUpload(String userId, String fileName);

    /**
     * 上传文件分片
     * @param uploadId 上传ID
     * @param chunkNumber 分片序号
     * @param chunkFile 分片文件
     */
    void uploadChunk(String uploadId, int chunkNumber, MultipartFile chunkFile);

    /**
     * 完成文件上传
     * @param uploadId 上传ID
     * @return 文件URL
     */
    String completeUpload(String uploadId);

    /**
     * 完成菜单图片上传并生成缩略图
     * @param uploadId 上传ID
     * @param thumbnailWidth 缩略图宽度
     * @param thumbnailHeight 缩略图高度
     * @return 包含原图URL和缩略图URL的Map
     */
    Map<String, String> completeMenuUpload(String uploadId, int thumbnailWidth, int thumbnailHeight);
}