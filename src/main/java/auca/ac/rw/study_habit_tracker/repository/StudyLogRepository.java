package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.StudyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import auca.ac.rw.study_habit_tracker.model.StudySession;

public interface StudyLogRepository extends JpaRepository <StudyLog, UUID> {
    @Query("SELECT l FROM StudyLog l WHERE l.studySession.student.studentNumber = :studentNumber ORDER BY l.loggedDate DESC")
    List<StudyLog> findLogsByStudentNumber(@Param("studentNumber") String studentNumber);
    boolean existsByStudySession(StudySession studySession);
}
