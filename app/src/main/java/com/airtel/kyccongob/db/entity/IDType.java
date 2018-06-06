package com.airtel.kyccongob.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = DatabaseTableName.TABLE_IDENTITIES)
public class IDType {

    @PrimaryKey
    @ColumnInfo(name ="masterId")
    private int masterId;


    @ColumnInfo(name ="idName")
    private String idName;


    @ColumnInfo(name ="idType")
    private int idType;


    @ColumnInfo(name ="isAm")
    private int isAm;


    @ColumnInfo(name ="isGsm")
    private int isGsm;


    @ColumnInfo(name ="isMandatory")
    private int isMandatory;


    @ColumnInfo(name ="idDesc")
    private String idDesc;

    public String getIDTypeFront() {
        return idName + "_FRONT";
    }

    public String getIDTypeBack() {
        return idName + "_BACK";
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIsAm() {
        return isAm;
    }

    public void setIsAm(int isAm) {
        this.isAm = isAm;
    }

    public int getIsGsm() {
        return isGsm;
    }

    public void setIsGsm(int isGsm) {
        this.isGsm = isGsm;
    }

    public int getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(int isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getIdDesc() {
        return idDesc;
    }

    public void setIdDesc(String idDesc) {
        this.idDesc = idDesc;
    }
}
