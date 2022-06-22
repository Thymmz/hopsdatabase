package com.walmart.hopsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "hop")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(hopsId.class)
public class Hops {
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

    @Id
    @Column(name = "HOP_SEQ")
    private String hopsequence;

    @Column(name = "HOP_LINK")
    private String hoplink;

    @Column(name = "HOP_NAME")
    private String hopname;

    @Column(name = "HOP_DEST")
    private String hopdest;

    @Column(name = "HOP_SERVICE")
    private String hopservice;

    @Column(name = "HOP_META")
    private String hopmeta;
}
