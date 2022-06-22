package com.walmart.serviceservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class serviceId implements Serializable {
    private String intid;
    private String intcc;
    private Integer intinst;
    private String intserid;
}
