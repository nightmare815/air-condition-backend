package com.bin.aircondition.service;

import com.bin.aircondition.entity.AirportBridge;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bin.aircondition.vo.CommonQueryVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bintian
 * @since 2020-11-02
 */
public interface AirportBridgeService extends IService<AirportBridge> {

    Map<String, Object> getPageAirportBridge(Long current, Long limit);

    Map<String, Object> getPageBridgeByCondition(Long current, Long limit, CommonQueryVo queryVo);
}
