package com.cskapp.contentproviderwithroom.data


import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

object AppUpdateHelper {

    private const val COM_VERSION = "com.verizon"
    fun getAppPackageInfo(context: Context): ArrayList<AppModel> {
        return ArrayList<AppModel>().apply {
            val packageManager = context.packageManager
            val installedApps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            for(app in installedApps) {
                val appModel= AppModel()
                appModel.apply {
                    appName= getAppsName(app, packageManager)
                    version= getAppVersion(app,packageManager)
                }
                add(appModel)
            }
        }
    }

    private fun getAppVersion(app: ApplicationInfo, packageManager: PackageManager): String? {
      return  packageManager.getPackageInfo(app.packageName, 0)?.versionName
    }

    private fun getAppsName(app: ApplicationInfo?, packageManager: PackageManager?): String? {
        return try {
            if (app != null) {
                packageManager?.getApplicationLabel(app).toString()
            }else null

        } catch (e:Exception ) {
            app?.name
        }
    }
}