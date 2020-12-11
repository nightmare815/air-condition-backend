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
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Gate对象", description="")
public class Gate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "卷帘门唯一id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "绑定的设备id")
    private String deviceId;

    @ApiModelProperty(value = "卷帘门名称")
    private String name;

    @ApiModelProperty(value = "门的开关状态,0关, 1开, 2正在开, 3正在关")
    private Integer gateStatus;

    @ApiModelProperty(value = "添加者")
    private String addBy;

    @TableLogic
    @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;


}
