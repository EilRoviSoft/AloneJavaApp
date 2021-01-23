package com.example.aloneservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
	Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.startButton = (Button)findViewById(R.id.start_button);
		this.startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				MainActivity.this.onClick();
			}
		});
	}

	public void onClick() {
		startService(new Intent(this, Servant.class)
				.putExtra("class", "Saber")
				.putExtra("name", "Arturia Pendragon")
				.putExtra("time", 2));

		startService(new Intent(this, Servant.class)
				.putExtra("class", "Archer")
				.putExtra("name", "Emiya Shirou")
				.putExtra("time", 4));
	}
}