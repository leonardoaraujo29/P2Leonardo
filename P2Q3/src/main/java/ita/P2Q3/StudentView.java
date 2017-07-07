package ita.P2Q3;

public class StudentView extends View{
	@Override
	public void printDetails(String name, String rollNo) {
		System.out.println("Student: ");
		System.out.println("Name: " + name);
		System.out.println("Roll No: " + rollNo);
	}

}
