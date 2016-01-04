package com.example.ttttable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class test {

	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();
		l.add(3);
		l.add(1);
		Collections.sort(l);
		for(int i=0;i<l.size();i++){
			System.out.println(l.get(i));
		}
	}

}
