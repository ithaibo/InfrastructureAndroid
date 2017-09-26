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

    private long time;

    @Generated(hash = 1811755834)
    public User(Long id, String name, long time) {
        this.id = id;
        this.name = name;
        this.time = time;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
