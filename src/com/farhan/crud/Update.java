package com.farhan.crud;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends Activity {
	
	private Header header;
	EditText etId;
	EditText etName;
	EditText etAge;
	
	Button btnShow;
	Button btnUpdate;
	Button btnCancel;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        
        header = (Header) findViewById(R.id.layoutHeader);
        header.init();
        
        etId = (EditText) findViewById(R.id.et_id);
        etName = (EditText) findViewById(R.id.et_name);
        etAge = (EditText) findViewById(R.id.et_age);
        
        btnShow = (Button) findViewById(R.id.btnShow);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        
        btnShow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
				int id = Integer.parseInt(etId.getText().toString());								
				ArrayList<UserBO> listUsers = getUsers(id);
				UserBO user = listUsers.get(0);
				etName.setText(user.getName());
				etAge.setText(user.getAge()+"");
				etName.setEnabled(true);
				etAge.setEnabled(true);
				etId.setEnabled(false);
				btnShow.setEnabled(false);
				btnUpdate.setEnabled(true);
				btnCancel.setEnabled(true);
				Toast.makeText(Update.this, listUsers.size()+" rows selected 1st show", Toast.LENGTH_SHORT).show();
				
			}        	
        });        
        
        btnCancel.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
				etName.setEnabled(false);
				etAge.setEnabled(false);
				etId.setEnabled(true);
				btnShow.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnCancel.setEnabled(false);
			}        	
        });        
        
        btnUpdate.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {	
				


				DBAdapter dbAdapter = DBAdapter.getDBAdapterInstance(Update.this);
				dbAdapter.openDataBase();								
				
				ContentValues initialValues = new ContentValues();
				initialValues.put("name", etName.getText().toString());
				initialValues.put("age", etAge.getText().toString());
				String id = etId.getText().toString();
				String [] strArray = {""+id};
				long n = dbAdapter.updateRecordsInDB("user", initialValues, "id=?", strArray);
				
				Toast.makeText(Update.this, n+" rows updated", Toast.LENGTH_SHORT).show();
				
				
				
				etName.setEnabled(false);
				etAge.setEnabled(false);
				etId.setEnabled(true);
				btnShow.setEnabled(true);
				btnUpdate.setEnabled(false);
				btnCancel.setEnabled(false);
			}        	
        });
        
    }
    
    public ArrayList<UserBO> getUsers(int id){    	    	

		DBAdapter dbAdapter=DBAdapter.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ",e.getMessage());
		}
    	dbAdapter.openDataBase();		
		String query="SELECT * FROM user where id = "+id+";";
		ArrayList<ArrayList<String>> stringList = dbAdapter.selectRecordsFromDBList(query, null);
		dbAdapter.close();
		
		ArrayList<UserBO> usersList = new ArrayList<UserBO>();
		for(int i=0; i<stringList.size(); i++){
			ArrayList<String> list = stringList.get(i);
			UserBO user = new UserBO();
			user.id = Integer.parseInt( list.get(0) );
			user.name = list.get(1);
			user.age = Long.parseLong( list.get(2) );
			usersList.add(user);
		}
		return usersList;
    }
    
    
}
