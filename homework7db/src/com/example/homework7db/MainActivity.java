package com.example.homework7db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "com.example.homework7db.MainActivity";
	private EmployeeService service;
	private ListView view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view = (ListView)findViewById(R.id.listView);
		service = new EmployeeService(this);
		initDb();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		List<Employee> l;
		switch(id){
		case R.id.a:
			l = service.findEmployee_a();
			disp(l);
			break;
		case R.id.b:
			l = service.findEmployee_b();
			disp(l);
			break;
		case R.id.c:
			l = service.findAllEmployees();
			disp(this.screen(l));
			break;
		case R.id.d:
			l = service.findEmployee_d();
			disp(l);
			break;
		case R.id.e:
			l = service.findEmployee_e();
			disp(l);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void initDb(){
		service.truncate();
		String str="";
		StringBuffer buf = new StringBuffer();			
		//InputStream is = this.getResources().openRawResource(R.drawable.my_base_data);
		InputStream iss = null;
		try {
			iss = MainActivity.this.getResources().getAssets().open("list.csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(iss));
			int i = 0;
			if (iss!=null) 
				{							
				while ((str = reader.readLine()) != null) 
				{	
					if (++i < 3)continue;
					String s[] = str.split(",");
					Employee employee = new Employee();
					employee.setName(s[0]);
					employee.setHiredate(s[1]);
					employee.setFfood(s[2]);
					employee.setFgame(s[3]);
					employee.setSfgame(s[4]);
					employee.setFcolor(s[5]);
					employee.setGender(s[6]);
					service.saveEmployee(employee);
					//buf.append(str + "\n" );
					
					
				}		
				iss.close();
				}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void disp(List<Employee> l){
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for(Employee employee : l){
			HashMap<String, Object> mapitem = new HashMap<String, Object>();
			mapitem.put("id", employee.getId());
			mapitem.put("name", employee.getName());
			mapitem.put("hiredate", employee.getHiredate());
			mapitem.put("ffood", employee.getFfood());
			mapitem.put("fgame", employee.getFgame());
			mapitem.put("sfgame", employee.getSfgame());
			mapitem.put("fcolor", employee.getFcolor());
			mapitem.put("gender", employee.getGender());
			data.add(mapitem);
			Log.i(TAG, mapitem.toString());
		}
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,   
                new String[]{"name", "hiredate", "ffood", "fgame", "sfgame", "fcolor", "gender"}, 
                new int[]{R.id.name, R.id.hiredate, R.id.ffood, R.id.fgame, R.id.sfgame, R.id.fcolor, R.id.gender});  
        view.setAdapter(adapter); 
	}
	public List<Employee> screen(List<Employee> l){
		List<Employee> list = new ArrayList<Employee>();
		for(Employee e : l){
			String hiredate = e.getHiredate();
			String str[] = hiredate.split("/");
			if (Integer.valueOf(str[2]) > 2000){
				list.add(e);
			}
			
		}
		return list;
	}
}
