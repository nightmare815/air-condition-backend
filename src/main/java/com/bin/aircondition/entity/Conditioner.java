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
@ApiModel(value="Condition对象", description="")
public class Conditioner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "空调id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "空调名称")
    private String name;

    @ApiModelProperty(value = "对应设备id")
    private String deviceId;

    @ApiModelProperty(value = "空调添加者")
    private String addBy;

    @ApiModelProperty(value = "当前温度")
    private String temperature;	    //温度

    @ApiModelProperty(value = "工作模式,1自动,2制冷,3除湿,4送风,5制热")
    private String mode;	        //工作模式

    @ApiModelProperty(value = "电源,0关,1开")
    private String source;	        //电源开关

    @ApiModelProperty(value = "风速,0低速,1中速,2高速,3自动")
    private String wind;	        //风速

    @ApiModelProperty(value = "扫风模式,0上下扫风,1左右扫风,2上下左右扫风,3无扫风")
    private String weep;	        //扫风模式

    @ApiModelProperty(value = "睡眠模式,0关,1开")
    private String sleep;	        //睡眠

    @ApiModelProperty(value = "超强风模式,0关,1开")
    private String power;	        //超强风

    @ApiModelProperty(value = "是否开启灯光,0关闭,1开启")
    private String light;	        //灯光

    @ApiModelProperty(value = "健康换气,0健康,1换气,2健康换气,3无")
    private String health;	        //健康换气

    @ApiModelProperty(value = "是否开启干燥0关,1开")
    private String dry;	            //干燥

    @ApiModelProperty(value = "是否显示温度,0关,1开")
    private String showtemp;	    //是否显示温度

    @ApiModelProperty(value = "节能,0关,1开")
    private String save;	        //节能

    @ApiModelProperty(value = "当前室温")
    private String temp;	        //当前室温

    @ApiModelProperty(value = "湿度")
    private String humi;	        //当前湿度

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "空调添加时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "空调修改时间")
    private Date gmtModified;

    @TableLogic
    @ApiModelProperty(value = "空调是否被删除")
    private Integer isDelete;


}
