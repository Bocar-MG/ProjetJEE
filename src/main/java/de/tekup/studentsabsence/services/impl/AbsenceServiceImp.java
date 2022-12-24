package de.tekup.studentsabsence.services.impl;

import de.tekup.studentsabsence.entities.Absence;
import de.tekup.studentsabsence.repositories.AbsenceRepository;
import de.tekup.studentsabsence.services.AbsenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AbsenceServiceImp implements AbsenceService {
    private final AbsenceRepository absenceRepository;

    @Override
    public List<Absence> getAllAbsences() {
        List<Absence> absences = new ArrayList<>();
        absenceRepository.findAll().forEach(absences::add);
        return absences;
    }

    @Override
    public List<Absence> getAllAbsencesByGroupId(Long id) {
        List<Absence> absences = new ArrayList<>();
        absenceRepository.findAllByStudent_Group_Id(id).forEach(absences::add);
        return absences;
    }

    @Override
    public List<Absence> getAllAbsencesByStudentId(Long sid) {
        List<Absence> absences = new ArrayList<>();
        //TODO complete the missing instructions : completed
        absences = absenceRepository.findAllByStudent_Sid(sid);
        return absences;
    }

    @Override
    public List<Absence> getAllAbsencesByStudentIdAndSubjectId(Long sid, Long id) {
        List<Absence> absences = new ArrayList<>();
        //TODO complete the missing instructions : completed
        absences = absenceRepository.findAllByStudent_SidAndSubject_Id(sid,id);
        return absences;
    }

    @Override
    public List<Absence> getAllAbsencesByGroupIdAndSubjectId(Long gid, Long id) {
        List<Absence> absences = new ArrayList<>();
        absenceRepository.findAllByStudent_Group_IdAndSubject_Id(gid, id).forEach(absences::add);
        return absences;
    }

    @Override
    public Absence getAbsenceById(Long id) {
        return absenceRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Absence With ID: " + id));
    }

    @Override
    public Absence addAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    @Override
    public Absence deleteAbsence(Long id) {
        Absence absence = getAbsenceById(id);
        absenceRepository.delete(absence);
        return absence;
    }

    @Override
    public float hoursCountByStudent(Long sid) {
        List<Absence> absences = getAllAbsencesByStudentId(sid);
        return countHours(absences);
    }

    @Override
    public float hoursCountByGroupAndSubject(Long gid, Long id) {
        List<Absence> absences = getAllAbsencesByGroupIdAndSubjectId(gid, id);
        return countHours(absences);
    }

    @Override
    public float hoursCountByStudentAndSubject(Long sid, Long id) {
        List<Absence> absences = getAllAbsencesByStudentIdAndSubjectId(sid, id);
        return countHours(absences);
    }
    //TODO Complete the countHours method : completed
    public float countHours(List<Absence> absences) {
        float hours = 0.0f;
        for (Absence absence:absences) {
            hours = hours + absence.getHours();
            
        }
        return hours;
    }

}