package com.skkk.easytouch.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.skkk.easytouch.Configs;
import com.skkk.easytouch.Services.EasyTouchBallService;
import com.skkk.easytouch.Services.EasyTouchLinearService;
import com.skkk.easytouch.Utils.SpUtils;

/**
 * 创建于 2018/3/4
 * 作者 admin
 */
/*
* 
* 描    述：通知点击的广播接收器
* 作    者：ksheng
* 时    间：2018/3/4$ 15:03$.
*/
public class ShowBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        int touchType= SpUtils.getInt(context.getApplicationContext(), Configs.KEY_SAVE_TOUCH_TYPE,0);
        if (touchType==Configs.TouchType.BALL.getValue()) {
            context.startService(new Intent(context,EasyTouchBallService.class));
        }else if (touchType==Configs.TouchType.LINEAR.getValue()){
            context.startService(new Intent(context,EasyTouchLinearService.class));
        }
    }
}
