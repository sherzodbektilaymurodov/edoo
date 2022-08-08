package auf.group.edu.service;

import auf.group.edu.entity.Lid;
import auf.group.edu.payload.ApiResponse;
import auf.group.edu.payload.DtoLid;
import auf.group.edu.repository.LidRepository;
import auf.group.edu.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;
import java.util.UUID;

@Service
public class LidService {
    @Autowired
    LidRepository lidRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public void shortCode(DtoLid dtoLid, Lid lid) {
        lid.setName(dtoLid.getName());
        lid.setPhoneNumber(dtoLid.getPhoneNumber());
        lid.setSubject(subjectRepository.findById(dtoLid.getSubjectId()).orElseThrow(() -> new ResourceNotFoundException("getSubject")));
        lid.setLidStatus(dtoLid.getLidStatus());
        lid.setFromWhere(dtoLid.getFromWhere());
        lidRepository.save(lid);
    }

    public ApiResponse saveLid(DtoLid dtoLid) {
        Lid lid = new Lid();
        shortCode(dtoLid, lid);
        return new ApiResponse("Successfully saved Lid", true);
    }

    public ApiResponse editLid(UUID id, DtoLid dtoLid) {
        Optional<Lid> byId = lidRepository.findById(id);
        Lid lid = byId.get();
        shortCode(dtoLid, lid);
        return new ApiResponse("Successfully saved Lid", true);
    }

    public ApiResponse deleteLid(UUID id) {
        lidRepository.deleteById(id);
        return new ApiResponse("Successfully deleted Lid", true);
    }
}
