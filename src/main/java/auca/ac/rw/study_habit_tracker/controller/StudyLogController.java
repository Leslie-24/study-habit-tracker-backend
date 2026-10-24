package auca.ac.rw.study_habit_tracker.controller;
import auca.ac.rw.study_habit_tracker.model.StudyLog;
import auca.ac.rw.study_habit_tracker.service.StudyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;



@RestController 
@RequestMapping ("/api/study-logs")
public class StudyLogController {
    @Autowired 
    private StudyLogService studyLogService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID studySessionId, @RequestBody StudyLog studyLog) {
        String message = studyLogService.saveStudyLog(studySessionId, studyLog);
        if (message.equals("Study log saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else if (message.equals("The study session with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public List<StudyLog> getAll() {
        return studyLogService.getAllStudyLogs();
    }  
    @GetMapping("/student/{studentNumber}")
    public List<StudyLog> getByStudent(@PathVariable String studentNumber) {
        return studyLogService.getLogsByStudentNumber(studentNumber);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = studyLogService.deleteStudyLog(id);
        if (message.equals("Study log deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } 
    }
}
