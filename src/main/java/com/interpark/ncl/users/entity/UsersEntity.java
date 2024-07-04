package com.interpark.ncl.users.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name="USERS")
public class UsersEntity {

    @Id
    @Column (name="USER_NO")
    private String userNo;

    @Column (name="GENDER")
    private String gender;

    @Column (name="AGE")
    private Integer age;

}
