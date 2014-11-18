package com.digicorp.android.researchsamples;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.digicorp.android.researchsamples.ex1.CustomFontTextViewActivity;
import com.digicorp.android.researchsamples.ex2.CustomAsyncLoaderActivity;
import com.digicorp.android.researchsamples.ex3.ShapeButtonActivity;
import com.digicorp.android.researchsamples.ex4.CalendarDemoActivity;
import com.digicorp.android.researchsamples.ex5.TestActivity;
import com.digicorp.android.researchsamples.ex6.CustomListAdapterExampleActivity;
import com.digicorp.android.researchsamples.ex7.RecycleViewDemoActivity;


public class MainActivity extends ActionBarActivity {

    private static final int EX1_CUSTOM_FONT_TEXT_VIEW = 0;
    private static final int EX2_CUSTOM_ASYNC_LOADER = 1;
    private static final int EX3_SHAPE_BUTTON = 2;
    private static final int EX4_CALENDAR = 3;
    private static final int EX5_OAUTH_TEST = 4;
    private static final int EX6_CUSTOM_LIST_ADAPTER = 5;
    private static final int EX7_RECYCLER_VIEW = 6;

    private String[] exampleTitles = new String[]{
            "Custom font TextView",
            "Custom AsyncLoader",
            "Shape Button",
            "Calendar",
            "Image Layer",
            "Custom List Adapter",
            "RecyclerView Demo"
    };

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toast = Toast.makeText(this, "", Toast.LENGTH_LONG);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, ExampleListFragment.newInstance(exampleTitles));
        ft.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class ExampleListFragment extends ListFragment {

        String[] data;

        public ExampleListFragment() {

        }

        public static ExampleListFragment newInstance(String[] data) {
            Bundle arguments = new Bundle();
            arguments.putStringArray("example_names", data);
            ExampleListFragment frag = new ExampleListFragment();
            frag.setArguments(arguments);
            return frag;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            data = getArguments().getStringArray("example_names");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
            setListAdapter(adapter);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            switch (position) {
                case EX1_CUSTOM_FONT_TEXT_VIEW:
                    Intent ex1Intent = new Intent(getActivity(), CustomFontTextViewActivity.class);
                    startActivity(ex1Intent);
                    break;
                case EX2_CUSTOM_ASYNC_LOADER:
                    Intent ex2Intent = new Intent(getActivity(), CustomAsyncLoaderActivity.class);
                    startActivity(ex2Intent);
                    break;
                case EX3_SHAPE_BUTTON:
                    Intent ex3Intent = new Intent(getActivity(), ShapeButtonActivity.class);
                    startActivity(ex3Intent);
                    break;
                case EX4_CALENDAR:
                    Intent ex4Intent = new Intent(getActivity(), CalendarDemoActivity.class);
                    startActivity(ex4Intent);
                    break;
                case EX5_OAUTH_TEST:
                    Intent ex5Intent = new Intent(getActivity(), TestActivity.class);
                    startActivity(ex5Intent);
                    break;
                case EX6_CUSTOM_LIST_ADAPTER:
                    Intent ex6Intent = new Intent(getActivity(), CustomListAdapterExampleActivity.class);
                    startActivity(ex6Intent);
                    break;
                case EX7_RECYCLER_VIEW:
                    Intent ex7Intent = new Intent(getActivity(), RecycleViewDemoActivity.class);
                    startActivity(ex7Intent);
                    break;
                default:
                    break;
            }
        }
    }
}
