package com.airtel.kyccongob.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.airtel.kyccongob.db.DatabaseTableName;


@Entity(tableName = DatabaseTableName.TABLE_DEPARTMENTS)
public class DepartmentDetails{

    @PrimaryKey
    @ColumnInfo(name ="departmentID")
    private Integer departmentID;

    @ColumnInfo(name ="departmentName")
    private String departmentName;

    @ColumnInfo(name ="departmentDescription")
    private String departmentDescription;


    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
