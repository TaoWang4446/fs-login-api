package com.jmdz.fushan.pad.model.farewell;

import com.jmdz.fushan.pad.model.SignImageItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * 告别任务服务详情信息
 *
 * @author LiCongLu
 * @date 2020-07-15 16:21
 */
@ApiModel(value = "告别任务服务详情信息", description = "告别任务服务详情信息")
public class FarewellTaskServiceItem extends FarewellTaskItem {

    /**
     * 礼仪服务
     */
    @ApiModelProperty(value = "礼仪服务", name = "riteService", position = 13)
    private String riteService;

    /**
     * 鲜花服务
     */
    @ApiModelProperty(value = "鲜花服务", name = "flowerService", position = 14)
    private String flowerService;

    /**
     * 整容服务
     */
    @ApiModelProperty(value = "整容服务", name = "faceLiftService", position = 15)
    private String faceLiftService;

    /**
     * 物品服务
     */
    @ApiModelProperty(value = "物品服务", name = "productService", position = 16)
    private String productService;

    /**
     * 签字图片信息
     */
    @ApiModelProperty(value = "签字图片信息", name = "signImageItem", position = 17)
    private SignImageItem signImageItem;


    public String getRiteService() {
        return riteService;
    }

    public FarewellTaskServiceItem setRiteService(String riteService) {
        this.riteService = riteService;
        return this;
    }

    public String getFlowerService() {
        return flowerService;
    }

    public FarewellTaskServiceItem setFlowerService(String flowerService) {
        this.flowerService = flowerService;
        return this;
    }

    public String getFaceLiftService() {
        return faceLiftService;
    }

    public FarewellTaskServiceItem setFaceLiftService(String faceLiftService) {
        this.faceLiftService = faceLiftService;
        return this;
    }

    public String getProductService() {
        return productService;
    }

    public FarewellTaskServiceItem setProductService(String productService) {
        this.productService = productService;
        return this;
    }

    public SignImageItem getSignImageItem() {
        return signImageItem;
    }

    public FarewellTaskServiceItem setSignImageItem(SignImageItem signImageItem) {
        this.signImageItem = signImageItem;
        return this;
    }
}
