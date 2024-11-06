package com.ssh.entity.compositeKey.identifying.idclass;

import java.io.Serializable;

import lombok.Data;

@Data
public class TreeId implements Serializable {
    
    private Plant plant;
    private String treeId;

}
