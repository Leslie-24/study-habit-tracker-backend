package auca.ac.rw.study_habit_tracker.service;
import auca.ac.rw.study_habit_tracker.model.StudySession;
import auca.ac.rw.study_habit_tracker.repository.StudySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import auca.ac.rw.study_habit_tracker.model.SessionStatus;
import java.time.LocalDate;
@Service 
public class StudySessionService {
    @Autowired
    private StudySessionRepository studySessionRepository;
    public String saveStudySession(StudySession studySession) {
        studySessionRepository.save(studySession);
        return "Study session saved successfully";
    }
    public List<StudySession> getAllStudySessions() {
        return studySessionRepository.findAll();
    }
    public Optional<StudySession> getStudySessionById(UUID id) {
        return studySessionRepository.findById(id);
    }
    public String updateStudySession(UUID id, StudySession newSession) {
        Optional<StudySession> found = studySessionRepository.findById(id);
        if (found.isEmpty()) {
            return "The study session with that id does not exist";
        }
        StudySession session = found.get();
        session.setSessionDate(newSession.getSessionDate());
        session.setStatus(newSession.getStatus());
        session.setGoal(newSession.getGoal());
        session.setPlannedMinutes(newSession.getPlannedMinutes());
        session.setStudent(newSession.getStudent());
        session.setMentor(newSession.getMentor());
        studySessionRepository.save(session);
        return "Study session updated successfully";
    }
    public String deleteStudySession(UUID id) {
        if (!studySessionRepository.existsById(id)) {
            return "The study session with that id does not exist";
        }
        studySessionRepository.deleteById(id);
        return "Study session deleted successfully";
    }
    public List<StudySession> getStudySessionsByStatus(SessionStatus status) {
        return studySessionRepository.findByStatusOrderBySessionDateAsc(status);
    }
    public List<StudySession> getStudySessionsBetween(LocalDate start, LocalDate end) {
        return studySessionRepository.findBySessionDateBetweenOrderBySessionDateAsc(start, end);
    }
}
