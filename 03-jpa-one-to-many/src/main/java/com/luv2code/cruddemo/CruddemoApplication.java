package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			//createInstructor(appDAO);
			// findInstructor(appDAO);
			//deleteInstructor(appDAO);
		 	//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			//deleteCourse(appDAO);
		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int id=10;
		System.out.println("Deleteing course "+id);
		appDAO.deleteCourseById(id);
		System.out.println("DONE");
	}

	private void updateCourse(AppDAO appDAO) {
		int id=11;
		System.out.println("Finding course with id "+id);
		Course tempCourse=appDAO.findCourseById(id);
		System.out.println("Updating Instructor id "+id);
		tempCourse.setTitle("Master Flute");
		appDAO.update(tempCourse);
		System.out.println("DONE");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id=1;
		System.out.println("Finding instructor with id "+id);
		Instructor tempInstructor=appDAO.findInstructorById(id);
		System.out.println("Updating Instructor id "+id);
		tempInstructor.setLastName("Buffay");
		appDAO.update(tempInstructor);
		System.out.println("DONE");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id=6;
		System.out.println("Finding instructor id"+id);
		Instructor tempInstructor=appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("tempInstructor "+tempInstructor);
		System.out.println("Associated Courses "+tempInstructor.getCourses());
		System.out.println("DONE");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id=6;
		System.out.println("Finding instructow with id "+id);
		Instructor tempInstructor=appDAO.findInstructorById(id);
		System.out.println("tempInstructor "+tempInstructor);
		List<Course> courses=appDAO.findCoursesByInstructorId(id);
		tempInstructor.setCourses(courses);
		System.out.println("Courses:"+tempInstructor.getCourses());
		System.out.println("DONE");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id=6;
		System.out.println("Finding instructow with id "+id);
		Instructor tempInstructor=appDAO.findInstructorById(id);
		System.out.println("tempInstructor "+tempInstructor);
		//System.out.println("Courses "+tempInstructor.getCourses());
		System.out.println("DONE");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		Instructor tempInstructor=
				new Instructor("Mike","Hannigon","mike@luv2code.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.piano.com/youtube","piano");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		Course tempCourse1=new Course("Piano guide");
		Course tempCourse2=new Course("Flute guide");
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		System.out.println("Saving instructor "+tempInstructor);
		System.out.println("Adding courses "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id=3;
		System.out.println("Deleting instructordetail id:"+id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done");

	}

	private void findInstructorDetail(AppDAO appDAO) {
		//get instructordetail object
		int id=2;
		InstructorDetail templInstructorDetail=appDAO.findInstructorDetailById(id);
		System.out.println("tempInstructorDetail" +templInstructorDetail);
		System.out.println("Associated Instructor" +templInstructorDetail.getInstructor());
		System.out.println("DONE");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id=1;
		System.out.println("Deleting instructor id:"+id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {

		int id=2;
		System.out.println("Finding Instructor Id");
		Instructor tempInstructor=appDAO.findInstructorById(id);
		System.out.println("tempInstructor "+tempInstructor);
		System.out.println("The associated instructordetail only "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO){
		Instructor tempInstructor=
				new Instructor("Ross","Geller","ross@luv2code.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.rossy.com/youtube","luv2code");
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Instructor tempInstructor1=
				new Instructor("Mary","Angela","mary@luv2code.com");

		InstructorDetail tempInstructorDetail1=
				new InstructorDetail("http://www.maryanjela.com/youtube","dancing");
		tempInstructor1.setInstructorDetail(tempInstructorDetail1);

		//save the instructor
		System.out.println("Saving instructor "+tempInstructor);
		appDAO.save(tempInstructor);
		appDAO.save(tempInstructor1);
		System.out.println("Done");


	}
}
