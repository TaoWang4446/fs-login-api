package com.jmdz.fushan.model.entity;

/**
 * RecordFile
 *
 * @author LiCongLu
 * @date 2020-06-12 15:12
 */
public class RecordFile {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 业务编号
     */
    private String operationNo;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小
     */
    private long fileLength;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 文件标记
     */
    private Integer fileCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 账号UserId
     */
    private String userId;

    /**
     * 更新时间
     */
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public RecordFile setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getOperationNo() {
        return operationNo;
    }

    public RecordFile setOperationNo(String operationNo) {
        this.operationNo = operationNo;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public RecordFile setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public RecordFile setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public long getFileLength() {
        return fileLength;
    }

    public RecordFile setFileLength(long fileLength) {
        this.fileLength = fileLength;
        return this;
    }

    public Integer getFileType() {
        return fileType;
    }

    public RecordFile setFileType(Integer fileType) {
        this.fileType = fileType;
        return this;
    }

    public Integer getFileCode() {
        return fileCode;
    }

    public RecordFile setFileCode(Integer fileCode) {
        this.fileCode = fileCode;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public RecordFile setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public RecordFile setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public RecordFile setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
