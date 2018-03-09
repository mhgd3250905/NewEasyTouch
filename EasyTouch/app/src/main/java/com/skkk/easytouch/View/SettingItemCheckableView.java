package com.skkk.easytouch.View;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skkk.easytouch.R;

/**
 * 创建于 2018/2/25
 * 作者 admin
 */
/*
* 
* 描    述：
* 作    者：ksheng
* 时    间：2018/2/25$ 16:46$.
*/
public class SettingItemCheckableView extends RelativeLayout {

    private LinearLayout llItem;
    private ImageView ivItem;
    private TextView tvItem;
    private ImageView ivCheckable;
    private ImageView ivTip;
    /**
     * 选中时候的图片
     */
    @DrawableRes
    private int checkedRes;
    /**
     * 未选中时候的图片
     */
    @DrawableRes
    private int unCheckedRes;

    private boolean isChecked = false;
    /**
     * 是否显示选择框
     */
    private boolean showCheckable = true;
    private boolean showTip=false;


    public SettingItemCheckableView(Context context) {
        super(context);
        initUI(null);
    }

    public SettingItemCheckableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(attrs);
    }

    public SettingItemCheckableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(attrs);
    }

    /**
     * 初始化界面
     */
    private void initUI(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_image_check_item, this, true);
        llItem = (LinearLayout) findViewById(R.id.ll_item);
        ivItem = (ImageView) findViewById(R.id.iv_item);
        tvItem = (TextView) findViewById(R.id.tv_item);
        ivCheckable = (ImageView) findViewById(R.id.iv_checkable);
        ivTip= (ImageView) findViewById(R.id.iv_tip);

        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SettingItemCheckableView);
            String content = ta.getString(R.styleable.SettingItemCheckableView_content);
            int titleIconRes = ta.getResourceId(R.styleable.SettingItemCheckableView_titleIcon, R.drawable.mian_item_title_icon_touch);
            checkedRes = ta.getResourceId(R.styleable.SettingItemCheckableView_checkedIcon, R.drawable.main_item_conetnt_icon_checked);
            unCheckedRes = ta.getResourceId(R.styleable.SettingItemCheckableView_uncheckIcon, R.drawable.main_item_conetnt_icon_uncheck);
            showCheckable = ta.getBoolean(R.styleable.SettingItemCheckableView_showCheckable, true);
            showTip = ta.getBoolean(R.styleable.SettingItemCheckableView_showTip, false);
            ta.recycle();

            tvItem.setText(content);
            ivItem.setImageResource(titleIconRes);
        }
        //设置选中图片
        ivCheckable.setImageResource(isChecked ? checkedRes : unCheckedRes);
        ivCheckable.setVisibility(showCheckable ? VISIBLE : GONE);
        ivTip.setVisibility(showTip ? VISIBLE : GONE);
        initEvent();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
    }

    /**
     * 当勾选框被隐藏的时候可以设置点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnClickListener onItemClickListener) {
        if (llItem != null) {
            llItem.setOnClickListener(onItemClickListener);
        }
    }

    /**
     * 设置标题Icon
     *
     * @param titleIcon
     */
    public void setTitleIcon(@DrawableRes int titleIcon) {
        if (ivItem != null) {
            ivItem.setImageResource(titleIcon);
        }
    }

    /**
     * 设置选中icon
     *
     * @param checkedRes
     */
    public void setCheckedRes(@DrawableRes int checkedRes) {
        this.checkedRes = checkedRes;
    }

    /**
     * 设置未选中icon
     *
     * @param unCheckedRes
     */
    public void setUnCheckedRes(@DrawableRes int unCheckedRes) {
        this.unCheckedRes = unCheckedRes;
    }

    /**
     * 设置文字内容
     *
     * @param content
     */
    public void setContent(String content) {
        if (tvItem != null) {
            tvItem.setText(content);
        }
    }

    /**
     * 设置是否勾选
     *
     * @param checked
     */
    public void setChecked(boolean checked) {
        isChecked = checked;
        ivCheckable.setImageResource(isChecked ? checkedRes : unCheckedRes);
    }

    /**
     * 设置是否勾选带动画
     *
     * @param checked
     */
    public void setCheckedWithAnim(final boolean checked) {
        final ObjectAnimator zoomOutXAnim=ObjectAnimator.ofFloat(ivCheckable,"scaleX",1f,0);
        final ObjectAnimator zoomOutYAnim=ObjectAnimator.ofFloat(ivCheckable,"scaleY",1f,0);
        final AnimatorSet zoomOutSet=new AnimatorSet();
        zoomOutSet.play(zoomOutXAnim).with(zoomOutYAnim);
        zoomOutSet.setDuration(100);
        zoomOutSet.setInterpolator(new LinearInterpolator());
        zoomOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isChecked = checked;
                ivCheckable.setImageResource(isChecked ? checkedRes : unCheckedRes);
                ObjectAnimator zoomInXAnim=ObjectAnimator.ofFloat(ivCheckable,"scaleX",0,1f);
                ObjectAnimator zoomInYAnim=ObjectAnimator.ofFloat(ivCheckable,"scaleY",0,1f);
                AnimatorSet zoomInSet=new AnimatorSet();
                zoomInSet.play(zoomInXAnim).with(zoomInYAnim);
                zoomInSet.setDuration(100);
                zoomInSet.setInterpolator(new LinearInterpolator());
                zoomInSet.start();
            }
        });
        zoomOutSet.start();
    }

    /**
     * 开始消失动画
     *
     */
    public void startZoomOutWithAnim() {
        final ObjectAnimator zoomOutXAnim=ObjectAnimator.ofFloat(ivCheckable,"scaleX",1f,0);
        final ObjectAnimator zoomOutYAnim=ObjectAnimator.ofFloat(ivCheckable,"scaleY",1f,0);
        final AnimatorSet zoomOutSet=new AnimatorSet();
        zoomOutSet.play(zoomOutXAnim).with(zoomOutYAnim);
        zoomOutSet.setDuration(100);
        zoomOutSet.setInterpolator(new LinearInterpolator());
        zoomOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        zoomOutSet.start();
    }
    /**
     * 开始出现动画
     *
     */
    public void startZoomInWithAnim() {
        final ObjectAnimator zoomInXAnim=ObjectAnimator.ofFloat(ivCheckable,"scaleX",0,1f);
        final ObjectAnimator zoomInYAnim=ObjectAnimator.ofFloat(ivCheckable,"scaleY",0,1f);
        final AnimatorSet zoomInSet=new AnimatorSet();
        zoomInSet.play(zoomInXAnim).with(zoomInYAnim);
        zoomInSet.setDuration(100);
        zoomInSet.setInterpolator(new LinearInterpolator());
        zoomInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        zoomInSet.start();
    }

    /**
     * 获取是否勾选状态
     *
     * @return
     */
    public boolean isChecked() {
        return isChecked;
    }

    /**
     * 设置是否显示勾选框
     * @param showCheckable 显示与否
     */
    public void setCheckIconShow(boolean showCheckable){
        this.showCheckable=showCheckable;
        ivCheckable.setVisibility(showCheckable?VISIBLE:GONE);
    }

    /**
     * 设置警告未实现
     * @param tipMessage
     */
    public void setTip(String tipMessage,OnClickListener onClickListener){
        if (ivTip!=null) {
            ivTip.setVisibility(VISIBLE);
            ivTip.setOnClickListener(onClickListener);
        }
    }


}
