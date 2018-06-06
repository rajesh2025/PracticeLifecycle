
package com.airtel.kyccongob.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@Entity(tableName = DatabaseTableName.TABLE_ZONES)
public class Zone {

    @PrimaryKey
    @ColumnInfo(name ="zoneId")
    private Integer zoneId;

    @ColumnInfo(name ="zoneName")
    private String zoneName;

    @Ignore
    private List<Territory> territory = null;

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public List<Territory> getTerritory() {
        return territory;
    }

    public void setTerritory(List<Territory> territory) {
        this.territory = territory;
    }

}
