import java.lang.Comparable;

public class Student implements Comparable<Student>{
	
	private int id;
	private String name;
	private String school;
	
	public Student(){
		
	}
	
	public Student(String name, int id, String school){
		this.name = name;
		this.id = id;
		this.school = school;
	}
	
	public int compareTo(Student student2){
		if((this.id - student2.id) == 0){
			return 0;
		}
		else if((this.id - student2.id) < 0){
			return -1;
		}
		else
			return 1;
	}
	
	@Override
	public boolean equals(Object o){
		if(this.id == ((Student)o).id){
			return true;
		}
		else 
			return false;
	}
	
	@Override
	public String toString(){
		return "Name: " + this.name + ". ID: " + this.id + ". School: " + this.school;
	}
	
	
}