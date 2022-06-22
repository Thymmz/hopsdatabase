package com.walmart.hopsservice.service;

import com.walmart.hopsservice.dao.HopsDao;
import com.walmart.hopsservice.model.Data;
import com.walmart.hopsservice.model.Hops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HopsService {

    @Autowired
    private HopsDao hopsDao;

    public HopsService(HopsDao hopsDao) {
        this.hopsDao = hopsDao;
    }

    public Hops getHopsFromDB(String int_id, String int_cc, Integer int_inst, String intserid){
        return hopsDao.findByIntidAndIntccAndIntinstAndIntseridAndHopsequence(int_id, int_cc, int_inst, intserid);
    }
}
