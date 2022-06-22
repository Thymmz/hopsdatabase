package com.walmart.configservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class configCompositeId implements Serializable {
    private String intid;
    private String intcc;
    private Integer intinst;

}
