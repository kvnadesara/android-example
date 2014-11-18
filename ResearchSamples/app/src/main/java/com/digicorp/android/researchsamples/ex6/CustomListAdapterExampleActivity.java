package com.digicorp.android.researchsamples.ex6;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.digicorp.android.researchsamples.R;

import java.util.ArrayList;

public class CustomListAdapterExampleActivity extends ActionBarActivity {

    ListView customListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_adapter_example);

        customListView = (ListView) findViewById(R.id.customListView);
        ArrayList<CustomItem> customItems = new ArrayList<CustomItem>();
        for (int i = 0; i < 3000; i++) {
            CustomItem item = new CustomItem();
            item.setTitle("Title " + (i + 1));
            item.setDesc("Desc " + (i + 1));
            item.setImgResId(i % 3 == 0 ? R.drawable.ic_calendar_next : R.drawable.ic_launcher);
            customItems.add(item);
        }
        CustomAdapter adapter = new CustomAdapter(this, customItems);
        customListView.setAdapter(adapter);
    }

    private class CustomItem {
        int imgResId;
        String title;
        String desc;

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setImgResId(int imgResId) {
            this.imgResId = imgResId;
        }

        public int getImgResId() {
            return imgResId;
        }
    }

    private class CustomAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<CustomItem> data;

        CustomAdapter(Context context, ArrayList<CustomItem> data) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = inflater.inflate(R.layout.raw_custom_list_item, null);
                Log.d("CustomAdapter", "create view : " + position);
            }

            TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            TextView txtDesc = (TextView) view.findViewById(R.id.txtDesc);
            ImageView imgThumb = (ImageView) view.findViewById(R.id.imgThumb);

            CustomItem item = (CustomItem) getItem(position);
            txtTitle.setText(item.getTitle());
            txtDesc.setText(item.getDesc());
            imgThumb.setImageResource(item.getImgResId());

            return view;
        }
    }
}
