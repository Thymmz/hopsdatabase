package com.walmart.hopsservice.dao;

import com.walmart.hopsservice.model.Hops;
import com.walmart.hopsservice.model.HopsInfo;
import com.walmart.hopsservice.model.hopsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HopsDao extends JpaRepository<List, hopsId> {
    Hops findByIntidAndIntccAndIntinstAndIntseridAndHopsequence(String int_id, String int_cc, Integer int_inst, String intserid);
}
