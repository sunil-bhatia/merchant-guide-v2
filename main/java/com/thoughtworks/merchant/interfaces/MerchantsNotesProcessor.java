package com.thoughtworks.merchant.interfaces;

import java.util.List;

public interface MerchantsNotesProcessor {

	public void processMerchantNotes();

	public List<String> processInputLines(List<String> inputLines);
}