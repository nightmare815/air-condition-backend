package com.bin.aircondition.vo;

import lombok.Data;

@Data
public class MessageQueryVo {
    private String begin;   //消息修改时间
    private String end;     //消息修改时间
    private String type;    //消息类型, 0定时, 1主动
    private String user;    //消息修改者

}
