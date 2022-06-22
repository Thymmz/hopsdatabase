package com.walmart.hopsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


public interface HopsInfo {
    String hoplink();
    String hopname();
    String hopdest();
    String hopservice();
    String hopmeta();
}

