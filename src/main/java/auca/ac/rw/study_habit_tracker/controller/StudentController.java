package auca.ac.rw.study_habit_tracker.controller;
import auca.ac.rw.study_habit_tracker.model.Student;
import auca.ac.rw.study_habit_tracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;




@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired 
    private StudentService studentService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Student student) {
        String message = studentService.saveStudent(student);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The student with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Student student) {
        String message = studentService.updateStudent(id, student);
        if (message.equals("Student updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = studentService.deleteStudent(id);
        if (message.equals("Student deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by-last-name")
    public List<Student> getByLastName(@RequestParam String lastName) {
        return studentService.getStudentsByLastName(lastName);
    }
}
