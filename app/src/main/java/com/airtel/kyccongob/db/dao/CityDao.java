package com.airtel.kyccongob.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.airtel.kyccongob.db.entity.City;
import com.airtel.kyccongob.db.entity.Country;

import java.util.List;

@Dao
public interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(City city);

    @Delete
    void deleteCity(City city);

    @Query("SELECT * from "+ DatabaseTableName.TABLE_CITIES+" ORDER BY city")
    LiveData<List<City>> getAllCities();
}
