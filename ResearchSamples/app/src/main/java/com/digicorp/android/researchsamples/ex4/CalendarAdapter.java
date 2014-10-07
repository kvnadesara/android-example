package com.digicorp.android.researchsamples.ex4;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.digicorp.android.researchsamples.R;

/**
 * @author Kevin<br>
 *         created date : 21 July 2011
 */
public class CalendarAdapter extends BaseAdapter {

    private Date[] calendarGrid;
    //	private SimpleDateFormat format = new SimpleDateFormat("dd");
    private Context mContext;
    private LayoutInflater inflater;
    private Calendar mCal;

    private String today;

//	private static int getViewCount;

    public CalendarAdapter(Context context) {
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCal = Calendar.getInstance();
        initCalendar(mCal);
    }

    private void initCalendar(Calendar cal) {
        Calendar c = Calendar.getInstance();
        c.setTime(cal.getTime());
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
//		int totalWeek = c.getActualMaximum(Calendar.WEEK_OF_MONTH);

        int startOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        c.add(Calendar.DATE, -startOfWeek);

        calendarGrid = new Date[6 * 7];
        int gridCount = 0;
        for (int week = 0; week < 6; week++) {
            for (int day = 0; day < 7; day++) {
                Date dt = c.getTime();
                dt.setHours(0);
                dt.setMinutes(0);
                dt.setSeconds(0);
                calendarGrid[gridCount++] = dt;
                c.add(Calendar.DATE, 1);
            }
        }
    }

    public void nextMonth() {
        mCal.set(mCal.get(Calendar.YEAR), mCal.get(Calendar.MONTH) + 1, mCal.get(Calendar.DATE));
        initCalendar(mCal);
        notifyDataSetChanged();
    }

    public void prevMonth() {
        mCal.set(mCal.get(Calendar.YEAR), mCal.get(Calendar.MONTH) - 1, mCal.get(Calendar.DATE));
        initCalendar(mCal);
        notifyDataSetChanged();
    }

    public Calendar getCurrentCalendar() {
        return mCal;
    }

    @Override
    public int getCount() {
        return calendarGrid.length;
    }

    @Override
    public Date getItem(int position) {
        return calendarGrid[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
//			Log.d("CALENDAR_ADAPTER", getViewCount + " : " + position /*+ " : " + date*/);
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.raw_my_calendar, null);
            holder.txtDate = (TextView) convertView.findViewById(R.id.txtCalDate);
            holder.txtEventCount = (TextView) convertView.findViewById(R.id.txtEventCount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Date date = getItem(position);
//		String day = format.format(date);
//		if (position < 2) {
//			Log.d("CALENDAR_ADAPTER", getViewCount + " : " + position + " => " + date.getDate() + " => " + date);
//		}

        holder.txtDate.setText(String.format("%2d", date.getDate()));
        String keyDate = DateUtils.formatDate(DateUtils.DB_DATE_FORMAT, date);
        setStyle(keyDate, position, holder.txtDate, convertView);

        return convertView;
    }

    private void setStyle(String dateKey, int position, TextView txt, View convertView) {
        if (dateKey.equals(today))
            convertView.setBackgroundColor(Color.parseColor("#88D23218"));
        else
            convertView.setBackgroundColor(Color.parseColor("#88CCCCCC"));

        if (getItem(position).getMonth() != mCal.get(Calendar.MONTH))
            txt.setTextColor(Color.parseColor("#FF787878"));
        else
            txt.setTextColor(Color.parseColor("#FF000000"));
    }

    static class ViewHolder {
        TextView txtDate;
        TextView txtEventCount;
    }
}
