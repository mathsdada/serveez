package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}, name = "uk_email_address"),
        @UniqueConstraint(columnNames = {"phone_num"}, name = "uk_phone_number")
})
public class User extends AuditModel {
    private long mId;
    private String mName;
    private String mEmail;
    private String mPhoneNum;

    public User() {
    }

    public User(long id, String name, String email, String phoneNum) {
        mId = id;
        mName = name;
        mEmail = email;
        mPhoneNum = phoneNum;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Column(name = "email")
    @JsonProperty("email")
    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    @Column(name = "phone_num", nullable = false)
    @JsonProperty("phone_num")
    public String getPhoneNum() {
        return mPhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        mPhoneNum = phoneNum;
    }
}
