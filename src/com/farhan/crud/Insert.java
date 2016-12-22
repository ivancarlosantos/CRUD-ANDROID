package com.farhan.crud;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Insert extends Activity {

	private Header header;
	private EditText etName;
	private EditText etAge;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);

		header = (Header) findViewById(R.id.layoutHeader);
		header.init();

		etName = (EditText) findViewById(R.id.et_name);
		etAge = (EditText) findViewById(R.id.et_age);

		findViewById(R.id.btnInsert).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
								
				DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(Insert.this);
				dbAdapter.openDataBase();
				
				ContentValues initialValues = new ContentValues();
				initialValues.put("name", etName.getText().toString());
				initialValues.put("age", etAge.getText().toString());				
				long n = dbAdapter.insertRecordsInDB("user", null, initialValues);				
				Toast.makeText(Insert.this, "new row inserted with id = "+n, Toast.LENGTH_SHORT).show();

			}
		});

	}
}
