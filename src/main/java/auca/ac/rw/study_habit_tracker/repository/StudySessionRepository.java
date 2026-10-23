package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import auca.ac.rw.study_habit_tracker.model.SessionStatus;
import java.time.LocalDate;

public interface StudySessionRepository extends JpaRepository<StudySession, UUID> {
    List<StudySession> findByStatusOrderBySessionDateAsc(SessionStatus status);
    List<StudySession> findBySessionDateBetweenOrderBySessionDateAsc(LocalDate start, LocalDate end);
}
