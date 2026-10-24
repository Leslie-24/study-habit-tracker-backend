package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface StudyRoomRepository extends JpaRepository<StudyRoom, UUID> {
    @Query("SELECT r FROM StudyRoom r WHERE NOT EXISTS (SELECT m FROM Mentor m WHERE m.studyRoom = r) ORDER BY r.roomNumber")
    List<StudyRoom> findFreeStudyRooms();
    Optional<StudyRoom> findByRoomNumber(int roomNumber);
}
