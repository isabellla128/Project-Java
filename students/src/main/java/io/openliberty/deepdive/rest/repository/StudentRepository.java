package io.openliberty.deepdive.rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.openliberty.deepdive.rest.entities.Student;
import io.openliberty.deepdive.rest.models.StudentDTO;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;

@ApplicationScoped
public class StudentRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Resource
    UserTransaction utx;

    public List<StudentDTO> getStudents() {
        return (em.createNamedQuery("Student.findAll",Student.class).getResultList())
                .stream()
                .map(StudentDTO::new)
                .collect(Collectors.toList());
    }

    public List<String> getStudentsNames() {
        return (em.createNamedQuery("Student.findAll",Student.class).getResultList())
                .stream()
                .map(Student::getUsername)
                .collect(Collectors.toList());
    }

    public Student getStudent(String name) {
        List<Student> students =
                em.createNamedQuery("Student.findStudent", Student.class)
                        .setParameter("name", name)
                        .getResultList();
        return students == null || students.isEmpty() ? null : students.get(0);
    }

    public String getGradeOfAStudent(String studentUsername) {
        return String.valueOf(em.createQuery("SELECT e.grade FROM Student e WHERE e.username = ?1")
                .setParameter(1, studentUsername).getResultList().get(0));

    }

    public Student getStudentByUsername(String username) {
        List<Student> students =
                em.createNamedQuery("Student.findStudentByUsername", Student.class)
                        .setParameter("username", username)
                        .getResultList();
        return students == null || students.isEmpty() ? null : students.get(0);
    }

    public void add(String name, String username, String grade) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException, NotSupportedException {
        utx.begin();
        em.persist(new Student(name, username, grade));
        utx.commit();
    }

}