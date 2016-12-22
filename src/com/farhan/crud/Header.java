package com.farhan.crud;

import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Header extends LinearLayout{
	private Context mContext;
	private Button btn11;
	private Button btn12;
	private Button btn13;
	private Button btn14;

	public Header(Context context, AttributeSet attrs) {
		super(context, attrs);

		mContext = context;

		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;

		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.header, this, true);

		btn11 = (Button) findViewById(R.id.Button11);		
		btn11.setOnClickListener(mFooterListener); 
		btn12 = (Button) findViewById(R.id.Button12);
		btn12.setOnClickListener(mFooterListener);
		btn13 = (Button) findViewById(R.id.Button13);		
		btn13.setOnClickListener(mFooterListener); 
		btn14 = (Button) findViewById(R.id.Button14);
		btn14.setOnClickListener(mFooterListener);		

	}

	public void init() {
		// set init otherwise of ctor and call externally...
	}

	// Create an anonymous implementation of OnClickListener
	private OnClickListener mFooterListener = new OnClickListener() {
	    public void onClick(View v) {

	    	switch (v.getId()) {
			case R.id.Button11:
				mContext.startActivity(new Intent(mContext, Select.class));				
				break;
			case R.id.Button12:
				mContext.startActivity(new Intent(mContext, Insert.class));
				break;
			case R.id.Button13:
				mContext.startActivity(new Intent(mContext, Update.class));
				break;
			case R.id.Button14:
				mContext.startActivity(new Intent(mContext, Delete.class));
				break;
			}

	    }
	};
}

