package com.bin.aircondition.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="MsgReceive对象", description="")
public class MsgReceive implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消息id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息是定时发送的, 还是认为人为设定的.0为定时发送, 1为认为设定的")
    private Integer type;

    @ApiModelProperty(value = "消息发送者,应为设备id")
    private String sender;

    @ApiModelProperty(value = "消息来自哪个路由")
    private String routingKey;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "消息接收时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "消息修改时间")
    private Date gmtModified;


}
