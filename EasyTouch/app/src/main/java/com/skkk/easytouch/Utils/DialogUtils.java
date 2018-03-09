package com.skkk.easytouch.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skkk.easytouch.R;

/**
 * 创建于 2017/9/18
 * 作者 admin
 */
/*
* 
* 描    述：弹出对话框样式
* 作    者：ksheng
* 时    间：2017/9/18$ 21:33$.
*/
public class DialogUtils {

    public static AlertDialog createDialog(Context context, @DrawableRes int iconRes, String title, String message
            , String positiveTitle, DialogInterface.OnClickListener positiveClickListener,
                                           String negativeTitle, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.AlertDialogCustom);
        builder.setIcon(iconRes);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveTitle, positiveClickListener);
        builder.setNegativeButton(negativeTitle, negativeClickListener);
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    public static AlertDialog createImageDialog(Context context, @DrawableRes int iconRes, String title, String message
            ,@DrawableRes int messageImgRes, String positiveTitle, DialogInterface.OnClickListener positiveClickListener,
                                                String negativeTitle, DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.AlertDialogCustom);
        builder.setIcon(iconRes);
        builder.setTitle(title);
        View messageView = LayoutInflater.from(context).inflate(R.layout.dialog_layout_image_content, null, false);
        TextView tvMessage = (TextView) messageView.findViewById(R.id.tv_dialog_message);
        ImageView ivMessage= (ImageView) messageView.findViewById(R.id.iv_dialog_message);
        tvMessage.setText(message);
        ivMessage.setImageResource(messageImgRes);
        builder.setView(messageView);
        builder.setPositiveButton(positiveTitle, positiveClickListener);
        builder.setNegativeButton(negativeTitle, negativeClickListener);
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

}
