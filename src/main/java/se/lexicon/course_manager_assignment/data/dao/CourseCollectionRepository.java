package se.lexicon.course_manager_assignment.data.dao;


import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java.util.HashSet;


public class CourseCollectionRepository implements CourseDao {

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        int courseId = CourseSequencer.nextCourseId();
        Course course = new Course(courseId, courseName, startDate, weekDuration);
        courses.add(course);
        System.out.println("The course " + course + " was added to courses.");
        return course;
    }

    @Override
    public Course findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Input value for id is not valid.");
        }
        Course result = null;
        for (Course course : courses) {
            if (course.getId() == id) {
                result = course;
                break;
            }
        }
        return result;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Input for name is not valid.");

        } else {
            Course result = null;
            for (Course course : courses) {
                if (course.getCourseName() == name) {
                    result = course;
                    return (Collection<Course>) result;

                } else {
                    System.out.println("Course was not found.");
                    return (Collection<Course>) result;
                }
            }
        }
        return null;

    }


    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        if (end == null) {
            throw new IllegalArgumentException("Input for date is not valid.");

        } else {
            Course result = null;

            for (Course course : courses) {
                int extraDagar = (course.getWeekDuration() * 7);
                LocalDate start = course.getStartDate();
                if (start.plusDays(extraDagar) == end) {
                    result = course;
                    return (Collection<Course>) result;

                } else {
                    System.out.println("No course was found at that exact date.");
                    return null;

                }
            }
        }
        return null;
    }


    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        if (start == null) {
            throw new IllegalArgumentException("Input for date is not valid.");

        } else {
            Course result = null;
            for (Course course : courses) {
                if (course.getStartDate() == start) {
                    result = course;
                    return (Collection<Course>) result;

                } else {
                    System.out.println("No course was found at that exact date.");
                    return null;

                }
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findAll() {

   /* int alla = (courses.size()*4);
        System.out.println("The courses found are: \n");
    Object[] ett = courses.toArray();
        for (int i = 0; i < alla ; i++){
            System.out.print(ett[i]+"\t");
            if ((i+1) % 4 == 0){
                System.out.println("");
            }
        }
    */
        return courses;
    }

    @Override
    public List<Student> findByStudentId(int studentId) {
//        int alla = courses.size();
//        Object[] ett = courses.toArray();
//        if (courses.containsAll(student.getId()) == studentId) {

        if (studentId <= 0) {
            throw new IllegalArgumentException("Input for id is not valid.");

        } else {

            List<Student> result = new ArrayList<>();
            List<Student> members;

            for (Course course : courses) {

                members = (List<Student>) course.getStudents();
                for (Student student : members)
                    if (student.getId() == studentId) {
                        result.add(student);
                    } else {
                    }
            }

            return result;
        }


    }

    @Override
    public boolean removeCourse(Course course) {
        boolean removedOrNot = false;
        if (courses.contains(course) == true) {
            courses.remove(course);
            System.out.println("Course: " + course + "\nwas removed from the list of courses.");
            removedOrNot = true;
            return removedOrNot;
        } else {
            System.out.println("Course: " + course + "\nwas not found in the list of courses.");
            return removedOrNot;
        }
    }

    @Override
    public void clear() {
        courses.clear();
        System.out.println("All listed courses are now removed.");
    }

}
