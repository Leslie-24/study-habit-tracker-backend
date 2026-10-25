package auca.ac.rw.study_habit_tracker.service;
import auca.ac.rw.study_habit_tracker.model.StudySession;
import auca.ac.rw.study_habit_tracker.repository.StudyLogRepository;
import auca.ac.rw.study_habit_tracker.repository.StudySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import auca.ac.rw.study_habit_tracker.model.SessionStatus;
import java.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service 
public class StudySessionService {
    @Autowired
    private StudySessionRepository studySessionRepository;
    @Autowired
    private StudyLogRepository studyLogRepository;
    public String saveStudySession(StudySession studySession) {
        if (studySession.getSessionDate().isBefore(LocalDate.now())) {
            return "Study session date cannot be in the past";
        }
        if (studySession.getMentor() != null
                && studySessionRepository.existsByMentorIdAndSessionDateAndStatusNot(
                    studySession.getMentor().getId(), studySession.getSessionDate(), SessionStatus.SKIPPED)) {
            return "Mentor already supervises a session on that date";
        }
        studySession.setStatus(SessionStatus.PLANNED);
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
        session.setGoal(newSession.getGoal());
        session.setPlannedMinutes(newSession.getPlannedMinutes());
        session.setStudent(newSession.getStudent());
        session.setMentor(newSession.getMentor());
        studySessionRepository.save(session);
        return "Study session updated successfully";
    }
    public String deleteStudySession(UUID id) {
        Optional<StudySession> found = studySessionRepository.findById(id);
        if (found.isEmpty()) {
            return "The study session with that id does not exist";
        }
        if (studyLogRepository.existsByStudySession(found.get())) {
            return "That study session has study logs, delete them first";
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
    public String updateStudySessionStatus(UUID id, SessionStatus newStatus) {
        Optional<StudySession> found = studySessionRepository.findById(id);
        if (found.isEmpty()) {
            return "The study session with that id does not exist";
        }
        StudySession session = found.get();
        SessionStatus current = session.getStatus();
        boolean allowed = false;
        if (current == SessionStatus.PLANNED
                && (newStatus == SessionStatus.IN_PROGRESS || newStatus == SessionStatus.SKIPPED)) {
            allowed = true;
        }
        if (current == SessionStatus.IN_PROGRESS
                && (newStatus == SessionStatus.FINISHED || newStatus == SessionStatus.SKIPPED)) {
            allowed = true;
        }
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        session.setStatus(newStatus);
        studySessionRepository.save(session);
        return "Study session status updated successfully";
    }
    public List<Object[]> getSessionCountsByStatus() {
        return studySessionRepository.countSessionsByStatus();
    }
    @Transactional
    public String cancelDay(UUID mentorId, LocalDate date) {
        int count = studySessionRepository.cancelDay(mentorId, date, SessionStatus.SKIPPED, SessionStatus.FINISHED);
        return count + " study sessions cancelled";
    }
    public Page<StudySession> getStudySessionsPage(Pageable pageable) {
        return studySessionRepository.findAll(pageable);
    }
}
