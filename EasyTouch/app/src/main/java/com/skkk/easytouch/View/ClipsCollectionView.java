package com.skkk.easytouch.View;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skkk.easytouch.R;
import com.skkk.easytouch.Utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClipsCollectionView extends LinearLayout {


    public final static String CLIP_SPLIT_FLAG="αβγ";

    private TextView tvClipsClear;
    private RecyclerView rvClip;
    private ClipAdapter adapter;
    private LinearLayoutManager linearLayoutManager;



    public ClipsCollectionView(Context context) {
        super(context);
        initUI();
    }

    public ClipsCollectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public ClipsCollectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    private void initUI() {
        initClipsListener();

        LayoutInflater.from(getContext()).inflate(R.layout.clip_layout_collection, this, true);
        tvClipsClear = (TextView) findViewById(R.id.tv_clips_clear);
        rvClip = (RecyclerView) findViewById(R.id.rv_clip);

        linearLayoutManager=new LinearLayoutManager(getContext());
        adapter=new ClipAdapter(getContext(),getDefaultClipDate());
        rvClip.setLayoutManager(linearLayoutManager);
        rvClip.setAdapter(adapter);
    }

    /**
     * 设置剪贴板监听
     */
    private void initClipsListener() {
        // 获取系统剪贴板
        final ClipboardManager clipboard = (ClipboardManager) getContext().getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);

        clipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                // 获取剪贴板的剪贴数据集
                ClipData clipData = clipboard.getPrimaryClip();

                if (clipData != null && clipData.getItemCount() > 0) {
                    // 从数据集中获取（粘贴）第一条文本数据
                    CharSequence clip = clipData.getItemAt(0).getText();
                    String saveClipContent = SpUtils.getString(getContext(), SpUtils.KEY_CLIPBOARD_CONTENT, "");
                    if (saveClipContent.isEmpty()){
                        SpUtils.saveString(getContext(), SpUtils.KEY_CLIPBOARD_CONTENT, clip.toString());
                    }else {
                        String newClip=clip.toString()+CLIP_SPLIT_FLAG+saveClipContent;
                        SpUtils.saveString(getContext(),SpUtils.KEY_CLIPBOARD_CONTENT,newClip);
                    }
                }
            }
        });

    }

    /**
     * 获取存储的剪贴板内容
     * @return
     */
    private List<String> getDefaultClipDate() {
        String clipContent = SpUtils.getString(getContext(), SpUtils.KEY_CLIPBOARD_CONTENT, "");
        String[] clipStrings = clipContent.split(CLIP_SPLIT_FLAG);
        List<String> clipArr=new ArrayList<>();
        for (int i = 0; i < clipStrings.length; i++) {
            clipArr.add(clipStrings[i]);
        }
        return clipArr;
    }


    /**
     * 数据适配器
     */
    class ClipAdapter extends BaseAdapter<String,ClipViewHolder> {
        public ClipAdapter(Context context, List<String> mDataList) {
            super(context, mDataList);
        }

        @Override
        protected ClipViewHolder getCostumViewHolder(ViewGroup parent, int viewType) {
            return new ClipViewHolder(LayoutInflater.from(context).inflate(R.layout.clips_layout_item_simple_string, parent, false));
        }

        @Override
        protected void setViewHolder(ClipViewHolder holder, int position) {
            holder.tvString.setText(mDataList.get(position));
        }
    }


    /**
     * 条目数据容器
     */
    class ClipViewHolder extends BaseViewHolder{
        @Bind(R.id.tv_item)
        TextView tvString;
        public ClipViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}