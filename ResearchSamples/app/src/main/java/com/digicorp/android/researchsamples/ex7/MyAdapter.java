package com.digicorp.android.researchsamples.ex7;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.digicorp.android.researchsamples.R;

import java.util.ArrayList;

/**
 * Created by kevin.adesara on 18/11/14.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> mData;
    private static int itemNumber;

    public MyAdapter(ArrayList<String> data) {
        this.mData = data;
        itemNumber = this.mData.size();
    }

    private AdapterView.OnItemClickListener mItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        v.setBackgroundResource(R.drawable.background_game);
        ViewHolder vh = new ViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(mData.get(i));
    }

    private void onItemHolderClick(ViewHolder item) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(null, item.itemView, item.getPosition(),
                    item.getItemId());
        }
    }

    public void addItem() {
        mData.add(1, "Data item : " + itemNumber++);
        notifyItemInserted(1);
    }

    public void removeItem() {
        if(mData.size() > 0) {
            mData.remove(0);
            notifyItemRemoved(0);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public AdapterView.OnItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(AdapterView.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;

        private MyAdapter mAdapter;

        public ViewHolder(View itemView, MyAdapter adapter) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(this);
            textView.setClickable(true);
            this.mAdapter = adapter;
        }

        @TargetApi(21)
        @Override
        public void onClick(View view) {


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // get the center for the clipping circle
                int cx = (view.getLeft() + view.getRight()) / 2;
                int cy = (view.getTop() + view.getBottom()) / 2;

                // get the final radius for the clipping circle
                int finalRadius = Math.max(view.getWidth(), view.getHeight());


                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

                // make the view invisible when the animation is done
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mAdapter.onItemHolderClick(ViewHolder.this);
                    }
                });

                anim.start();
            } else {
                mAdapter.onItemHolderClick(ViewHolder.this);
            }
        }
    }


}
