package com.skkk.easytouch.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 创建于 2018/3/1
 * 作者 admin
 */
/*
* 
* 描    述：收集剪贴板的View
* 作    者：ksheng
* 时    间：2018/3/1$ 23:07$.
*/
public class ClipCollectionView extends LinearLayout {
    public ClipCollectionView(Context context) {
        super(context);
    }

    public ClipCollectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClipCollectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//
//    public final static String CLIP_SPLIT_FLAG="&&";
//
//    private ImageView ivClipMove;
//    private ImageView ivClipClose;
//    private RecyclerView rvClip;
//    private ClipAdapter adapter;
//    private LinearLayoutManager linearLayoutManager;
//
//
//
//    public ClipCollectionView(Context context) {
//        super(context);
//        initUI();
//    }
//
//    public ClipCollectionView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initUI();
//    }
//
//    public ClipCollectionView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        initUI();
//    }
//
//    private void initUI() {
//        LayoutInflater.from(getContext()).inflate(R.layout.layout_clipboard, this, true);
//        ivClipMove = (ImageView) findViewById(R.id.iv_clip_move);
//        ivClipClose = (ImageView) findViewById(R.id.iv_clip_close);
//        rvClip = (RecyclerView) findViewById(R.id.rv_clip);
//
//        linearLayoutManager=new LinearLayoutManager(getContext());
//        adapter=new ClipAdapter(getContext(),getDefaultClipDate());
//        rvClip.setLayoutManager(linearLayoutManager);
//        rvClip.setAdapter(adapter);
//    }
//
//    /**
//     * 获取存储的剪贴板内容
//     * @return
//     */
//    private List<String> getDefaultClipDate() {
//        String clipContent = SpUtils.getString(getContext(), SpUtils.KEY_CLIPBOARD_CONTENT, "");
//        String[] clipStrings = clipContent.split(CLIP_SPLIT_FLAG);
//        List<String> clipArr=new ArrayList<>();
//        for (int i = 0; i < clipStrings.length; i++) {
//            clipArr.add(clipStrings[i]);
//        }
//        return clipArr;
//    }
//
//
//    /**
//     * 数据适配器
//     */
//    class ClipAdapter extends BaseAdapter<String,ClipViewHolder> {
//        public ClipAdapter(Context context, List<String> mDataList) {
//            super(context, mDataList);
//        }
//
//        @Override
//        protected ClipViewHolder getCostumViewHolder(ViewGroup parent, int viewType) {
//            return new ClipViewHolder(LayoutInflater.from(context).inflate(R.layout.item_string, parent, false));
//        }
//
//        @Override
//        protected void setViewHolder(ClipViewHolder holder, int position) {
//            holder.tvString.setText(mDataList.get(position));
//        }
//    }
//
//
//    /**
//     * 条目数据容器
//     */
//    class ClipViewHolder extends BaseViewHolder{
//        @Bind(R.id.tv_string)
//        TextView tvString;
//        public ClipViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this,itemView);
//        }
//    }
//

}
