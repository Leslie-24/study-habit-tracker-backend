package auca.ac.rw.study_habit_tracker.service;
import auca.ac.rw.study_habit_tracker.model.Student;
import auca.ac.rw.study_habit_tracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired 
    private StudentRepository studentRepository;
    public String saveStudent(Student student) {
        studentRepository.save(student);
        return "Student saved successfully";
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }
    public String updateStudent(UUID id, Student newStudent) {
        Optional<Student> found = studentRepository.findById(id);
        if (found.isEmpty()) {
            return "The student with that id does not exist";
        }
        Student student = found.get();
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setStudentNumber(newStudent.getStudentNumber());
        student.setProgramme(newStudent.getProgramme());
        studentRepository.save(student);
        return "Student updated successfully";
    }
    public String deleteStudent(UUID id) {
        if (!studentRepository.existsById(id)) {
            return "The student with that id does not exist";
        }
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
    public List<Student> getStudentsByLastName(String lastName) {
    return studentRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
}
}
