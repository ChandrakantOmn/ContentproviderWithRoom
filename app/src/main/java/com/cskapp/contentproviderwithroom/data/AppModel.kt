package com.cskapp.contentproviderwithroom.data

import android.content.ContentValues
import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Chandra Kant on 2/18/21.
 */
@Entity(tableName = AppModel.TABLE_NAME)
class AppModel{
    @JvmField
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    var id: Long = 0

    @JvmField
    @ColumnInfo(name = COLUMN_NAME)
    var appName:String?= null

    @JvmField
    @ColumnInfo(name = COLUMN_VERSION)
    var version:String?= null

    companion object {
        /** The name of the Cheese table.  */
        const val TABLE_NAME = "InstalledApp"

        /** The name of the ID column.  */
        const val COLUMN_ID = BaseColumns._ID

        /** The name of the name column.  */
        const val COLUMN_NAME = "name"

        const val COLUMN_VERSION = "version"

        @JvmStatic
        fun fromContentValues(values: ContentValues?): AppModel {
            val appModel = AppModel()
            if (values != null && values.containsKey(COLUMN_ID)) {
                appModel.id = values.getAsLong(COLUMN_ID)
            }
            if (values != null && values.containsKey(COLUMN_NAME)) {
                appModel.appName = values.getAsString(COLUMN_NAME)
            }
            if (values != null && values.containsKey(COLUMN_VERSION)) {
                appModel.version = values.getAsString(COLUMN_VERSION)
            }
            return appModel
        }

    }

}