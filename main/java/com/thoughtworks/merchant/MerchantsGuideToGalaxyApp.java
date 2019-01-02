package com.thoughtworks.merchant;

import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.Factory;
import com.thoughtworks.merchant.factory.FactoryImpl;

public class MerchantsGuideToGalaxyApp {
	
	public static void main(String[] args) {
		
		ConfigPropertiesManager configPropertiesManager = new FileConfigPropertiesManager();
		configPropertiesManager.configureProperties(args);

		Factory factory = new FactoryImpl();
		MerchantsNotesProcessorImpl merchantsNotesProcessor = (MerchantsNotesProcessorImpl) factory.getObject("MerchantsNotesProcessor");
		merchantsNotesProcessor.processMerchantNotes();
	}
}