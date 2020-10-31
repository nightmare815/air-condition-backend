package com.bin.aircondition.service;

import com.bin.aircondition.entity.Conditioner;
import com.bin.aircondition.entity.MsgSend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bin.aircondition.vo.ConditionStatusVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
public interface MsgSendService extends IService<MsgSend> {

    //发布消息
    Boolean publishMessage(Conditioner conditioner, String sender);
}
