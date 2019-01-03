package com.thoughtworks.merchant.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.thoughtworks.merchant.interfaces.factory.ConfigPropertiesManager;
import com.thoughtworks.merchant.interfaces.factory.GeneralFactory;

public class GeneralFactoryImpl implements GeneralFactory {
	
	private ConfigPropertiesManager configPropertiesManager;

	private static HashMap<String, Object> objectMap = new HashMap<String, Object>();

	@Override
	public Object getObject(String objectName) {

		Object object = null;
		
		String objectScope = configPropertiesManager.getPropertyValue(objectName + "Scope");
		
		if (!objectScope.equals("non-singleton") && objectMap.containsKey(objectName)) {
			object = objectMap.get(objectName);
		} else {

			String className = configPropertiesManager.getPropertyValue(objectName);

			Class<?> classObject = null;

			try {
				classObject = Class.forName(className);
				Constructor<?> constructor = classObject.getConstructor();
				object = constructor.newInstance();
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			//Check for dependencies
			int numberOfDependencies = 0;
			String numberOfDependenciesStr = configPropertiesManager.getPropertyValue(objectName + "NumOfDependencies");
			if (!numberOfDependenciesStr.isEmpty()){
				numberOfDependencies = Integer.parseInt(numberOfDependenciesStr);
			}
			
			//For each dependency, set the dependency
			Object depObject = null;
			for (int i = 0; i < numberOfDependencies; i++) {
				String depObjectName = configPropertiesManager.getPropertyValue(objectName + "Dependency" + (i + 1));

				depObject = getObject(depObjectName);
				try {
					classObject.getMethod("set" + depObjectName, Object.class).invoke(object, depObject);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}

			if (!objectScope.equals("non-singleton")) {
				objectMap.put(objectName, object);
			}
		}

		return object;
	}
	
	@Override
	public void setConfigPropertiesManager(Object configPropertiesManager) {
		this.configPropertiesManager = (ConfigPropertiesManager) configPropertiesManager;
	}
}
