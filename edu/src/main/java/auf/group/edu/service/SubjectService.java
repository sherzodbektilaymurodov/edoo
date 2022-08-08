package auf.group.edu.service;

import auf.group.edu.entity.Subject;
import auf.group.edu.payload.ApiResponse;
import auf.group.edu.payload.DtoSubject;
import auf.group.edu.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public void shortCode(DtoSubject dtoSubject, Subject subject) {
        subject.setName(dtoSubject.getName());
        subject.setDescription(dtoSubject.getDescription());
        subjectRepository.save(subject);
    }

    public ApiResponse saveSubject(DtoSubject dtoSubject) {
        Subject subject = new Subject();
        shortCode(dtoSubject, subject);
        return new ApiResponse("Successfully saved Subject", true);
    }

    public ApiResponse editSubject(Integer id, DtoSubject dtoSubject) {
        Optional<Subject> byId = subjectRepository.findById(id);
        Subject subject = byId.get();
        shortCode(dtoSubject, subject);
        return new ApiResponse("Successfully edited Subject", true);
    }
    public ApiResponse deleteSubject(Integer id){
        subjectRepository.deleteById(id);
        return new ApiResponse("Successfully deleted Subject",true);
    }
}
