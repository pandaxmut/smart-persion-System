package com.edu.smartpersionsys.service;

import com.edu.smartpersionsys.pojo.Outgoing;

import java.util.List;

public interface OutgoingService {
    List<Outgoing> getOutgoings();
    boolean Del(int id);
    boolean Add(Outgoing outgoing);
//    boolean Update(Outgoing outgoing);
}
