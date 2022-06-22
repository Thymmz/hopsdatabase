package com.walmart.mainservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hops {
    private String intid;

    private String intcc;

    private Integer intinst;

    private String intserid;

    private String hopsequence;

    private String hoplink;

    private String hopname;

    private String hopdest;

    private String hopservice;

    private String hopmeta;
}
