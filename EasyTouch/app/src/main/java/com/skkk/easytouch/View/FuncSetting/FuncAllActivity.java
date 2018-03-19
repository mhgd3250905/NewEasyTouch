package com.skkk.easytouch.View.FuncSetting;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.accessibility.AccessibilityManager;

import com.skkk.easytouch.Configs;
import com.skkk.easytouch.MyApplication;
import com.skkk.easytouch.R;
import com.skkk.easytouch.Services.EasyTouchBallService;
import com.skkk.easytouch.Services.EasyTouchLinearService;
import com.skkk.easytouch.Services.FloatService;
import com.skkk.easytouch.Utils.SpUtils;
import com.skkk.easytouch.View.FunctionSelect.FunctionSelectActivity;
import com.skkk.easytouch.View.SettingItemCheckableView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FuncAllActivity extends AppCompatActivity {

    @Bind(R.id.tb_about)
    Toolbar tbAbout;
    @Bind(R.id.item_check_func_motion)
    SettingItemCheckableView itemCheckFuncMotion;
    @Bind(R.id.item_check_landscape_hide)
    SettingItemCheckableView itemCheckLandscapeHide;
    @Bind(R.id.item_check_auto_hide)
    SettingItemCheckableView itemCheckAutoHide;
    @Bind(R.id.item_check_boot_start)
    SettingItemCheckableView itemCheckBootStart;

    private boolean isLandscapeHide;
    private boolean isAutoHide;
    private boolean isBootStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func_all);
        ButterKnife.bind(this);
        tbAbout.setTitle("功能设置");
        tbAbout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tbAbout.setNavigationIcon(R.drawable.ic_arrow_back_white);

        initUI();
        initEvent();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        //设置横向隐藏
        isLandscapeHide = SpUtils.getBoolean(getApplicationContext(), Configs.KEY_TOUCH_LANDSCAPE_HIDE, false);
        itemCheckLandscapeHide.setChecked(isLandscapeHide);
        //设置悬浮球自动躲避
        isAutoHide = SpUtils.getBoolean(getApplicationContext(), Configs.KEY_TOUCH_BALL_AUTO_HIDE, false);
        itemCheckAutoHide.setChecked(isAutoHide);
        //设置开机启动
        isBootStart = SpUtils.getBoolean(getApplicationContext(), Configs.KEY_BOOT_START, false);
        itemCheckBootStart.setChecked(isBootStart);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        itemCheckLandscapeHide.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCheckLandscapeHide.isChecked()) {
                    SpUtils.saveBoolean(getApplicationContext(), Configs.KEY_TOUCH_LANDSCAPE_HIDE, false);
                    itemCheckLandscapeHide.setCheckedWithAnim(false);
                } else {
                    SpUtils.saveBoolean(getApplicationContext(), Configs.KEY_TOUCH_LANDSCAPE_HIDE, true);
                    itemCheckLandscapeHide.setCheckedWithAnim(true);
                }
                restartService();
            }
        });


        itemCheckAutoHide.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCheckAutoHide.isChecked()) {
                    SpUtils.saveBoolean(getApplicationContext(), Configs.KEY_TOUCH_BALL_AUTO_HIDE, false);
                    itemCheckAutoHide.setCheckedWithAnim(false);
                } else {
                    SpUtils.saveBoolean(getApplicationContext(), Configs.KEY_TOUCH_BALL_AUTO_HIDE, true);
                    itemCheckAutoHide.setCheckedWithAnim(true);
                }
                restartService();
            }
        });

        itemCheckBootStart.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCheckBootStart.isChecked()) {
                    SpUtils.saveBoolean(getApplicationContext(), Configs.KEY_BOOT_START, false);
                    itemCheckBootStart.setCheckedWithAnim(false);
                } else {
                    SpUtils.saveBoolean(getApplicationContext(), Configs.KEY_BOOT_START, true);
                    itemCheckBootStart.setCheckedWithAnim(true);
                }
            }
        });

        itemCheckFuncMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FuncAllActivity.this, FunctionSelectActivity.class));
            }
        });

    }

    /**
     * 重启服务
     */
    private void restartService() {
        if (MyApplication.getTouchType() == Configs.TouchType.BALL) {
            stopService(new Intent(FuncAllActivity.this, EasyTouchBallService.class));
            startService(new Intent(FuncAllActivity.this, EasyTouchBallService.class));
        } else if (MyApplication.getTouchType() == Configs.TouchType.LINEAR) {
            stopService(new Intent(FuncAllActivity.this, EasyTouchLinearService.class));
            startService(new Intent(FuncAllActivity.this, EasyTouchLinearService.class));
        }
    }

    /**
     * 重启服务
     */
    private void restartFloatService() {
        stopService(new Intent(FuncAllActivity.this, FloatService.class));
        startService(new Intent(FuncAllActivity.this, FloatService.class));
    }

    /**
     * 判断是否存在置顶的无障碍服务
     *
     * @param name
     * @return
     */
    public boolean isAccessibilityServiceRunning(String name) {
        AccessibilityManager am = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> enableServices
                = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo enableService : enableServices) {
//            Log.i(TAG, "installService.id-->" + enableService.getId());
            if (enableService.getId().endsWith(name)) {
                return true;
            }
        }
        return false;
    }

}
