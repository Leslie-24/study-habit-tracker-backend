package auca.ac.rw.study_habit_tracker.controller;
import auca.ac.rw.study_habit_tracker.model.Mentor;
import auca.ac.rw.study_habit_tracker.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/mentors")
public class MentorController {
    @Autowired 
    private MentorService mentorService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Mentor mentor) {
        String message = mentorService.saveMentor(mentor);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public List<Mentor> getAll() {
        return mentorService.getAllMentors();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Mentor> mentor = mentorService.getMentorById(id);
        if (mentor.isPresent()) {
            return new ResponseEntity<>(mentor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The mentor with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Mentor mentor) {
        String message = mentorService.updateMentor(id, mentor);
        if (message.equals("Mentor updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = mentorService.deleteMentor(id);
        if (message.equals("Mentor deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
