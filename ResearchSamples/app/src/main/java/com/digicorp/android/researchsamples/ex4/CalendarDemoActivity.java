package com.digicorp.android.researchsamples.ex4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.digicorp.android.researchsamples.R;

public class CalendarDemoActivity extends Activity {
	private GridView calendar;
	private CalendarAdapter adapter;
	private TextView txtDate;
	private SimpleDateFormat format = new SimpleDateFormat("MMMM, yyyy");

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar_demo);
		findViews();
		adapter = new CalendarAdapter(this);
		calendar.setAdapter(adapter);
		
		Date currDate = adapter.getCurrentCalendar().getTime();
		//Locale locale = new Locale("ar", "KW");
        Locale locale = new Locale("en", "GB");
		SimpleDateFormat df = new SimpleDateFormat("MMMM, yyyy", locale);
		
		String strDate = df.format(currDate);
		txtDate.setTypeface(Typeface.SERIF);
		txtDate.setText(strDate);	
	}

	public void findViews() {
		calendar = (GridView) findViewById(R.id.gridCalendar);
		txtDate = (TextView) findViewById(R.id.txtDate);
	}

	public void btnPrevClick(View v) {
		adapter.prevMonth();
		//txtDate.setText(format.format(adapter.getCurrentCalendar().getTime()));
		
		Date currDate = adapter.getCurrentCalendar().getTime();
		//Locale locale = new Locale("ar", "KW");
        Locale locale = new Locale("en", "GB");
		SimpleDateFormat df = new SimpleDateFormat("MMMM, yyyy", locale);
		txtDate.setText(df.format(currDate));
		txtDate.setGravity(Gravity.RIGHT);
	}

	public void btnNextClick(View v) {
		adapter.nextMonth();
		//txtDate.setText(format.format(adapter.getCurrentCalendar().getTime()));
		
		Date currDate = adapter.getCurrentCalendar().getTime();
		//Locale locale = new Locale("ar", "KW");
        Locale locale = new Locale("en", "GB");
		SimpleDateFormat df = new SimpleDateFormat("MMMM, yyyy", locale);
		txtDate.setText(df.format(currDate));
		txtDate.setGravity(Gravity.RIGHT);
	}
}