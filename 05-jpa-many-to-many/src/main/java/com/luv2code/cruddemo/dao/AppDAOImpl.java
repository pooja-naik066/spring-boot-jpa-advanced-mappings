package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
       entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        //retrieve the instructor
        Instructor tempInstructor=entityManager.find(Instructor.class,id);

        //get the courses
        List<Course> courses=tempInstructor.getCourses();

        //break association of all courses for the instructor
        for(Course tempCourse: courses){
            tempCourse.setInstructor(null);
        }

        entityManager.remove(tempInstructor);

        //delete
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail=entityManager.find(InstructorDetail.class,id);
        //remove the associated object reference
        //break bidirectional link
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query=entityManager.createQuery(
                "from Course where instructor.id=:data",Course.class);
        query.setParameter("data",id);
        List<Course> courses=query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create query
        TypedQuery<Instructor> query=entityManager.createQuery(
                                    "select i from Instructor i "
                                    + "JOIN FETCH i.courses "
                                    +"JOIN FETCH i.instructorDetail "
                                    + "where i.id=:data",Instructor.class);

        query.setParameter("data",theId);
        Instructor instructor=query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class,id);

    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
      Course tempCourse=entityManager.find(Course.class,id);
      entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);

    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query=entityManager.createQuery(
                "select c from Course c "
                +" JOIN FETCH c.reviews "
                +"where c.id=:data",Course.class);
        query.setParameter("data",id);
        Course course=query.getSingleResult();
         return course;


    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query=entityManager.createQuery(
                "select c from Course c "
                        +" JOIN FETCH c.students "
                        +"where c.id=:data",Course.class);
        query.setParameter("data",id);
        Course course=query.getSingleResult();
        return course;
    }

    @Override
    public Student findCourseAndStudentsByStudentsId(int id) {
        TypedQuery<Student> query=entityManager.createQuery(
                "select s from Student s "
                        +" JOIN FETCH s.courses "
                        +"where s.id=:data",Student.class);
        query.setParameter("data",id);
        Student student=query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student student) {
     entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student=entityManager.find(Student.class,id);
        entityManager.remove(student);

    }
}
