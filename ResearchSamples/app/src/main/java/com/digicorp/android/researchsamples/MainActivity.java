package com.digicorp.android.researchsamples;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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


public class MainActivity extends ListActivity {

    private static final int EX1_CUSTOM_FONT_TEXT_VIEW = 0;
    private static final int EX2_CUSTOM_ASYNC_LOADER = 1;
    private static final int EX3_SHAPE_BUTTON = 2;
    private static final int EX4_CALENDAR = 3;
    private static final int EX5_IMAGE_LAYER = 4;

    private String[] exampleTitles = new String[]{
            "Custom font TextView",
            "Custom AsyncLoader",
            "Shape Button",
            "Calendar",
            "Image Layer"
    };

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toast = Toast.makeText(this, "", Toast.LENGTH_LONG);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exampleTitles);
        setListAdapter(adapter);
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


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case EX1_CUSTOM_FONT_TEXT_VIEW:
                Intent ex1Intent = new Intent(this, CustomFontTextViewActivity.class);
                startActivity(ex1Intent);
                break;
            case EX2_CUSTOM_ASYNC_LOADER:
                Intent ex2Intent = new Intent(this, CustomAsyncLoaderActivity.class);
                startActivity(ex2Intent);
                break;
            case EX3_SHAPE_BUTTON:
                Intent ex3Intent = new Intent(this, ShapeButtonActivity.class);
                startActivity(ex3Intent);
                break;
            case EX4_CALENDAR:
                Intent ex4Intent = new Intent(this, CalendarDemoActivity.class);
                startActivity(ex4Intent);
                break;
            case EX5_IMAGE_LAYER:

                break;
            default:
                toast.setText("Example is under development...");
                toast.show();
                break;
        }
    }
}
