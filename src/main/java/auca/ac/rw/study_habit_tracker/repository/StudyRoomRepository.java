package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StudyRoomRepository extends JpaRepository<StudyRoom, UUID> {
    
}
