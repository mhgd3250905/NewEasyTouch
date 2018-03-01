package com.skkk.easytouch.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
    private
    @DrawableRes
    int checkedRes;//选中时候的图片
    private
    @DrawableRes
    int unCheckedRes;//未选中时候的图片

    private boolean isChecked = false;
    private boolean showCheckable = true;//是否显示选择框


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

        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SettingItemCheckableView);
            String content = ta.getString(R.styleable.SettingItemCheckableView_content);
            int titleIconRes = ta.getResourceId(R.styleable.SettingItemCheckableView_titleIcon, R.drawable.logo);
            checkedRes = ta.getResourceId(R.styleable.SettingItemCheckableView_checkedIcon, R.drawable.logo);
            unCheckedRes = ta.getResourceId(R.styleable.SettingItemCheckableView_uncheckIcon, R.drawable.logo);
            showCheckable = ta.getBoolean(R.styleable.SettingItemCheckableView_showCheckable, true);
            ta.recycle();

            tvItem.setText(content);
            ivItem.setImageResource(titleIconRes);
        }
        //设置选中图片
        ivCheckable.setImageResource(isChecked ? checkedRes : unCheckedRes);
        ivCheckable.setVisibility(showCheckable ? VISIBLE : GONE);
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
     * 获取是否勾选状态
     *
     * @return
     */
    public boolean isChecked() {
        return isChecked;
    }


}
