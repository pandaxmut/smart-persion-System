package com.edu.smartpersionsys.service;

import com.edu.smartpersionsys.mapper.OutgoingMapper;
import com.edu.smartpersionsys.pojo.Outgoing;
import com.edu.smartpersionsys.service.OutgoingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutgoingServiceImpl implements OutgoingService {
    @Autowired
    OutgoingMapper outgoingMapper;
    @Override
    public List<Outgoing> getOutgoings() {

       return outgoingMapper.getOutgoings();
    }
    public boolean Del(int id){
        int i =outgoingMapper.del(id);
        if(i>0)
            return true;
        else
            return false;
    }
    @Override
    public boolean Add(Outgoing outgoing){
        int i=outgoingMapper.insert(outgoing);
        if(i>0)
            return true;
        else
            return false;
    }
//    @Override
//    public boolean Update(Outgoing outgoing){
//        int i=outgoingMapper.Update(outgoing);
//        if (i>0)
//            return true;
//        else
//            return false;
//    }
}
