package ita.P2Q3;

import java.lang.reflect.InvocationTargetException;

public class MVCPatternDemo {
	public static void main(String[] args) {
		ControllerFactory factory = new ControllerFactory();
		// fetch student record based on his roll no from the database
		Student studentModel = retriveStudentFromDatabase();
		Teacher teacherModel = retriveTeacherFromDatabase();
		// Create a view : to write student details on console
		StudentView studentView = new StudentView();
		TeacherView teacherView = new TeacherView();
		Controller controller;
		try{
			controller = factory.buildController("Teacher",teacherModel,teacherView);
			controller.updateView();

			// update model data
			controller.setName("John");

			controller.updateView();
			controller = factory.buildController("Student",studentModel,studentView);
			controller.updateView();

			// update model data
			controller.setName("Zack");

			controller.updateView();
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Student retriveStudentFromDatabase() {
		Student student = new Student();
		student.setName("Robert");
		student.setRollNo("10");
		return student;
	}
	
	private static Teacher retriveTeacherFromDatabase() {
		Teacher teacher = new Teacher();
		teacher.setName("Dan");
		teacher.setRollNo("12");
		return teacher;
	}

}
