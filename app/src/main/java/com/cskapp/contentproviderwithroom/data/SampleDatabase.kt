package com.cskapp.contentproviderwithroom.data

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * The Room database.
 */
@Database(entities = [AppModel::class], version = 2)
abstract class SampleDatabase : RoomDatabase() {
    abstract fun appModelDao(): AppModelDao

    private fun populateInitialData(context: Context) {
        val apps= AppUpdateHelper.getAppPackageInfo(context)
        if (appModelDao().count() == 0) {
            runInTransaction {
                for (app in apps) {
                   appModelDao().insert(app)
                }
            }
        }
    }

    companion object {
        /** The only instance  */
        private var sInstance: SampleDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): SampleDatabase? {
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context.applicationContext, SampleDatabase::class.java, "ex")
                        .build()
                sInstance!!.populateInitialData(context)
            }
            return sInstance
        }

        /**
         * Switches the internal implementation with an empty in-memory database.
         *
         * @param context The context.
         */
        @JvmStatic
        @VisibleForTesting
        fun switchToInMemory(context: Context) {
            sInstance = Room.inMemoryDatabaseBuilder(context.applicationContext,
                    SampleDatabase::class.java).build()
        }
    }
}