import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Bilguun Batsaikhan	
 * @ID 1512346
 *
 */

public class Project2{

	/**
	 * Read in a file containing student records and create an array of Student
	 * objects
	 * 
	 * @param file The filename
	 * @param num The number of students in the file
	 * @return An array of Student objects
	 */
	
	static Student[] readStudentsFromFile(String filename, int num) {
		
		try (Scanner in = new Scanner(new File(filename))) {
			Student[] students = new Student[num];
			int h = 0;			//counter for students
			while(in.hasNextLine()){
				String line = in.nextLine();
				String [] fields = line.split(",");
				String name = fields[0];
				int id = Integer.parseInt(fields[1]);
				String school = fields[2];
				students[h] = new Student(name, id, school);
				h++;
			}
			Arrays.sort(students);
			return students;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Write an array of Student objects to a file
	 * 
	 * @param students The array of Student objects to write out
	 * @param filename The output filename
	 */
	static void writeStudentsToFile(Student[] students, String filename) {
		try (FileWriter out = new FileWriter(filename)) {
			for(Object student: students){
				out.write(student.toString()+"\n");
			}
			// YOUR CODE HERE
			// Hints:
			// To write a line to the file you can call 
			//		   out.write("Hello World!" + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Find students belonging to both groups.
	 * 
	 * This function checks each student in group1 for membership in group2 by
	 * comparing it with every student in group2.
	 * 
	 * @param group1 A group of Students
	 * @param group2 A group of Students
	 * @param numCommon number of students belonging to both groups
	 * @return An array of Students which belong to both group1 and group2
	 */
	static Student[] findCommonStudents1(Student[] group1, Student[] group2,
			int numCommon) {
		Student[] common = new Student[numCommon];
		int commonCounter = 0;
		for(int i=0; i<group1.length; i++){
			for(int j=0; j<group2.length; j++){
				if(group1[i].equals(group2[j])){
					common[commonCounter] = group1[i];
					commonCounter++;
					break;
				}
			}
		}
		return common;
	}

	/**
	 * Find students belonging to both groups.
	 * 
	 * This function checks each student in group1 for membership in group2 by
	 * doing a binary search on group2.
	 * 
	 * @param group1 A group of Students
	 * @param group2 A group of Students
	 * @param numCommon number of students belonging to both groups
	 * @return An array of Students which belong to both group1 and group2
	 */
	static Student[] findCommonStudents2(Student[] group1, Student[] group2,
			int numCommon) {
		Student[] common = new Student[numCommon];
		int commonCounter = 0;
		for(int i=0; i<group1.length; i++){
			int returnIndex =  Arrays.binarySearch(group2, group1[i]);
			if(returnIndex >= 0){
				common[commonCounter] = group1[i];
				commonCounter++;
			}
		}
		return common;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/***** These files provided to help you with initial testing *****/
//		Student[] uc = readStudentsFromFile("sample_uc_students.txt", 10);
//		Student[] smc = readStudentsFromFile("sample_smc_grads.txt", 5);
//		final int SMC_UC_GradsNumber = 2;
		
		/***** Use these files for the output you submit *****/
		Student[] uc = readStudentsFromFile("uc_students.txt", 350000);
		Student[] smc = readStudentsFromFile("smc_grads.txt", 75000);
		final int SMC_UC_GradsNumber = 25000;

		long start, end;

		start = System.currentTimeMillis();
		Student[] common1 = findCommonStudents1(uc, smc, SMC_UC_GradsNumber);
		end = System.currentTimeMillis();
		System.out.println("Cross checking took " + (end - start) / 1000.0
				+ " seconds.");
		Arrays.sort(common1);
		writeStudentsToFile(common1, "sample_smc_grads_at_uc_1.txt");
		
		
		start = System.currentTimeMillis();
		Student[] common2 = findCommonStudents2(uc, smc, SMC_UC_GradsNumber);
		end = System.currentTimeMillis();
		System.out.println("Using binary search it took " + (end - start)
				/ 1000.0 + " seconds.");
		Arrays.sort(common2);
		writeStudentsToFile(common2, "sample_smc_grads_at_uc_2.txt");
		
	}

}