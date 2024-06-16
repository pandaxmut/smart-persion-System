package com.edu.smartpersionsys.pojo;

import lombok.Data;

@Data
public class Outgoing {
    private int id;
    private int olderId;
    private String startTime;
    private String endTime;
    private String reason;
    private String status;
    private Older older;

    public Outgoing(int id, int olderId, String startTime, String endTime, String reason,String status) {
        this.id = id;
        this.olderId = olderId;

        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.status = status;
    }

    public Outgoing() {
    }
}
