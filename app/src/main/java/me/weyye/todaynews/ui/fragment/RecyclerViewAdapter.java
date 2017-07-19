package me.weyye.todaynews.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.weyye.todaynews.R;
import me.weyye.todaynews.presenter.WhaterBean;
import me.weyye.todaynews.utils.ImageLoaderUtils;

/**
 * Created by wty on 2017/7/16.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter {
    List<WhaterBean.ArticlesBean> mTemperature;

    public RecyclerViewAdapter(List<WhaterBean.ArticlesBean> temperature) {
        mTemperature = temperature;
    }

//    private OnItemClickListener onItemClickListener;

    //点击接口
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.onItemClickListener = listener;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView Title;
        public TextView comment;
        public ImageView iv;
//        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.title_text);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            iv.setVisibility(View.GONE);
            comment = (TextView) itemView.findViewById(R.id.comment);

//            //设置点击事件
//            this.onItemClickListener = onItemClickListener;
//            itemView.setOnClickListener(this);
        }


//        @Override
//        public void onClick(View v) {
//            if (onItemClickListener != null) {
//                onItemClickListener.onClick(v, getLayoutPosition());
//            }
//        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                ViewHolder vh = (ViewHolder) holder;

        vh.Title.setText(mTemperature.get(position).getTitle());
        vh.comment.setText(mTemperature.get(position).getAbstracts());
        if (!TextUtils.isEmpty( mTemperature.get(position).getTitlepic())){

            vh.iv.setVisibility(View.VISIBLE);
            vh.iv.setBackgroundResource(R.mipmap.ic_launcher);
            ImageLoaderUtils.displayImage(mTemperature.get(position).getTitlepic(), vh.iv);
        }

    }

    @Override
    public int getItemCount() {
        System.out.println("RecyclerViewAdapter.getItemCount=="+mTemperature.size());
        return mTemperature.size();
    }
}