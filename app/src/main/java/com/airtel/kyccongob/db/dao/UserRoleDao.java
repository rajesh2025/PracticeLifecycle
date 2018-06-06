package com.airtel.kyccongob.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.airtel.kyccongob.db.DatabaseTableName;
import com.airtel.kyccongob.db.entity.Country;
import com.airtel.kyccongob.db.entity.Territory;
import com.airtel.kyccongob.db.entity.UserRole;

import java.util.List;

@Dao
public interface UserRoleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserRole(UserRole userRole);

    @Delete
    void deleteUserRole(UserRole userRole);

    @Query("SELECT * from "+ DatabaseTableName.TABLE_USER_ROLES+" ORDER BY userRoleName")
    LiveData<List<UserRole>> getAllUserRoles();
}
