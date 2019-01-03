package com.thoughtworks.merchant.interfaces.factory;

import com.thoughtworks.merchant.interfaces.Line;

public interface LineFactory {

	public Line getLineObject(String line);
}
