package auca.ac.rw.study_habit_tracker.controller;
import auca.ac.rw.study_habit_tracker.model.StudyRoom;
import auca.ac.rw.study_habit_tracker.service.StudyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;




@RestController 
@RequestMapping("/api/study-rooms")
public class StudyRoomController {
    @Autowired 
    private StudyRoomService studyRoomService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody StudyRoom studyRoom) {
        String message = studyRoomService.saveStudyRoom(studyRoom);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public List<StudyRoom> getAll() {
        return studyRoomService.getAllStudyRooms();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<StudyRoom> room = studyRoomService.getStudyRoomById(id);
        if (room.isPresent()) {
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("The study room with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody StudyRoom studyRoom) {
        String message = studyRoomService.updateStudyRoom(id, studyRoom);
        if (message.equals("Study room updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = studyRoomService.deleteStudyRoom(id);
        if (message.equals("Study room deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/free")
    public List<StudyRoom> getFree() {
        return studyRoomService.getFreeStudyRooms();
    }
}
