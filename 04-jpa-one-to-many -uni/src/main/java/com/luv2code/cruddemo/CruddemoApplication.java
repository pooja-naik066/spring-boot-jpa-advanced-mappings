package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
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
    //createCourseAndReviews(appDAO);

	//retrieveCourseAndReviews(appDAO);

	//deleteCourseAndReviews(appDAO);
			findCourses(appDAO);



		};
	}

	private void findCourses(AppDAO appDAO) {
		Course c= appDAO.findCourseById(1);
		System.out.println(c);
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id=10;
		System.out.println("Deleting course with id "+id);
		appDAO.deleteCourseById(id);
		System.out.println("DONEE");

	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id=1;
		Course course=appDAO.findCourseAndReviewsByCourseId(id);
		System.out.println("Course "+course);
		System.out.println("Reviews "+course.getReviews());
		System.out.println("DONE!!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course=new Course("Learn Python in 30 days");
		course.addReview(new Review("Excellent course"));
		course.addReview(new Review("Organized well"));
		course.addReview(new Review("Great Instructor"));

		System.out.println("Saving the course..."+course);
		System.out.println("Reviews "+course.getReviews());
		appDAO.save(course);
		System.out.println("DONE!!");

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
		int id=1;
		System.out.println("Finding instructor id"+id);
		Instructor tempInstructor=appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("tempInstructor "+tempInstructor);
		System.out.println("Associated Courses "+tempInstructor.getCourses());
		System.out.println("DONE");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id=1;
		System.out.println("Finding instructow with id "+id);
		Instructor tempInstructor=appDAO.findInstructorById(id);
		System.out.println("tempInstructor "+tempInstructor);
		List<Course> courses=appDAO.findCoursesByInstructorId(id);
		tempInstructor.setCourses(courses);
		System.out.println("Courses:"+tempInstructor.getCourses());
		System.out.println("DONE");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id=1;
		System.out.println("Finding instructow with id "+id);
		Instructor tempInstructor=appDAO.findInstructorById(id);
		System.out.println("tempInstructor "+tempInstructor);
		System.out.println("Courses "+tempInstructor.getCourses());
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
				new Instructor("Chad","Darby","darby@luv2code.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.luv2code.com/youtube","luv2code");
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
