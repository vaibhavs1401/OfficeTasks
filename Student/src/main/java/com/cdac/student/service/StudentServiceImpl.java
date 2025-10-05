package com.cdac.student.service;

import com.cdac.student.dao.StudentDao;
import com.cdac.student.dao.UserAccountDao;
import com.cdac.student.entity.Student;
import com.cdac.student.entity.UserAccount;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentDao students;
    private final UserAccountDao users;

    public StudentServiceImpl(StudentDao students, UserAccountDao users) {
        this.students = students;
        this.users = users;
    }


    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Student findByUserId(Long userId) {
        return students.findByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found for user id: " + userId));
    }

    @Override
    public void updateOwnProfile(Long userId, Student form) {
        Student entity = students.findByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("Student not found for user id: " + userId));

        if (form.getId() != null && !form.getId().equals(entity.getId())) {
            throw new AccessDeniedException("You can only update your own profile.");
        }

        copyEditableFields(form, entity);
        students.save(entity);
    }

    private void copyEditableFields(Student src, Student dest) {
        if (src.getName() != null) dest.setName(src.getName());
        if (src.getRollNo() != null) dest.setRollNo(src.getRollNo());
        if (src.getAge() != 0) dest.setAge(src.getAge());
        if (src.getStandard() != 0) dest.setStandard(src.getStandard());
    }

    // ---------- shared reads ----------

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Student getStudentByRollNo(String rollNo) {
        return students.findByRollNo(rollNo)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + rollNo));
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Student findByRollNo(String rollNo) {
        return getStudentByRollNo(rollNo);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Student> getAllStudents() {
        return students.findAllOrdered();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Student> getStudentClassWise(int standard) {
        return students.findByStandardOrdered(standard);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Student> findStudentsByStd(int standard) {
        return students.findByStandardOrdered(standard);
    }

    // ---------- admin-managed writes ----------

    @Override
    public Student addStudent(Student student) {
        if (student.getAccount() == null || student.getAccount().getId() == null) {
            throw new IllegalArgumentException("Student must be linked to an existing UserAccount.");
        }

        UserAccount ua = users.findById(student.getAccount().getId())
            .orElseThrow(() -> new IllegalArgumentException(
                "UserAccount not found: " + student.getAccount().getId()));

        students.findByUserId(ua.getId()).ifPresent(s -> {
            throw new IllegalStateException("A student already exists for this user account.");
        });

        student.setAccount(ua);
        return students.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        if (student.getId() == null && (student.getRollNo() == null || student.getRollNo().isBlank())) {
            throw new IllegalArgumentException("Provide student id or rollNo for update.");
        }

        Student entity = (student.getId() != null)
            ? students.findById(student.getId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found: id=" + student.getId()))
            : students.findByRollNo(student.getRollNo())
                .orElseThrow(() -> new IllegalArgumentException("Student not found: rollNo=" + student.getRollNo()));

        if (student.getName() != null) entity.setName(student.getName());
        if (student.getRollNo() != null) entity.setRollNo(student.getRollNo());
        if (student.getAge() != 0) entity.setAge(student.getAge());
        if (student.getStandard() != 0) entity.setStandard(student.getStandard());

        if (student.getAccount() != null && student.getAccount().getId() != null) {
            Long newUserId = student.getAccount().getId();

            students.findByUserId(newUserId).ifPresent(existing -> {
                if (!existing.getId().equals(entity.getId())) {
                    throw new IllegalStateException("Another student already linked to user " + newUserId);
                }
            });

            UserAccount ua = users.findById(newUserId)
                .orElseThrow(() -> new IllegalArgumentException("UserAccount not found: " + newUserId));
            entity.setAccount(ua);
        }

        return students.save(entity);
    }

    @Override
    public void deleteStudentByRollNo(String rollNo) {
        Student s = students.findByRollNo(rollNo)
            .orElseThrow(() -> new IllegalArgumentException("Student not found: " + rollNo));
        students.delete(s);
    }
}
