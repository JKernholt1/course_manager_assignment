package se.lexicon.course_manager_assignment.data.dao;


import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.HashSet;
import java.util.List;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;


    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        // use sequencer
        int studentId = StudentSequencer.nextStudentId();
        Student student = new Student(studentId, name, email, address);
        students.add(student);
        return student;
    }

    //teacherDaoImpl 2a generics
    @Override
    public Student findByEmailIgnoreCase(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Input for email is not valid.");
        } else {
            Student result = null;
            for (Student student : students) {
                if (student.getEmail() == email) {
                    result = student;
                    break;
                } else {
                    System.out.println("Student was not found.");
                }
            }
            return result;
        }

        // for each
        // condition
        // return it

    }

    @Override
    public Collection<Student> findByNameContains(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Input for name is not valid.");

        } else {
            List<Student> result = new ArrayList<>();
            for (Student student : students) {
                String nome = student.getName();
                if (nome.equalsIgnoreCase(name)) {
                    result.add(student);
                } else {
                }
            }
            return result;
        }
    }


    @Override
    public Student findById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Input for id is not valid.");

        } else {
            Student result = null;
            for (Student student : students) {
                if (student.getId() == id) {
                    result = student;
                    break;
                } else {
                    System.out.println("Student was not found.");
                }

            }
            return result;
        }

    }

    @Override
    public Collection<Student> findAll() {
        return students;
    }

    @Override
    public boolean removeStudent(Student student) {
        boolean removedOrNot = false;
        if (students.contains(student) == true) {
            students.remove(student);
            System.out.println("Student: " + student + "\nwas removed from the students list.");
            removedOrNot = true;
            return removedOrNot;
        } else {
            System.out.println("student: " + student + "\nwas not found in the list of students.");
            return removedOrNot;
        }

    }

    @Override
    public void clear() {
        students.clear();
        System.out.println("All students listed for the course are now removed.");
    }
}
