package auca.ac.rw.study_habit_tracker.controller;
import auca.ac.rw.study_habit_tracker.model.Subject;
import auca.ac.rw.study_habit_tracker.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;



@RestController 
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired 
    private SubjectService subjectService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Subject subject) {
        String message = subjectService.saveSubject(subject);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects(){
        List<Subject> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
       }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable UUID id){
        Optional<Subject> subject = subjectService.getSubjectById(id);
        if(subject.isPresent()){
            return new ResponseEntity<>(subject.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The subject with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Subject subject) {
        String message = subjectService.updateSubject(id, subject);
        if (message.equals("Subject updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = subjectService.deleteSubject(id);
        if (message.equals("Subject deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
