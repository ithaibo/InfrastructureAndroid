package com.andy.infrastructure.demos.db.note;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Andy on 2017/5/16.
 */

@Entity
public class User {
    @Id
    private Long id;

    private String name;

    @Transient
    private int tempUserageCount;

    @Generated(hash = 873297011)
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTempUserageCount() {
        return tempUserageCount;
    }

    public void setTempUserageCount(int tempUserageCount) {
        this.tempUserageCount = tempUserageCount;
    }
}
