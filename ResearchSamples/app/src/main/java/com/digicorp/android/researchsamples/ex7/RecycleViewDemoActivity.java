package com.digicorp.android.researchsamples.ex7;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.digicorp.android.researchsamples.R;

import java.util.ArrayList;

public class RecycleViewDemoActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private RecyclerView mRecyclerView;

    private DividerDecoration mListViewItemDecoration;
    private GridDividerDecoration mGridViewItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_demo);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);

        // set adapter
        ArrayList<String> dataList = new ArrayList<String>();
        for (int i = 0; i < 300; i++)
            dataList.add("Data item : " + (i + 1));
        MyAdapter adapter = new MyAdapter(dataList);
        adapter.setItemClickListener(this);
        mRecyclerView.setAdapter(adapter);


        mListViewItemDecoration = new DividerDecoration(this);
        mGridViewItemDecoration = new GridDividerDecoration(this);
        //mRecyclerView.addItemDecoration(itemDecoration);

        // this is the default;
        // this call is actually only necessary with custom ItemAnimators
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // set layout manager (1) LinearLayoutManager (2) GridLayoutManager
        // (3) StaggeredGridLayoutManager OR (4) Any custom layout manager
        showLinearLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycle_view_demo, menu);
        return true;
    }

    private void removeAllDecorations() {
        mRecyclerView.removeItemDecoration(mGridViewItemDecoration);
        mRecyclerView.removeItemDecoration(mListViewItemDecoration);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_item:
                ((MyAdapter)mRecyclerView.getAdapter()).addItem();
                return true;

            case R.id.action_remove_item:
                ((MyAdapter)mRecyclerView.getAdapter()).removeItem();
                return true;

            case R.id.action_linear_layout:
                showLinearLayout();
                break;

            case R.id.action_grid_layout:
                showGridLayout();
                break;

            case R.id.action_staggered_grid:
                showStaggeredGridLayout();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Item : " + i, Toast.LENGTH_SHORT).show();
    }

    private void showLinearLayout() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mRecyclerViewLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        removeAllDecorations();
        mRecyclerView.addItemDecoration(mListViewItemDecoration);
    }

    private void showGridLayout() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mRecyclerViewLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        removeAllDecorations();
        mRecyclerView.addItemDecoration(mListViewItemDecoration);
    }

    private void showStaggeredGridLayout() {
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mRecyclerViewLayoutManager = new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        removeAllDecorations();
        mRecyclerView.addItemDecoration(mListViewItemDecoration);
    }

    public void addItemClick(View view) {
        ((MyAdapter)mRecyclerView.getAdapter()).addItem();
    }
}
