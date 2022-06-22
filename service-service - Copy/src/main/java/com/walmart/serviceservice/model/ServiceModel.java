package com.walmart.serviceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.List;

@Entity(name = "service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(serviceId.class)
public class ServiceModel {
    @Id
    @Column(name = "INT_ID")
    private String intid;

    @Id
    @Column(name = "INT_CC")
    private String intcc;

    @Id
    @Column(name = "INT_INST")
    private Integer intinst;

    @Id
    @Column(name = "INT_SERVICE_ID")
    private String intserid;

    @Column(name = "HOP_SEQ")
    private String hopsequence;
}

