package ita.P2Q3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ControllerFactory {

	public Controller buildController(String type,Person model,View view) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Class<?> c = Class.forName("ita.P2Q3." + type + "Controller");
		Constructor<?> cons[] = c.getConstructors();
		Controller controller = (Controller) cons[0].newInstance(model,view);
		return controller;
	}
}
