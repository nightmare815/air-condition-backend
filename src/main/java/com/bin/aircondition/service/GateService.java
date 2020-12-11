package com.bin.aircondition.service;

import com.bin.aircondition.entity.Gate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bin.aircondition.vo.CommonQueryVo;
import com.bin.aircondition.vo.GateControlVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-11-11
 */
public interface GateService extends IService<Gate> {

    Map<String, Object> getPageGateStates(Long current, Long limit);

    Map<String, Object> getPageGateByCondition(Long current, Long limit, CommonQueryVo queryVo);

    Boolean updateGateStatus(GateControlVo gateControlVo, String sender);
}
