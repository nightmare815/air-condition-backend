package com.bin.aircondition.vo;

import lombok.Data;

@Data
public class ConditionStatusVo {
        private String deviceId;        //消息发往设备的id
        private String temperature;	    //温度
        private String mode;	        //工作模式
        private String source;	        //电源开关
        private String wind;	        //风速
        private String weep;	        //扫风模式
        private String sleep;	        //睡眠
        private String power;	        //超强风
        private String light;	        //灯光
        private String health;	        //健康换气
        private String dry;	            //干燥
        private String showtemp;	    //是否显示温度
        private String save;	        //节能
        private String temp;	        //当前室温
        private String humi;	        //当前湿度
}
