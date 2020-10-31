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
@ApiModel(value="Airport对象", description="")
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机场id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "机场名称")
    private String name;

    @ApiModelProperty(value = "机场添加者")
    private String addBy;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "机场添加时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "机场修改时间")
    private Date gmtModified;

    @TableLogic
    @ApiModelProperty(value = "机场是否被删除,0未删除,1已删除")
    private Integer isDeleted;


}
