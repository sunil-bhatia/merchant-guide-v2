package com.thoughtworks.merchant;

import com.thoughtworks.merchant.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.factory.Factory;

public class MerchantsGuideToGalaxyApp {
	
	public static void main(String[] args) {
		
		// Configure the properties like Dependency Injection classes etc
		ConfigPropertiesManager.configureProperties(args);

		MerchantsNotesProcessor merchantsNotesProcessor = Factory.createMerchantsNotesProcessor();
		merchantsNotesProcessor.processMerchantNotes();
	}
}