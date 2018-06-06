package com.airtel.kyccongob.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.airtel.kyccongob.BuildConfig;
import com.airtel.kyccongob.db.dao.CityDao;
import com.airtel.kyccongob.db.dao.CountryDao;
import com.airtel.kyccongob.db.dao.DepartmentDetailsDao;
import com.airtel.kyccongob.db.dao.IDTypeDao;
import com.airtel.kyccongob.db.dao.TerritoryDao;
import com.airtel.kyccongob.db.dao.UserRoleDao;
import com.airtel.kyccongob.db.dao.ZoneDao;
import com.airtel.kyccongob.db.entity.City;
import com.airtel.kyccongob.db.entity.Country;
import com.airtel.kyccongob.db.entity.DepartmentDetails;
import com.airtel.kyccongob.db.entity.IDType;
import com.airtel.kyccongob.db.entity.Territory;
import com.airtel.kyccongob.db.entity.UserRole;
import com.airtel.kyccongob.db.entity.Zone;

@Database(entities = {City.class, Country.class, DepartmentDetails.class,
        IDType.class, Territory.class, UserRole.class, Zone.class}, version = BuildConfig.DATABASE_VERSION,exportSchema=false)
public abstract class KYCDataBase extends RoomDatabase {

    public abstract CountryDao countryDao();
    public abstract CityDao cityDao();
    public abstract DepartmentDetailsDao departmentDetailsDao();
    public abstract IDTypeDao idTypeDao();
    public abstract TerritoryDao territoryDao();
    public abstract UserRoleDao userRoleDao();
    public abstract ZoneDao zoneDao();

    private static KYCDataBase INSTANCE;


    static KYCDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (KYCDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            KYCDataBase.class, BuildConfig.DATABASE_NAME)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
