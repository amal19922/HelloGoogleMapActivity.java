package com.tutorial.alarmmgr;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmManagerExample extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setAlarm();
    }
    
   

	/**
	 * Set alarm manager for auto-refreshing twits
	 */
	private void setAlarm()
	{
		final AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		final Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
		final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 100, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (5000), 
				60000, pendingIntent);

	}
	
	 /* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		clearNotification();
	}
	private void clearNotification(){
		NotificationManager mNotificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
	}
    
    
}
