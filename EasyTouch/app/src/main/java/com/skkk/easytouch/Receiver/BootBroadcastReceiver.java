package com.skkk.easytouch.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.skkk.easytouch.Configs;
import com.skkk.easytouch.Services.EasyTouchBallService;
import com.skkk.easytouch.Services.EasyTouchLinearService;
import com.skkk.easytouch.Utils.SpUtils;

/**
 * 创建于 2018/3/19
 * 作者 admin
 */
/*
* 
* 描    述：
* 作    者：ksheng
* 时    间：2018/3/19$ 22:33$.
*/
public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (SpUtils.getBoolean(context.getApplicationContext(), Configs.KEY_BOOT_START,false)) {
            Intent appIntent = new Intent();
            int touchType=SpUtils.getInt(context.getApplicationContext(),Configs.KEY_TOUCH_TYPE,0);
            if (touchType == Configs.TouchType.LINEAR.getValue()) {
                appIntent.setClass(context, EasyTouchLinearService.class);
            }else if (touchType == Configs.TouchType.BALL.getValue()){
                appIntent.setClass(context, EasyTouchBallService.class);
            }
            context.startService(appIntent);
        }
    }
}
