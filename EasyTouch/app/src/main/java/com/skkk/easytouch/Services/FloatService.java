package com.skkk.easytouch.Services;

import android.accessibilityservice.AccessibilityService;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import com.skkk.easytouch.Utils.FlashLightUtils;
import com.skkk.easytouch.Utils.SpUtils;

import static android.content.ContentValues.TAG;

/**
 * 创建于 2017/10/20
 * 作者 admin
 */
/*
* 
* 描    述：
* 作    者：ksheng
* 时    间：2017/10/20$ 18:52$.
*/
public class FloatService extends AccessibilityService {
    private static AccessibilityService service;
    private static final long TICK_TIME_GAP = 300;//双击间隔
    private long startTickTime;
    private long tickTimeGap;
    private int tickTimes;

    public FloatService() {
        service = this;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    public static AccessibilityService getService() {
        if (service == null) {
            return null;
        }
        return service;
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        if (SpUtils.getBoolean(getApplicationContext(),SpUtils.KEY_IDEA_FUNC_TICK_LIGHT,false)) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (event.getKeyCode()) {
                    case KeyEvent.KEYCODE_VOLUME_DOWN:
                    case KeyEvent.KEYCODE_VOLUME_UP:
                        if (tickTimes == 0) {
                            Log.i(TAG, "onKeyEvent: tickTimes:" + tickTimes);
                            startTickTime = System.currentTimeMillis();
                            tickTimes++;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (tickTimes == 1) {
                                        tickTimes = 0;
                                        tickTimeGap = 0;
                                    }
                                }
                            }, 300);
                        } else if (tickTimes == 1) {
                            Log.i(TAG, "onKeyEvent: tickTimes:" + tickTimes);
                            tickTimeGap = System.currentTimeMillis() - startTickTime;
                            if (tickTimeGap < TICK_TIME_GAP) {
                                FlashLightUtils.openFlashLight(getApplicationContext());
                                tickTimes++;
                                Log.i(TAG, "openLight: tickTimes:" + tickTimes);
                            } else {
                                Log.i(TAG, "onKeyEvent: tickTimeGap:" + tickTimeGap);
                                tickTimes = 0;
                                tickTimeGap = 0;
                            }
                        } else if (tickTimes == 2) {
                            FlashLightUtils.closeFlashLight(getApplicationContext());
                            tickTimes = 0;
                            tickTimeGap = 0;
                            Log.i(TAG, "closeLight: tickTimes:" + tickTimes);

                        }
                        break;
                    default:
                }
            }
            return super.onKeyEvent(event);
        }else {
            return super.onKeyEvent(event);
        }
    }
}
