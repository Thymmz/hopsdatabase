package com.walmart.configservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(configCompositeId.class)
public class Config {
    @Id
    @Column(name = "INT_ID")
    private String intid;

    @Id
    @Column(name = "INT_CC")
    private String intcc;

    @Id
    @Column(name = "INT_INST")
    private Integer intinst;

    @Column(name = "INT_SERVICE_ID")
    private String intserviceid;
}
