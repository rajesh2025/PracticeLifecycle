package com.airtel.kyccongob.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.airtel.kyccongob.db.DatabaseTableName;

@Entity(tableName = DatabaseTableName.TABLE_CITIES)
public class City {
    @PrimaryKey
    @ColumnInfo(name ="cityId")
    private long cityId;

    @ColumnInfo(name ="countryId")
    private long countryId;

    @ColumnInfo(name ="city")
    private String city;


    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
