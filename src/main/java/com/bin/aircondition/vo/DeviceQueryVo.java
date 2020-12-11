package com.bin.aircondition.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceQueryVo {

    private String airport;

    private String station;

    private String bridge;

    private String deviceId;

    private String begin;

    private String end;
}
