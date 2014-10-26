/**
 * Copyright 2011 Saurabh Gangarde & Rohit Ghatol (http://code.google.com/p/droidtwit/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.tutorial.alarmmgr;

import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author rohit
 * 
 */

public class AlarmReceiver extends BroadcastReceiver {

	

	@Override
	public void onReceive(final Context context, Intent intent) {
		Log.d(AlarmReceiver.class.getSimpleName(),"Got Broadcast message "+(new Date()));
		//In life cycle method run everything in thread to avoid ANR
		Runnable runnable = new Runnable(){

			public void run() {
				sendNotification(context);
				
			}
			
		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		
		
	}
	
	private void sendNotification(Context context)
	{
		final String ns = Context.NOTIFICATION_SERVICE;
		final NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(ns);
		final Notification notification = new Notification(R.drawable.icon, "Notification",  System.currentTimeMillis());
		//Pending event to open our Application Screen when this notification is clicked
		final PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, AlarmManagerExample.class), 0);
		notification.setLatestEventInfo(context, "nouvelle notification", "vous avez une nouvelle notification!", contentIntent);
		mNotificationManager.notify(1, notification);
	}
}
