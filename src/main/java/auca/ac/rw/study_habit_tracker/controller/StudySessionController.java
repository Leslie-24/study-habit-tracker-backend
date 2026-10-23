package auca.ac.rw.study_habit_tracker.controller;
import auca.ac.rw.study_habit_tracker.model.StudySession;
import auca.ac.rw.study_habit_tracker.service.StudySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import auca.ac.rw.study_habit_tracker.model.SessionStatus;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/study-sessions")
public class StudySessionController {
    @Autowired 
    private StudySessionService studySessionService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody StudySession studySession) {
        String message = studySessionService.saveStudySession(studySession);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public List<StudySession> getAll() {
        return studySessionService.getAllStudySessions();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<StudySession> session = studySessionService.getStudySessionById(id);
        if (session.isPresent()) {
            return new ResponseEntity<>(session.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The study session with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody StudySession studySession) {
        String message = studySessionService.updateStudySession(id, studySession);
        if (message.equals("Study session updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = studySessionService.deleteStudySession(id);
        if (message.equals("Study session deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by-status")
    public List<StudySession> getByStatus(@RequestParam SessionStatus status) {
        return studySessionService.getStudySessionsByStatus(status);
    }
    @GetMapping("/between")
    public List<StudySession> getBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return studySessionService.getStudySessionsBetween(startDate, endDate);
    }
}
