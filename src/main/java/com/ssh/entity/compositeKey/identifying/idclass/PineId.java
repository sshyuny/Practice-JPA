package com.ssh.entity.compositeKey.identifying.idclass;

import java.io.Serializable;

import lombok.Data;

@Data
public class PineId implements Serializable {
    
    private String pineId;
    private Tree tree;

}
