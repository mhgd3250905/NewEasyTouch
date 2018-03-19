package com.skkk.easytouch.View.IdeaFunc;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import android.widget.Toast;

import com.skkk.easytouch.Configs;
import com.skkk.easytouch.MyApplication;
import com.skkk.easytouch.R;
import com.skkk.easytouch.Services.EasyTouchBallService;
import com.skkk.easytouch.Services.EasyTouchLinearService;
import com.skkk.easytouch.Services.FloatService;
import com.skkk.easytouch.Utils.DialogUtils;
import com.skkk.easytouch.Utils.SpUtils;
import com.skkk.easytouch.View.SettingItemCheckableView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IdeaFuncActivity extends AppCompatActivity {

    @Bind(R.id.tb_about)
    Toolbar tbAbout;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.item_check_tick_light)
    SettingItemCheckableView itemCheckTickLight;
    @Bind(R.id.item_check_gravity_sensor)
    SettingItemCheckableView itemCheckGravitySensor;
    private boolean isTickLightOpen;
    private boolean isGravitySensorOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_func);
        ButterKnife.bind(this);
        tbAbout.setTitle("试验田");
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
        //设置双击闪光灯
        isTickLightOpen = SpUtils.getBoolean(getApplicationContext(), SpUtils.KEY_IDEA_FUNC_TICK_LIGHT, false);
        itemCheckTickLight.setChecked(isTickLightOpen);
        //设置抬腕唤醒
        isGravitySensorOpen = SpUtils.getBoolean(getApplicationContext(), SpUtils.KEY_IDEA_FUNC_GRAVITY_SENSOR, false);
        itemCheckGravitySensor.setChecked(isGravitySensorOpen);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        itemCheckTickLight.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAccessibilityServiceRunning("FloatService")) {
                    Toast.makeText(IdeaFuncActivity.this, "使用此功能前，请先打开辅助功能。！", Toast.LENGTH_SHORT).show();
                } else {
                    if (itemCheckTickLight.isChecked()) {
                        SpUtils.saveBoolean(getApplicationContext(), SpUtils.KEY_IDEA_FUNC_TICK_LIGHT, false);
                        itemCheckTickLight.setCheckedWithAnim(false);
                    } else {
                        SpUtils.saveBoolean(getApplicationContext(), SpUtils.KEY_IDEA_FUNC_TICK_LIGHT, true);
                        itemCheckTickLight.setCheckedWithAnim(true);
                    }
                    restartFloatService();
                }
            }
        });

        itemCheckGravitySensor.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCheckGravitySensor.isChecked()) {
                    SpUtils.saveBoolean(getApplicationContext(), SpUtils.KEY_IDEA_FUNC_GRAVITY_SENSOR, false);
                    itemCheckGravitySensor.setCheckedWithAnim(false);

                } else {
                    SpUtils.saveBoolean(getApplicationContext(), SpUtils.KEY_IDEA_FUNC_GRAVITY_SENSOR, true);
                    itemCheckGravitySensor.setCheckedWithAnim(true);

                }
                restartService();
            }
        });

        itemCheckTickLight.setTip("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.createDialog(IdeaFuncActivity.this,
                        R.drawable.dialog_icon_warning,
                        "使用说明",
                        "双击音量键，即可打开闪光灯；\n然后单击音量键，即可关闭闪光灯。",
                        getString(R.string.dialog_button_sure),null,"",null)
                        .show();
            }
        });


        itemCheckTickLight.setTip("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.createDialog(IdeaFuncActivity.this,
                        R.drawable.dialog_icon_warning,
                        "使用说明",
                        "在亮屏情况下;\n双击音量键，即可打开闪光灯；\n然后单击音量键，即可关闭闪光灯。",
                        getString(R.string.dialog_button_sure),null,"",null)
                        .show();
            }
        });

        itemCheckGravitySensor.setTip("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.createDialog(IdeaFuncActivity.this,
                        R.drawable.dialog_icon_warning,
                        "使用说明",
                        "息屏情况下，抬腕拿起手机，即可自动亮屏。",
                        getString(R.string.dialog_button_sure),null,"",null)
                        .show();
            }
        });

    }

    /**
     * 重启服务
     */
    private void restartService() {
        if (MyApplication.getTouchType() == Configs.TouchType.BALL) {
            stopService(new Intent(IdeaFuncActivity.this, EasyTouchBallService.class));
            startService(new Intent(IdeaFuncActivity.this, EasyTouchBallService.class));
        } else if (MyApplication.getTouchType() == Configs.TouchType.LINEAR) {
            stopService(new Intent(IdeaFuncActivity.this, EasyTouchLinearService.class));
            startService(new Intent(IdeaFuncActivity.this, EasyTouchLinearService.class));
        }
    }

    /**
     * 重启服务
     */
    private void restartFloatService() {
        stopService(new Intent(IdeaFuncActivity.this, FloatService.class));
        startService(new Intent(IdeaFuncActivity.this, FloatService.class));
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
