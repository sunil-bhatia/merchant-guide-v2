package com.thoughtworks.merchant;

import com.thoughtworks.merchant.factory.FileConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.MerchantsNotesProcessor;
import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.factory.GeneralFactory;

public class MerchantsGuideToGalaxyApp {
	
	public static void main(String[] args) {
		
		ConfigPropertiesManager configPropertiesManager = new FileConfigPropertiesManager();
		configPropertiesManager.readConfigProperties(args);

		GeneralFactory factory = configPropertiesManager.getGeneralFactoryObject();

		MerchantsNotesProcessor merchantsNotesProcessor = (MerchantsNotesProcessor) factory.getObject("MerchantsNotesProcessor");
		merchantsNotesProcessor.processMerchantNotes();
	}
}