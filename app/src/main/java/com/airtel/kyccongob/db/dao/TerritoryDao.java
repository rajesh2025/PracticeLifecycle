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
import com.airtel.kyccongob.db.entity.Territory;

import java.util.List;

@Dao
public interface TerritoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerritory(Territory territory);

    @Delete
    void deleteTerritory(Territory territory);

    @Query("SELECT * from "+ DatabaseTableName.TABLE_TERRITORIES+" ORDER BY territoryName")
    LiveData<List<Territory>> getAllTerritories();
}
