package com.thoughtworks.merchant.lines.listmanagers;

import java.util.List;

public interface ListManager {
	
	public void addObject(String object);

	public List<String> getList();

	public void setList(List<String> list);

}
