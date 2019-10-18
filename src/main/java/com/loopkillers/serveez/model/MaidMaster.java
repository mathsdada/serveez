package com.loopkillers.serveez.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "maids_masters_book", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"maid_id"}, name = "uk_maid_id"),
        @UniqueConstraint(columnNames = {"maid_id", "master_id"}, name = "uk_master_id_maid_id")
})
public class MaidMaster extends AuditModel {
    private Long mId;
    private User mMaid;
    private User mMaster;

    public MaidMaster(User maid, User master) {
        mMaid = maid;
        mMaster = master;
    }

    public MaidMaster() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return mId;
    }

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "maid_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public User getMaid() {
        return mMaid;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "master_id", nullable = false, referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public User getMaster() {
        return mMaster;
    }

    public void setId(Long id) {
        mId = id;
    }

    public void setMaid(User maid) {
        mMaid = maid;
    }

    public void setMaster(User master) {
        mMaster = master;
    }
}
