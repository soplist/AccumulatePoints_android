package com.jingrui.accumulatepoints;

import android.app.Activity;
import android.app.AlertDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jingrui.adapter.ScoreListAdapter;
import com.jingrui.dao.AccumulatePointsDao;
import com.jingrui.dao.AccumulatePointsDatabaseHelper;
import com.jingrui.dao.UserSQLiteDao;
import com.jingrui.entity.AccumulatePoint;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	private AccumulatePointsDatabaseHelper accumulatePointsDatabaseHelper;
	private String globalName;
	private UserSQLiteDao userSQLiteDao;
	private Button updateNameButton;
	private Spinner monthsSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
		accumulatePointsDatabaseHelper = new AccumulatePointsDatabaseHelper(this, "AccumulatePoints.db", null, 1);
		SQLiteDatabase db = accumulatePointsDatabaseHelper.getWritableDatabase();
		userSQLiteDao = new UserSQLiteDao(db);
		
		updateNameButton = (Button) findViewById(R.id.update_name);
		updateNameButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText nameText = new EditText(MainActivity.this);
			    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			    builder.setTitle("name");
			    builder.setView(nameText);
			    builder.setPositiveButton("ok", new android.content.DialogInterface.OnClickListener() {
					
				    @Override
					public void onClick(DialogInterface dialog, int which) {
					    // TODO Auto-generated method stub
						globalName = nameText.getText().toString();
						userSQLiteDao.updateUserName(globalName);
						new Thread(runnable).start();
					}
				}).setNegativeButton("cancel", null).create().show();;
			}
		});
		
		globalName = userSQLiteDao.getUserName();
		if("".equals(globalName)){
			 final EditText nameText = new EditText(this);
		     AlertDialog.Builder builder = new AlertDialog.Builder(this);
		     builder.setTitle("name");
		     builder.setView(nameText);
		     builder.setPositiveButton("ok", new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					globalName = nameText.getText().toString();
					userSQLiteDao.insertUserName(globalName);
					new Thread(runnable).start();
				}
			}).setNegativeButton("cancel", null).create().show();;
		}else{
			Toast.makeText(this, globalName, Toast.LENGTH_SHORT).show();
			new Thread(runnable).start();
		}
	}
	
	Handler handler = new Handler(){
	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        Bundle data = msg.getData();
	        ArrayList<AccumulatePoint> list = data.getParcelableArrayList("apList");
	        List<String> listString = new ArrayList<String>();
	        for (AccumulatePoint accumulatePoint : list) {
				//Log.d("MainActivity", accumulatePoint.getNo()+","+accumulatePoint.getName()+","+accumulatePoint.getTime()+","+accumulatePoint.getComment()+","+accumulatePoint.getScore()+","+accumulatePoint.getUpdateTime());
	        	listString.add(accumulatePoint.getNo()+"|"+accumulatePoint.getName()+"|"+accumulatePoint.getTime()+"|"+accumulatePoint.getComment()+"|"+accumulatePoint.getScore()+"|"+accumulatePoint.getUpdateTime());
	        	
			}
	        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,listString);
	        ScoreListAdapter adapter = new ScoreListAdapter(MainActivity.this, android.R.layout.simple_list_item_1,listString);
	        ListView listView = (ListView) findViewById(R.id.list_score);
	        listView.setAdapter(adapter);
	        
	        ArrayList<String> months = data.getStringArrayList("monthsList");
	        ArrayAdapter<String> monthsAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, months);
	        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        monthsSpinner =  (Spinner) findViewById(R.id.months);
	        monthsSpinner.setAdapter(monthsAdapter);
	        
	        ProgressBar pb = (ProgressBar) findViewById(R.id.loading_data);
	        pb.setVisibility(View.GONE);
	    }
	};

	
	Runnable runnable = new Runnable(){
		@Override
		public void run() {
			Message msg = new Message();
			Bundle data = new Bundle();
			AccumulatePointsDao apDao = new AccumulatePointsDao();
			
			Date date = new Date();
	        DateFormat format = new SimpleDateFormat("yyyy-MM-");
	        String strDate = format.format(date);
			
			ArrayList<AccumulatePoint> list = apDao.getAccumulatePointsByNameAndMonth(globalName, strDate+"%");
			data.putParcelableArrayList("apList", list);
			
			ArrayList<String> monthsList = apDao.getAccumulatePointsMonthsByName(globalName);
			data.putStringArrayList("monthsList", monthsList);
			
			msg.setData(data);
			handler.sendMessage(msg);
		}
	};
}
