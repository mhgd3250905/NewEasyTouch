package com.skkk.easytouch.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.skkk.easytouch.Configs;
import com.skkk.easytouch.R;

/**
 * 创建于 2018/3/4
 * 作者 admin
 */
/*
*
* 描    述：通知工具类
* 作    者：ksheng
* 时    间：2018/3/4$ 0:52$.
*/
public class NotificationUtils {
    /**
     * 发送通知
     *
     * @param context
     */
    public static void sendNotification(Context context) {
        //点击发送显示悬浮窗的广播
        Intent mainIntent = new Intent();
        mainIntent.setAction(Configs.ACTION_SHOW_FLOAT);
        PendingIntent mainPendingIntent = PendingIntent.getBroadcast(context, 0, mainIntent, PendingIntent.FLAG_ONE_SHOT);
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                //设置小图标
                .setSmallIcon(R.drawable.logo)
                //设置通知标题
                .setContentTitle("白开水笔记")
                //设置通知内容
                .setContentText("点击这里显示悬浮助手")
                //设置点击事件
                .setContentIntent(mainPendingIntent);

        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        //.setWhen(System.currentTimeMillis());
        //通过builder.build()方法生成Notification对象,并发送通知,id=1
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        notifyManager.notify(1, notification);
    }
}
