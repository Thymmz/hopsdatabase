package com.walmart.configservice.dao;

import com.walmart.configservice.model.Config;
import com.walmart.configservice.model.configCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigDao extends JpaRepository<Config, configCompositeId> {
    Config findByIntidAndIntccAndIntinst(String INT_ID, String INT_CC, Integer INT_INST);
}
