package com.thoughtworks.merchant.computations;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.merchant.interfaces.ListManager;

//This class maintains a list of objects
public class ListManagerImpl implements ListManager {

	private List<String> objectList = new ArrayList<>();

	@Override
	public void addObject(String object) {
		objectList.add(object);
	}

	@Override
	public List<String> getList() {
		return objectList;
	}

	@Override
	public void setList(List<String> list) {
		objectList = list;
	}
}
