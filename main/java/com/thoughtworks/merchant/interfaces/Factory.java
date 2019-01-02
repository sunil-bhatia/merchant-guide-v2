package com.thoughtworks.merchant.interfaces;

import com.thoughtworks.merchant.MerchantsNotesProcessor;
import com.thoughtworks.merchant.interfaces.Line;

public interface Factory {

	public MerchantsNotesProcessor createMerchantsNotesProcessor();

	public Line getLineObject(String line);

	public Object getObject(String objectName);
}
