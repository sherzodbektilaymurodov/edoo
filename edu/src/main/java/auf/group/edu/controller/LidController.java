package auf.group.edu.controller;

import auf.group.edu.entity.Lid;
import auf.group.edu.payload.ApiResponse;
import auf.group.edu.payload.DtoLid;
import auf.group.edu.repository.LidRepository;
import auf.group.edu.service.LidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lid")
public class LidController {
    @Autowired
    LidService lidService;
    @Autowired
    LidRepository lidRepository;
    @PostMapping
    @ResponseBody
    public HttpEntity<?> saveLid(@RequestBody DtoLid dtoLid) {
        ApiResponse apiResponse = lidService.saveLid(dtoLid) ;
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @GetMapping("/list")
    @ResponseBody
    public HttpEntity<?> getList(){
        List<Lid> all = lidRepository.findAll();
        return ResponseEntity.ok(all);
    }


    @PutMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> editLid(@PathVariable UUID id, @RequestBody DtoLid dtoLid) {
        ApiResponse apiResponse = lidService.editLid(id, dtoLid);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> deleteLid(@PathVariable UUID id){
        ApiResponse apiResponse = lidService.deleteLid(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
