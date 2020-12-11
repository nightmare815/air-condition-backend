package com.bin.aircondition.service;

import com.bin.aircondition.entity.Conditioner;
import com.bin.aircondition.entity.MsgSend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bin.aircondition.vo.ConditionStatusVo;
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
public interface MsgSendService extends IService<MsgSend> {
    //发布消息
    Boolean publishMessage(Conditioner conditioner, String sender);
    //分页获取某个设备的历史修改记录
    Map<String, Object> getPageHistoryData(String deviceId, Long current, Long limit);

    Map<String, Object> getPageHistoryDataByCondition(String deviceId, Long current, Long limit, MessageQueryVo messageQueryVo);
}
