package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.Mentor;
import auca.ac.rw.study_habit_tracker.model.StudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface MentorRepository extends JpaRepository<Mentor, UUID> {
    @Query("SELECT m FROM Mentor m JOIN m.subjects s WHERE LOWER(s.name) = LOWER(:name)")
    List<Mentor> findMentorsBySubjectName(@Param("name") String name);
    boolean existsByStudyRoom(StudyRoom studyRoom);
}
