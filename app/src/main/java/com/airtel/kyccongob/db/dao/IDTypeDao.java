package com.airtel.kyccongob.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.airtel.kyccongob.db.entity.Country;
import com.airtel.kyccongob.db.entity.IDType;

import java.util.List;

@Dao
public interface IDTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIDType(IDType idType);

    @Delete
    void deleteIDType(IDType idType);

    @Query("SELECT * from "+ DatabaseTableName.TABLE_IDENTITIES+" ORDER BY idType")
    LiveData<List<IDType>> getAllIDTypes();
}
