package com.example.aloneservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Servant extends Service {
	String LOG_TAG = "AloneLogs";

	ExecutorService es;
	String name, type;
	int time;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(this.LOG_TAG, "Servant: onCreate() call;");

		this.es = Executors.newFixedThreadPool(1);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(this.LOG_TAG, "Servant: onDestroy() call;");
	}

	@Override
	public IBinder onBind(Intent _intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent _intent, int _flags, int _startId) {
		Log.d(this.LOG_TAG, "Servant: onStartCommand() call;");
		this.type = _intent.getStringExtra("class");
		this.name = _intent.getStringExtra("name");
		this.time = _intent.getIntExtra("time", 0);

		ServantRun command = new ServantRun(_startId, this.time, this.name, this.type);
		es.execute(command);

		return super.onStartCommand(_intent, _flags, _startId);
	}

	class ServantRun implements Runnable {
		int time, startId;
		String type, name;

		public ServantRun(int _startId, int _time, String _type, String _name) {
			this.time = _time;
			this.startId = _startId;
			this.type = _type;
			this.name = _name;

			Log.d(Servant.this.LOG_TAG, "ServantRuns " + this.startId + " create() call;");
		}

		public void run() {
			Log.d(Servant.this.LOG_TAG, "ServantRuns " + this.startId + " start() call, time: " + time + ";");
			System.out.println("My name is " + this.name + " and my class is " + this.type + ".\n" +
					"Order, my master, I will do your bidding.");

			try {
				TimeUnit.SECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			stop();
		}

		public void stop() {
			Log.d(Servant.this.LOG_TAG, "ServantRuns " + this.startId + " stop() call;");
			stopSelf(this.startId);
		}
	}
}
