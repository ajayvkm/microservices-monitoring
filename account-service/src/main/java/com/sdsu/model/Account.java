package com.sdsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Account {

    private Integer id;
    private String fullName;
    private String email;
    /*private Date createdDate;
    private String createdBy;*/
}
