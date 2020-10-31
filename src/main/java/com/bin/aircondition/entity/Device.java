package com.bin.aircondition.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Device对象", description="")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "注册设备的id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "设备出厂自带id")
    private String deviceId;

    @ApiModelProperty(value = "设备型号")
    private String type;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "设备添加者")
    private String addBy;

    @ApiModelProperty(value = "设备绑定的路由")
    private String routingKey;

    @ApiModelProperty(value = "设备所属机场")
    private String airportId;

    @ApiModelProperty(value = "设备所属廊道")
    private String galleryId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "设备注册时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "设备修改时间")
    private Date gmtModified;

    @TableLogic
    @ApiModelProperty(value = "设备是否已被删除0未删除,1已删除")
    private Integer isDeleted;


}
