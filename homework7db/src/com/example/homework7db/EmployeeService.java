package com.example.homework7db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EmployeeService {
	private static final String TAG = "com.example.homework7db.EmployeeService";
	private SQL sqlHelper;

	public EmployeeService(Context ontext) {
		this.sqlHelper = new SQL(ontext);
	}

	public void saveEmployee(Employee employee) {
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", employee.getName());
		values.put("hiredate", employee.getHiredate());
		values.put("ffood", employee.getFfood());
		values.put("fgame", employee.getFgame());
		values.put("sfgame", employee.getSfgame());
		values.put("fcolor", employee.getFcolor());
		values.put("gender", employee.getGender());

		long ins = db.insert("employees", null, values);
		if (ins > 0)
			Log.i(TAG, "row inserted, row# " + ins);
		else
			Log.i(TAG, "row not insertd");
		db.close();
	}

	public void truncate() {
		SQLiteDatabase db = sqlHelper.getWritableDatabase();
		db.execSQL("delete from employees");
		db.close();
	}

	public List<Employee> findAllEmployees() {
		SQLiteDatabase db = this.sqlHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from employees ", null);
		return convertcurtolist(cursor);
	}

	public List<Employee> findEmployeeByWhere(Employee employee) {
		if (null == employee) {
			return this.findAllEmployees();
		} else {
			StringBuffer sb = new StringBuffer();
			ArrayList<String> al = new ArrayList<String>();

			int i = 0;
			sb.append("select * from employees where 1 = 1 ");
			if (employee.getId() != null) {
				sb.append("and id = ?");
				al.add(employee.getId());
			}
			if (employee.getName() != null) {
				sb.append(" and name = ?");
				al.add(employee.getName());
			}
			if (employee.getHiredate() != null) {
				sb.append(" and hiredate = ?");
				al.add(employee.getHiredate());
			}
			if (employee.getHiredate() != null) {
				sb.append(" and hiredate = ?");
				al.add(employee.getHiredate());
			}
			if (employee.getFfood() != null) {
				sb.append(" and ffood = ?");
				al.add(employee.getFfood());
			}
			if (employee.getFgame() != null) {
				sb.append(" and fgame = ?");
				al.add(employee.getFgame());
			}
			if (employee.getSfgame() != null) {
				sb.append(" and sfgame = ?");
				al.add(employee.getSfgame());
			}
			if (employee.getFcolor() != null) {
				sb.append(" and fcolor = ?");
				al.add(employee.getFcolor());
			}
			if (employee.getGender() != null) {
				sb.append(" and gender = ?");
				al.add(employee.getGender());
			}
			int len = al.size();
			String[] args = al.toArray(new String[len]);
			SQLiteDatabase db = this.sqlHelper.getReadableDatabase();
			Cursor cursor = db.rawQuery(sb.toString(), args);
			Log.i(TAG, sb.toString());
			return convertcurtolist(cursor);
		}
	}

	public List<Employee> findEmployee_a() {
		SQLiteDatabase db = this.sqlHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from employees where fgame = ? or sfgame = ?",
				new String[] { "tetris", "tetris" });
		return convertcurtolist(cursor);
	}

	public List<Employee> findEmployee_b() {
		SQLiteDatabase db = this.sqlHelper.getReadableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select * from employees where (fgame = ? or sfgame = ?) and gender = ?",
						new String[] { "pacman", "pacman", "female" });
		return convertcurtolist(cursor);
	}

	public List<Employee> findEmployee_d() {
		List<Employee> list = new ArrayList<Employee>();
		SQLiteDatabase db = this.sqlHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from employees where fgame = ? or sfgame = ?",
				new String[] { "League_of_Legends", "League_of_Legends" });
		List<Employee> l = convertcurtolist(cursor);
		cursor = db.rawQuery(
				"select * from employees where fgame = ? or sfgame = ?",
				new String[] { "call_of_duty", "call_of_duty" });
		List<Employee> l1 = convertcurtolist(cursor);

		for (Employee e : l) {
			if (e.getSfgame().equalsIgnoreCase("mortal_kombat")) {
				list.add(e);
			}
		}
		for (Employee e1 : l1) {
			if (e1.getSfgame().equalsIgnoreCase("mortal_kombat")
					&& (!list.contains(e1))) {
				list.add(e1);
			}
		}
		return list;
	}

	public List<Employee> findEmployee_e() {
		SQLiteDatabase db = this.sqlHelper.getReadableDatabase();
		Cursor cursor = db
				.rawQuery(
						"select * from employees where gender = ? and (fgame != ? and sfgame != ?) order by fcolor ",
						new String[] { "male", "tetris", "tetris" });
		return convertcurtolist(cursor);
	}

	private List<Employee> convertcurtolist(Cursor cursor) {
		List<Employee> l = new ArrayList<Employee>();
		cursor.moveToNext();
		while (cursor.moveToNext()) {
			Employee e = new Employee();
			e.setId(cursor.getString(0));
			e.setName(cursor.getString(1));
			e.setHiredate(cursor.getString(2));
			e.setFfood(cursor.getString(3));
			e.setFgame(cursor.getString(4));
			e.setSfgame(cursor.getString(5));
			e.setFcolor(cursor.getString(6));
			e.setGender(cursor.getString(7));
			l.add(e);
		}
		cursor.close();
		return l;
	}
}
