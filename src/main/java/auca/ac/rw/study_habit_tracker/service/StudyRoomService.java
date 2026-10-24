package auca.ac.rw.study_habit_tracker.service;
import auca.ac.rw.study_habit_tracker.model.StudyRoom;
import auca.ac.rw.study_habit_tracker.repository.StudyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudyRoomService {
    
    @Autowired 
    private StudyRoomRepository studyRoomRepository;

    public String saveStudyRoom(StudyRoom studyRoom) {
        studyRoomRepository.save(studyRoom);
        return "Study room saved successfully";
    }

    public List<StudyRoom> getAllStudyRooms() {
        return studyRoomRepository.findAll();
    }

    public Optional<StudyRoom> getStudyRoomById(UUID id) {
        return studyRoomRepository.findById(id);
    }
    public String updateStudyRoom(UUID id, StudyRoom newStudyRoom) {
        Optional<StudyRoom> found = studyRoomRepository.findById(id);
        if (found.isEmpty()) {
            return "The study room with that id does not exist";
        }
        StudyRoom room = found.get();
        room.setRoomNumber(newStudyRoom.getRoomNumber());
        room.setName(newStudyRoom.getName());
        studyRoomRepository.save(room);
        return "Study room updated successfully";
    }

    public String deleteStudyRoom(UUID id) {
        if (!studyRoomRepository.existsById(id)) {
            return "The study room with that id does not exist";
        }
        studyRoomRepository.deleteById(id);
        return "Study room deleted successfully";
    }
    public List<StudyRoom> getFreeStudyRooms() {
        return studyRoomRepository.findFreeStudyRooms();
    }
}
