package lesson1;

public class Student {
    private int id;
	private String name;
    private int age;
    static int NoofStudents = 0;
    
    Student() {
    	NoofStudents++ ;
    }
    
    public static int getNoofStudents() {
    	return NoofStudents;
    }
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


}