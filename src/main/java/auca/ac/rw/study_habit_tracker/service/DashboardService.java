package auca.ac.rw.study_habit_tracker.service;
import auca.ac.rw.study_habit_tracker.model.SessionStatus;
import auca.ac.rw.study_habit_tracker.repository.MentorRepository;
import auca.ac.rw.study_habit_tracker.repository.StudentRepository;
import auca.ac.rw.study_habit_tracker.repository.StudyLogRepository;
import auca.ac.rw.study_habit_tracker.repository.StudyRoomRepository;
import auca.ac.rw.study_habit_tracker.repository.StudySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service 
public class DashboardService {
    @Autowired 
    private StudentRepository studentRepository;
    @Autowired 
    private MentorRepository mentorRepository;
    @Autowired 
    private StudyRoomRepository studyRoomRepository;
    @Autowired 
    private StudySessionRepository studySessionRepository;
    @Autowired 
    private StudyLogRepository studyLogRepository;
    public Map<String, Long> getSummary(){
        Map<String, Long> summary= new LinkedHashMap<>();
        summary.put("students", studentRepository.count());
        summary.put("mentors",mentorRepository.count());
        summary.put("freeStudyRooms", (long) studyRoomRepository.findFreeStudyRooms().size());
        summary.put("sessionsToday", studySessionRepository.countBySessionDate(LocalDate.now()));
        summary.put("planned", studySessionRepository.countByStatus(SessionStatus.PLANNED));
        summary.put("studylogs", studyLogRepository.count());
        return summary;
    }
}
