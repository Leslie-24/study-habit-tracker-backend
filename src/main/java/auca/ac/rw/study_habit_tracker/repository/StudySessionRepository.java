package auca.ac.rw.study_habit_tracker.repository;
import auca.ac.rw.study_habit_tracker.model.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import auca.ac.rw.study_habit_tracker.model.SessionStatus;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.jpa.repository.Modifying;


public interface StudySessionRepository extends JpaRepository<StudySession, UUID> {
    List<StudySession> findByStatusOrderBySessionDateAsc(SessionStatus status);
    List<StudySession> findBySessionDateBetweenOrderBySessionDateAsc(LocalDate start, LocalDate end);
    boolean existsByMentorIdAndSessionDateAndStatusNot(UUID mentorId, LocalDate sessionDate, SessionStatus status);
    @Query("SELECT s.status, COUNT(s) FROM StudySession s GROUP BY s.status")
    List<Object[]> countSessionsByStatus(); 
    @Modifying
    @Query("UPDATE StudySession s SET s.status = :skipped WHERE s.mentor.id = :mentorId AND s.sessionDate = :date AND s.status <> :finished")
    int cancelDay(@Param("mentorId") UUID mentorId, @Param("date") LocalDate date,
                  @Param("skipped") SessionStatus skipped, @Param("finished") SessionStatus finished);
    long countBySessionDate(LocalDate sessionDate);
    long countByStatus(SessionStatus status);
}
