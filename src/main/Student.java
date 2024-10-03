package main;

public abstract class Student {

	private final String smcid;
	private final String lastName;
	private final String firstName;
	
	public Student(String smcid, String lastName, String firstName) {
		super();
		this.smcid = smcid;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String toString() {
		return this.lastName + ", " + this.firstName + " (" + this.smcid + ")";
	}
	
}
