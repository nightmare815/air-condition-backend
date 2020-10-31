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
@ApiModel(value="Gallery对象", description="")
public class Gallery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "廊道id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "廊道名称")
    private String name;

    @ApiModelProperty(value = "所属机场id")
    private String airportId;

    @ApiModelProperty(value = "廊道添加者")
    private String addBy;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "廊道添加时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "廊道修改时间")
    private Date gmtModified;

    @TableLogic
    @ApiModelProperty(value = "廊道是否被删除")
    private Boolean isDeleted;


}
