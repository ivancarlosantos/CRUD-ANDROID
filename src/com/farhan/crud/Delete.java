package com.farhan.crud;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends Activity {
	
	private Header header;
	private EditText etId;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        
        header = (Header) findViewById(R.id.layoutHeader);
        header.init();
        
        etId = (EditText) findViewById(R.id.et_id);
        
        findViewById(R.id.btnDelete).setOnClickListener(
        		new OnClickListener(){
					public void onClick(View v) {
						
						DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(Delete.this);
						dbAdapter.openDataBase();
						
						String id = etId.getText().toString();
						String [] strArray = {""+id};
						long n = dbAdapter.deleteRecordInDB("user", "id = ?", strArray);				
						Toast.makeText(Delete.this, n+" rows effected", Toast.LENGTH_SHORT).show();
					}        			
        		}
        );
        
    }
}
