package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "House", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"block", "house_num", "society_id"}, name = "uk_block_house_num_society_id")
})
public class House {
    private Long mId;
    private String mBlock;
    private String mHouseNum;
    private Society mSociety;
    private Set<User> mUsers = new HashSet<>();

    public House() {
    }

    public House(long id, String block, String houseNum) {
        this.mId = id;
        mBlock = block;
        mHouseNum = houseNum;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty("id")
    public Long getId() {
        return mId;
    }

    @Column(name = "block", nullable = false)
    @JsonProperty("block")
    public String getBlock() {
        return mBlock;
    }

    @Column(name = "house_num", nullable = false)
    @JsonProperty("house_num")
    public String getHouseNum() {
        return mHouseNum;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "society_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Society getSociety() {
        return mSociety;
    }

    public void setId(Long id) {
        this.mId = id;
    }

    public void setBlock(String block) {
        mBlock = block;
    }

    public void setHouseNum(String houseNum) {
        mHouseNum = houseNum;
    }

    public void setSociety(Society society) {
        mSociety = society;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "houses")
    @JsonIgnore
    public Set<User> getUsers() {
        return mUsers;
    }

    public void addUser(User user) {
        mUsers.add(user);
    }

    public void removeUser(User user) {
        mUsers.remove(user);
    }

    public void setUsers(Set<User> users) {
        mUsers = users;
    }
}
