package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

public class Course {

    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private Collection<Student> students;


    public Course(int courseId, String courseName, LocalDate startDate, int weekDuration) {

    }

    public Course(int id, String courseName, int weekDuration, Collection students) {

        this.id = CourseSequencer.nextCourseId();
        this.courseName = courseName;
        this.weekDuration = weekDuration;
        this.students = students;
    }

    public int getId() {
        return id;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }


    public boolean enrollStudent(Student student) {
        boolean enrolledOrNot = false;

        if (student != null ) {
            students.add(student);
            System.out.println("Student " + student + " was added to the course.");
            enrolledOrNot = true;
            return enrolledOrNot;
        } else {
            System.out.println("Student without any parameters was not added to the course.");
            return enrolledOrNot;
        }

    }

  /*
should be used to add a Student.class object to Collection<Student> students.
Make sure you avoid adding a duplicate or null into the Collection.
Should return true when student was successfully added, otherwise false.
//Student(int id, String name, String email, String address)
*/

    public boolean unenrollStudent(Student student) {
    boolean unenrolledOrNot = false;
        if (students.contains(student) == true){
            students.remove(student);
            System.out.println("Student " + student + " was found and removed from the course.");
            unenrolledOrNot = true;
            return unenrolledOrNot;
        } else {
            System.out.println("Student " + student + " was not found in the course.");
            return unenrolledOrNot;
        }

    }//unenroll

/*
public boolean unenrollStudent(Student student) should be used to remove a Student.class object from Collection<Student> students.
Returns true when the Student object was successfully removed.
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && weekDuration == course.weekDuration && Objects.equals(courseName, course.courseName) && Objects.equals(startDate, course.startDate) && Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Student{" +
                "Student id = " + id +
                ", Course name = " + courseName +
                ", Start date = " + startDate +
                ", Week duration = " + weekDuration +
                ", Students = " + students +
                " " + super.toString() +
                '}';
    }

}

