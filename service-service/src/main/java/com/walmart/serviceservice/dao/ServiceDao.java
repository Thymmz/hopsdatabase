package com.walmart.serviceservice.dao;

import com.walmart.serviceservice.model.ServiceModel;
import com.walmart.serviceservice.model.serviceId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDao extends JpaRepository<ServiceModel, serviceId> {
    ServiceModel findByIntidAndIntccAndIntinstAndIntserid(String int_id, String int_cc, Integer int_inst, String intserid);
}
