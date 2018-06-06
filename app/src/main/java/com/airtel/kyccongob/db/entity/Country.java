package com.airtel.kyccongob.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.airtel.kyccongob.db.DatabaseTableName;

@Entity(tableName = DatabaseTableName.TABLE_COUNTRIES)
public class Country {
    @PrimaryKey
    @ColumnInfo(name = "countryId")
    private Integer countryId;

    @ColumnInfo(name ="countryName")
    private String countryName;

    @ColumnInfo(name ="isoCode")
    private String countryISOCode;

    @ColumnInfo(name ="countryNameSW")
    private String countryNameSW;

    @ColumnInfo(name ="nationality")
    private String nationality;

    @ColumnInfo(name ="isForeigner")
    private String isForeigner;

    @NonNull
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(@NonNull Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryISOCode() {
        return countryISOCode;
    }

    public void setCountryISOCode(String countryISOCode) {
        this.countryISOCode = countryISOCode;
    }

    public String getCountryNameSW() {
        return countryNameSW;
    }

    public void setCountryNameSW(String countryNameSW) {
        this.countryNameSW = countryNameSW;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIsForeigner() {
        return isForeigner;
    }

    public void setIsForeigner(String isForeigner) {
        this.isForeigner = isForeigner;
    }
}
