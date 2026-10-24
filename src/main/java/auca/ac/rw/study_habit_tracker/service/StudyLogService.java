package auca.ac.rw.study_habit_tracker.service;
import auca.ac.rw.study_habit_tracker.model.SessionStatus;
import auca.ac.rw.study_habit_tracker.model.StudyLog;
import auca.ac.rw.study_habit_tracker.model.StudySession;
import auca.ac.rw.study_habit_tracker.repository.StudyLogRepository;
import auca.ac.rw.study_habit_tracker.repository.StudySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudyLogService {
    @Autowired 
    private StudyLogRepository studyLogRepository;
    @Autowired 
    private StudySessionRepository studySessionRepository;
    public String saveStudyLog(UUID studySessionId, StudyLog studyLog){
        Optional<StudySession> found = studySessionRepository.findById(studySessionId);
        if (found.isEmpty()){
            return "The study session with that id does not exist";
        }
        StudySession session = found.get();
        if (session.getStatus() != SessionStatus.FINISHED) {
            return "Study logs are only for FINISHED study sessions";
        }
        studyLog.setLoggedDate(LocalDate.now());
        studyLog.setStudySession(session);
        studyLogRepository.save(studyLog);
        return "Study log saved successfully";
    }
    public List<StudyLog> getAllStudyLogs(){
        return studyLogRepository.findAll();
    }
    public List<StudyLog> getLogsByStudentNumber(String studentNumber) {
        return studyLogRepository.findLogsByStudentNumber(studentNumber);
    }
    public String deleteStudyLog(UUID id) {
    if (!studyLogRepository.existsById(id)) {
        return "The study log with that id does not exist";
    }
    studyLogRepository.deleteById(id);
        return "Study log deleted successfully";
    }
}
