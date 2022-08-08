package auf.group.edu.controller;

import auf.group.edu.entity.Subject;
import auf.group.edu.entity.User;
import auf.group.edu.payload.ApiResponse;
import auf.group.edu.payload.ReqRegister;
import auf.group.edu.payload.ResRegister;
import auf.group.edu.repository.AuthRepository;
import auf.group.edu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AuthRepository authRepository;
    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody ReqRegister reqRegister){
        ApiResponse register = authService.register(reqRegister);
        return ResponseEntity.status(register.isSuccess()?200:409).body(register);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable UUID id){
        ApiResponse apiResponce = authService.deleteUser(id);
        return ResponseEntity.ok(apiResponce);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable UUID id, @RequestBody ReqRegister reqRegister){
        ApiResponse apiResponce = authService.editUser(id, reqRegister);
        return ResponseEntity.status(apiResponce.isSuccess()?200:409).body(apiResponce);
    }

//    @GetMapping("/getPage")
//    public HttpEntity<?> getGroup(@RequestParam (value = "page", defaultValue = AppConstant.APP_DEFAULT_PAGE) int page,
//                                  @RequestParam (value = "size", defaultValue = AppConstant.APP_DEFAULT_SIZE) int size){
//        return ResponseEntity.ok(authService.getUsers(page, size));
//    }
    @GetMapping("/{id}")
    public ResRegister getOneUser(User user) throws ParseException {
        return  authService.getOneUser(user);
    }


//    @GetMapping
//    public HttpEntity<?> getUsers(){
//        return (HttpEntity<?>) authService.getUserList();
//    }
@GetMapping("/register/list")
@ResponseBody
public HttpEntity<?> getList(){
    List<User> all = authRepository.findAll();
    return ResponseEntity.ok(all);
}


    @GetMapping("/isChecked/{isTrue}")
    public List<ResRegister> getCheckedTrue(@PathVariable boolean isTrue){
        return  authService.getIsChecked(isTrue);
    }
}
