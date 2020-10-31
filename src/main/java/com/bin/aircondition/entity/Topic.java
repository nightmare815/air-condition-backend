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
@ApiModel(value="Topic对象", description="")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主题id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "队列名称")
    private String queueName;

    @ApiModelProperty(value = "topic添加者")
    private String addBy;

    @ApiModelProperty(value = "路由键名称")
    private String routingKey;

    @ApiModelProperty(value = "主题的类型, 0为用来发送消息的主题, 1为用来接收消息的主题")
    private Integer type;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "主题创建日期")
    private Date gmtCreate;

    @TableLogic
    @ApiModelProperty(value = "主题是否被删除,0未删除,1已删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "主题修改时间")
    private Date gmtModified;


}
