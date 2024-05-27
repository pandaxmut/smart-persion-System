package com.edu.smartpersionsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Older {
    private Integer olderId;//老人id
    private Integer userId;//用户id
    private String olderName;//名字
    private String olderPhone;//联系方式
    private String olderGender;//性别 1：男 ，2：女
    private String olderStatus;//1:"健康" ,2:"不健康"
    private String olderRoomNum;//房间号
    private String olderDescription;//老人背景信息描述
    private String olderPhoto;//老人头像地址
    private String date;
}
