package com.airtel.kyccongob.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = DatabaseTableName.TABLE_USER_ROLES)
public class UserRole {

    @ColumnInfo(name ="version")
    private String version;

    @PrimaryKey
    @ColumnInfo(name ="roleId")
    private Integer roleId;

    @ColumnInfo(name ="roleName")
    private String roleName;

    @ColumnInfo(name ="userRoleName")
    private String userRoleName;

    @ColumnInfo(name ="description")
    private String description;

    @Ignore
    private List<UserRole> subRolesList;

    @ColumnInfo(name="parentRoleId")
    private Integer parentRoleId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserRole> getSubRolesList() {
        return subRolesList;
    }

    public void setSubRolesList(List<UserRole> subRolesList) {
        this.subRolesList = subRolesList;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(Integer parentRoleId) {
        this.parentRoleId = parentRoleId;
    }
}
