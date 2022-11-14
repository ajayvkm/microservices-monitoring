package com.sdsu.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "createdby")
    private String createdBy;

    public Account(String fullName, String email, Date createdDate, String createdBy) {
        this.fullName = fullName;
        this.email = email;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }
}
