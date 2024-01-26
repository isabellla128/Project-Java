package io.openliberty.deepdive.rest.repository;

import java.io.Serializable;
import java.util.List;

import io.openliberty.deepdive.rest.model.Student;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.*;

@ApplicationScoped
public class StudentRepository {

    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Resource
    UserTransaction utx;

    public List<Student> getStudents() {
        return em.createNamedQuery("Student.findAll", Student.class)
                .getResultList();
    }

    public Student getStudent(String name) {
        List<Student> students =
                em.createNamedQuery("Student.findStudent", Student.class)
                        .setParameter("name", name)
                        .getResultList();
        return students == null || students.isEmpty() ? null : students.get(0);
    }

    public Student getStudentByEmail(String email) {
        List<Student> students =
                em.createNamedQuery("Student.findStudentByEmail", Student.class)
                        .setParameter("email", email)
                        .getResultList();
        return students == null || students.isEmpty() ? null : students.get(0);
    }

    public void add(String name, String email, String grade) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException, NotSupportedException {
        utx.begin();
        em.persist(new Student(name, email, grade));
        utx.commit();
    }

}