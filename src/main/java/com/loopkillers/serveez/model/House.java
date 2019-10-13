package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "House", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"block", "house_num", "society_id"}, name = "uk_block_house_num_society_id")
})
public class House {
    private Long mId;
    private String mBlock;
    private String mHouseNum;
    private Society mSociety;

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
}
