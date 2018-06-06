package com.airtel.kyccongob.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.airtel.kyccongob.db.entity.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountry(Country country);

    @Delete
    void deleteCountry(Country country);

    @Query("SELECT * from "+ DatabaseTableName.TABLE_COUNTRIES+" ORDER BY countryName")
    LiveData<List<Country>> getAllCountries();
}
