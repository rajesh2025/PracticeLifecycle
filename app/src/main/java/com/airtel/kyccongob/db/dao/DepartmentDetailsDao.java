package com.airtel.kyccongob.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.airtel.kyccongob.db.entity.Country;
import com.airtel.kyccongob.db.entity.DepartmentDetails;

import java.util.List;

@Dao
public interface DepartmentDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDepartment(DepartmentDetails departmentDetails);

    @Delete
    void deleteDepartment(DepartmentDetails departmentDetails);

    @Query("SELECT * from "+ DatabaseTableName.TABLE_DEPARTMENTS+" ORDER BY departmentName")
    LiveData<List<DepartmentDetails>> getAllDepartmentDetails();
}
