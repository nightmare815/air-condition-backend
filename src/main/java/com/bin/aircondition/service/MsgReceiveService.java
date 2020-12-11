package com.bin.aircondition.service;

import com.bin.aircondition.entity.MsgReceive;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bin.aircondition.vo.MessageQueryVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-10-27
 */
public interface MsgReceiveService extends IService<MsgReceive> {
    //分页获取收到的历史消息
    Map<String, Object> getPageReceiveMsg(String deviceId, Long current, Long limit);

    Map<String, Object> getPageReceiveMsgByCondition(String deviceId, Long current, Long limit, MessageQueryVo messageQueryVo);
}
