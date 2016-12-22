package com.farhan.crud;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Select extends Activity {
	
	private Header header;
	private ListView lvUsers;
	private ArrayList<UserBO> mListUsers;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        
        header = (Header) findViewById(R.id.layoutHeader);
        header.init();
        
        mListUsers = getUsers();
        lvUsers = (ListView) findViewById(R.id.lv_user);
        lvUsers.setAdapter(new ListAdapter(this, R.id.lv_user, mListUsers));                
        
    }
    
    public ArrayList<UserBO> getUsers(){    	    	

		DBAdapter dbAdapter=DBAdapter.getDBAdapterInstance(this);
		try {
			dbAdapter.createDataBase();
		} catch (IOException e) {
			Log.i("*** select ",e.getMessage());
		}
    	dbAdapter.openDataBase();		
		String query="SELECT * FROM user;";
		ArrayList<ArrayList<String>> stringList = dbAdapter.selectRecordsFromDBList(query, null);
		dbAdapter.close();
		
		ArrayList<UserBO> usersList = new ArrayList<UserBO>();
		for (int i = 0; i < stringList.size(); i++) {
			ArrayList<String> list = stringList.get(i);
			UserBO user = new UserBO();
			try {
				user.id = Integer.parseInt(list.get(0));
				user.name = list.get(1);
				user.age = Long.parseLong(list.get(2));
			} catch (Exception e) {
				Log.i("***" + Select.class.toString(), e.getMessage());
			}
			usersList.add(user);
		}
		return usersList;
	}
    
 // ***ListAdapter***
	private class ListAdapter extends ArrayAdapter<UserBO> {  // --CloneChangeRequired
		private ArrayList<UserBO> mList;  // --CloneChangeRequired
		private Context mContext;
		
		public ListAdapter(Context context, int textViewResourceId,ArrayList<UserBO> list) { // --CloneChangeRequired
			super(context, textViewResourceId, list);
			this.mList = list;
			this.mContext = context;
		}

		public View getView(int position, View convertView, ViewGroup parent){
			View view = convertView;
			try{
			if (view == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = vi.inflate(R.layout.list_item, null); 	// --CloneChangeRequired(list_item)
			}
			final UserBO listItem = mList.get(position); 	// --CloneChangeRequired				
			if (listItem != null) {
				// setting list_item views						
				( (TextView) view.findViewById(R.id.tv_id) ).setText( listItem.getId()+"");
				( (TextView) view.findViewById(R.id.tv_name) ).setText( listItem.getName() );
				( (TextView) view.findViewById(R.id.tv_age) ).setText( listItem.getAge()+"" );
						
			}}catch(Exception e){
				Log.i(Select.ListAdapter.class.toString(), e.getMessage());				
			}
			return view;
		}
		
	}
    
}
