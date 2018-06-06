
package com.airtel.kyccongob.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;
@Entity(tableName = DatabaseTableName.TABLE_TERRITORIES)
public class Territory implements Serializable {

    @PrimaryKey
    @ColumnInfo(name ="territoryId")
    private Integer territoryId;

    @ColumnInfo(name ="territoryName")
    private String territoryName;

    @ColumnInfo(name ="territoryZoneId")
    private Integer territoryZoneId;

    public Integer getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(Integer territoryId) {
        this.territoryId = territoryId;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }

    public Integer getTerritoryZoneId() {
        return territoryZoneId;
    }

    public void setTerritoryZoneId(Integer territoryZoneId) {
        this.territoryZoneId = territoryZoneId;
    }

}
