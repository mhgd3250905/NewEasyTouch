package com.skkk.easytouch.Utils;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;

/**
 * 创建于 2018/3/6
 * 作者 admin
 */
/*
* 
* 描    述：手电筒工具类
* 作    者：ksheng
* 时    间：2018/3/6$ 22:30$.
*/
public class FlashLightUtils {
    /**
     * 打开手电筒
     * @param context
     */
    public static void openFlashLight(Context context) {
        CameraManager manager = (CameraManager) context.getApplicationContext().getSystemService(Context.CAMERA_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //android6.0调用的手电筒接口
            try {
                manager.setTorchMode("0", true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            Camera camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);// 开启
            camera.setParameters(parameters);
            camera.startPreview();
        }
    }

    /**
     * 关闭手电筒
     * @param context
     */
    public static void closeFlashLight(Context context) {
        CameraManager manager = (CameraManager) context.getApplicationContext().getSystemService(Context.CAMERA_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //android6.0调用的手电筒接口
            try {
                manager.setTorchMode("0", false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            Camera camera = Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);// 关闭
            camera.setParameters(parameters);
            camera.stopPreview();
            camera.release();
        }
    }
}

