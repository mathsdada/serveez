package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private UserType mUserType;
    private Set<House> mHouses = new HashSet<>();
    private Set<MaidTimeSlot> mTimeSlot = new HashSet<>();

    public User() {
    }

    public User(long id, String name, String email, String phoneNum, UserType userType) {
        mId = id;
        mName = name;
        mEmail = email;
        mPhoneNum = phoneNum;
        mUserType = userType;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_house",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "house_id")})
    @JsonIgnore
    public Set<House> getHouses() {
        return mHouses;
    }

    public void addHouse(House house) {
        mHouses.add(house);
        house.addUser(this);
    }

    public void removeHouse(House house) {
        mHouses.remove(house);
        house.removeUser(this);
    }

    public void setHouses(Set<House> houses) {
        mHouses = houses;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    @JsonProperty("user_type")
    public UserType getUserType() {
        return mUserType;
    }

    public void setUserType(UserType userType) {
        mUserType = userType;
    }

    @OneToMany(mappedBy = "timeSlot")
    public Set<MaidTimeSlot> getTimeSlot() {
        return mTimeSlot;
    }

    public void setTimeSlot(Set<MaidTimeSlot> timeSlot) {
        mTimeSlot = timeSlot;
    }
}
