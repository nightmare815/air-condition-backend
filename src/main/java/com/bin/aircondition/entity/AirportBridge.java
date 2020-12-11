package com.bin.aircondition.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

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
 * @since 2020-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AirportBridge对象", description="")
public class AirportBridge implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登机桥id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "登机桥名称")
    private String name;

    @ApiModelProperty(value = "登机桥所属机场id")
    private String airportId;

    @ApiModelProperty(value = "登机桥所有航站楼id")
    private String stationId;

    @ApiModelProperty(value = "登机桥添加者")
    private String addBy;

    @TableLogic
    @ApiModelProperty(value = "登机桥是否删除了")
    private Boolean isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改日期")
    private Date gmtModified;
}
