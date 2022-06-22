package com.walmart.hopsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class hopsId implements Serializable {
    private String intid;
    private String intcc;
    private Integer intinst;
    private String intserid;
    private String hopsequence;
}
