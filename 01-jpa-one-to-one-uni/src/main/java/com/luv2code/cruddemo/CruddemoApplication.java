package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			//createInstructor(appDAO);
			 findInstructor(appDAO);
			//deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id=1;
		System.out.println("Deleting instructor id:"+id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {

		int id=4;
		System.out.println("Finding Instructor Id");
		Instructor tempInstructor=appDAO.findInstructorById(id);
		System.out.println("tempInstructor "+tempInstructor);
		//System.out.println("The associated instructordetail only "+tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO){
		/*Instructor tempInstructor=
				new Instructor("Chad","Darby","darby@luv2code.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.luv2code.com/youtube","luv2code");
		tempInstructor.setInstructorDetail(tempInstructorDetail);
*/
		Instructor tempInstructor=
				new Instructor("Mary","Angela","mary@luv2code.com");

		InstructorDetail tempInstructorDetail=
				new InstructorDetail("http://www.maryanjela.com/youtube","dancing");
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//save the instructor
		System.out.println("Saving instructor "+tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done");


	}
}
