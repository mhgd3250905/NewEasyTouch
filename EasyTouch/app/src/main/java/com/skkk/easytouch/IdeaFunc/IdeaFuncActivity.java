package com.skkk.easytouch.IdeaFunc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.skkk.easytouch.R;
import com.skkk.easytouch.Utils.SpUtils;
import com.skkk.easytouch.View.SettingItemCheckableView;

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
        tbAbout.setTitle("白开水试验田");
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
                if (itemCheckTickLight.isChecked()){
                    SpUtils.saveBoolean(getApplicationContext(),SpUtils.KEY_IDEA_FUNC_TICK_LIGHT,false);
                    itemCheckTickLight.setCheckedWithAnim(false);
                }else {
                    SpUtils.saveBoolean(getApplicationContext(),SpUtils.KEY_IDEA_FUNC_TICK_LIGHT,true);
                    itemCheckTickLight.setCheckedWithAnim(true);

                }
            }
        });

        itemCheckGravitySensor.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCheckGravitySensor.isChecked()){
                    SpUtils.saveBoolean(getApplicationContext(),SpUtils.KEY_IDEA_FUNC_GRAVITY_SENSOR,false);
                    itemCheckGravitySensor.setCheckedWithAnim(false);

                }else {
                    SpUtils.saveBoolean(getApplicationContext(),SpUtils.KEY_IDEA_FUNC_GRAVITY_SENSOR,true);
                    itemCheckGravitySensor.setCheckedWithAnim(true);

                }
            }
        });
    }

}
