package com.mahdi.tesh3ilisavingbox;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "member_table")
public class Member {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String memberName;

    public Member(String memberName) {
        this.memberName = memberName;
    }

    public int getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setId(int id) {
        this.id = id;
    }
}
