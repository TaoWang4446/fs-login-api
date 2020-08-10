package com.jmdz.fushan.pad.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author LiCongLu
 * @date 2020-07-14 13:40
 */
@ApiModel(value = "签字图片信息", description = "签字图片信息")
public class SignImageItem {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id", position = 1)
    private int id;

    /**
     * 图片文件名称
     */
    @ApiModelProperty(value = "图片文件名称", name = "imageName", position = 2)
    private String imageName;

    /**
     * 图片保存路径
     */
    @ApiModelProperty(value = "图片保存路径", name = "imagePath", position = 3)
    private String imagePath;

    /**
     * 缩略图文件路径
     */
    @ApiModelProperty(value = "缩略图文件路径", name = "thumbPath", position = 4)
    private String thumbPath;

    public int getId() {
        return id;
    }

    public SignImageItem setId(int id) {
        this.id = id;
        return this;
    }

    public String getImageName() {
        return imageName;
    }

    public SignImageItem setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public SignImageItem setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public SignImageItem setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
        return this;
    }
}
