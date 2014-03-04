package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	private MyApplication mApplication = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mApplication = (MyApplication) this.getApplication();
		initUI();
	}

	EditText ed1;
	EditText ed2;

	private void initUI()
	{
		ed1 = (EditText) findViewById(R.id.key);
		ed2 = (EditText) findViewById(R.id.value);
		(findViewById(R.id.commit)).setOnClickListener(this);
		(findViewById(R.id.get)).setOnClickListener(this);
		(findViewById(R.id.remove)).setOnClickListener(this);
		(findViewById(R.id.clear)).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.get:
		{
			String str = (String) mApplication
					.getData(ed1.getText().toString());
			if (str == null)
			{
				showToast("there is no result!");
				return;
			}
			showToast(str);
		}
			break;
		case R.id.commit:
		{
			String key = ed1.getText().toString();
			String value = ed2.getText().toString();
			if (key != null)
			{
				mApplication.setData(key, value);
				showToast(key + " have been committed!");
				return;
			}
			showToast("key is null !");
		}
			break;
		case R.id.remove:
		{
			String key = ed1.getText().toString();
			if (key != null)
			{
				mApplication.removeData(key);
				showToast(key + " has been removed!");
				return;
			}
			showToast("key is null !");
		}
			break;
		case R.id.clear:
		{
			mApplication.clearData();
			showToast("Datas have been cleared!");
		}
			break;
		default:
			break;
		}
	}

	private void showToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

}
