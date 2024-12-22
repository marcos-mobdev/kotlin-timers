package br.com.appforge.kotlintimers.framework.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import java.security.Permissions

object PermissionManager{

    fun requestPostNotificationsPermission(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val notificationPermission = ActivityCompat.checkSelfPermission(context,android.Manifest.permission.POST_NOTIFICATIONS)
            if(notificationPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(context as Activity,arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),1)
            }
        }
    }

}