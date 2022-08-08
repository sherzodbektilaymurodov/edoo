package auf.group.edu.controller;

import auf.group.edu.entity.Subject;
import auf.group.edu.payload.ApiResponse;
import auf.group.edu.payload.DtoSubject;
import auf.group.edu.repository.SubjectRepository;
import auf.group.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    SubjectRepository subjectRepository;
    @PostMapping
    @ResponseBody
    public HttpEntity<?> saveSubject(@RequestBody DtoSubject dtoSubject) {
        ApiResponse apiResponse = subjectService.saveSubject(dtoSubject) ;
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @GetMapping("/list")
    @ResponseBody
    public HttpEntity<?> getList(){
        List<Subject> all = subjectRepository.findAll();
        return ResponseEntity.ok(all);
    }


    @PutMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> editSubject(@PathVariable Integer id, @RequestBody DtoSubject dtoSubject) {
        ApiResponse apiResponse = subjectService.editSubject(id, dtoSubject);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> deleteSubject(@PathVariable Integer id){
        ApiResponse apiResponse = subjectService.deleteSubject(id);
        return ResponseEntity.ok(apiResponse);
    }
}
