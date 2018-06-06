package com.airtel.kyccongob.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.airtel.kyccongob.db.entity.Country;
import com.airtel.kyccongob.db.entity.UserRole;
import com.airtel.kyccongob.db.entity.Zone;

import java.util.List;

@Dao
public interface ZoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserRole(Zone zone);

    @Delete
    void deleteUserRole(Zone zone);

    @Query("SELECT * from "+ DatabaseTableName.TABLE_ZONES+" ORDER BY zoneName")
    LiveData<List<Zone>> getAllZones();
}
