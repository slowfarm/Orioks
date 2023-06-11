package ru.eva.oriokslive.utils

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.ForegroundInfo
import ru.eva.oriokslive.R
import ru.eva.oriokslive.ui.activity.main.MainActivity

private const val GRADE_UPDATE_CHANNEL_ID = "GRADE_UPDATE_CHANNEL_ID"
private const val DISCIPLINES_UPDATED_CHANNEL_ID = "DISCIPLINES_UPDATED_CHANNEL_ID"
private const val DEBT_UPDATED_CHANNEL_ID = "DEBT_UPDATED_CHANNEL_ID"

private const val GRADE_UPDATE_ID = 1
private const val DISCIPLINES_UPDATED_ID = 2
private const val DEBT_UPDATED_ID = 3

fun Context.checkNotificationPermission(callback: (String) -> Unit) {
    if (SDK_INT >= TIRAMISU) {
        val permission = POST_NOTIFICATIONS
        if (checkSelfPermission(this, permission) != PERMISSION_GRANTED) callback.invoke(permission)
    }
}

fun showDisciplinesUpdatedNotification(context: Context, text: String) {
    val channelId = createNotificationChannelId(context, DISCIPLINES_UPDATED_CHANNEL_ID)
    val pendingIntent = NavDeepLinkBuilder(context)
        .setComponentName(MainActivity::class.java)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.nav_main)
        .createPendingIntent()
    val notification = NotificationCompat.Builder(context, channelId)
        .setContentIntent(pendingIntent)
        .setContentTitle(context.getString(R.string.grade_changed))
        .setStyle(NotificationCompat.BigTextStyle().bigText(text))
        .setSmallIcon(R.drawable.ic_menu_home)
        .build()
        .apply { flags = Notification.FLAG_AUTO_CANCEL }

    val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(DISCIPLINES_UPDATED_ID, notification)
}

fun showDebtUpdatedNotification(context: Context, text: String) {
    val channelId = createNotificationChannelId(context, DEBT_UPDATED_CHANNEL_ID)
    val pendingIntent = NavDeepLinkBuilder(context)
        .setComponentName(MainActivity::class.java)
        .setGraph(R.navigation.nav_graph)
        .setDestination(R.id.nav_debts)
        .createPendingIntent()
    val notification = NotificationCompat.Builder(context, channelId)
        .setContentIntent(pendingIntent)
        .setContentTitle(context.getString(R.string.debts_changed))
        .setStyle(NotificationCompat.BigTextStyle().bigText(text))
        .setSmallIcon(R.drawable.ic_menu_debts)
        .build()
        .apply { flags = Notification.FLAG_AUTO_CANCEL }

    val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.notify(DEBT_UPDATED_ID, notification)
}

fun createGradeUpdateForegroundInfo(context: Context): ForegroundInfo {
    val name = context.getString(R.string.grade_update)
    val channelId = createNotificationChannelId(context, GRADE_UPDATE_CHANNEL_ID)
    val notification = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_update)
        .setContentTitle(name)
        .setContentText(name)
        .build()

    return ForegroundInfo(GRADE_UPDATE_ID, notification)
}

private fun createNotificationChannelId(context: Context, channelId: String): String {
    val name = context.getString(R.string.grade_update)
    val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    var channel = notificationManager.getNotificationChannel(channelId)
    if (channel == null) {
        channel = NotificationChannel(channelId, name, IMPORTANCE_LOW)
        notificationManager.createNotificationChannel(channel)
    }
    return channel.id
}
