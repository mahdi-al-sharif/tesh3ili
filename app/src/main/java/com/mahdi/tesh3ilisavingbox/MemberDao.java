package com.mahdi.tesh3ilisavingbox;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MemberDao {

    @Insert
    public void insertMember(Member member);

    @Update
    public void updateMember(Member member);

    @Delete
    public void deleteMember(Member member);

    @Query("DELETE FROM member_table")
    public void deleteAllMembers();

    @Query("SELECT * FROM member_table ORDER BY memberName")
    LiveData<List<Member>> getAllMembers();

}
