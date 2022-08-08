package auf.group.edu.service;

import auf.group.edu.bot.BotSettings;
import auf.group.edu.entity.User;
import auf.group.edu.entity.enums.RoleName;
import auf.group.edu.payload.ApiResponse;
import auf.group.edu.payload.ReqRegister;
import auf.group.edu.payload.ResRegister;
import auf.group.edu.repository.AuthRepository;
import auf.group.edu.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AuthRepository authRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BotSettings botSettings;

    public ApiResponse register(ReqRegister reqRegister, User user) {
        boolean existsByPhoneNumber = authRepository.existsByPhoneNumber(reqRegister.getPhoneNumber());
        if (!existsByPhoneNumber) {
            user.setFirstName(reqRegister.getFirstName());
            user.setPhoneNumber(reqRegister.getPhoneNumber());
            user.setLastName(reqRegister.getLastName() );
            user.setBirthDate(reqRegister.getBirthDate());
            user.setEmail(reqRegister.getEmail());
            user.setIsChecked(reqRegister.getIsChecked() != null ? reqRegister.getIsChecked() : false);
            user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_USER)));
            authRepository.save(user);
            return new ApiResponse("Foydalanuvchi saqlandi", true);
        }
        return new ApiResponse("tel number bor", false);
    }

    public ApiResponse editCheck(UUID id, ReqRegister reqRegister) {
        boolean b = authRepository.existsByIdNot(id);
        if (!b) {
            User user = new User();
            user.setIsChecked(!user.getIsChecked());
            authRepository.save(user);
            return new ApiResponse("Check Edit complete", true);
        }
        return new ApiResponse("user not found", false);
    }

    public ResRegister getOneUser(User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = user.getBirthDate();
        try {


            return new ResRegister(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoneNumber(),
                    dateFormat.parse(date.getYear() + "-" + date.getMonth() + "-" + date.getDate()),
                    user.getRoles(),
                    user.getEmail(),
                    user.getIsChecked()
            );
        }catch (Exception e) {
            return null;
        }
    }

    public ApiResponse deleteUser(UUID id) {
        Optional<User> byId = authRepository.findById(id);
        if (byId.isPresent()) {
            authRepository.deleteById(id);

            return new ApiResponse("Delete complete", true);
        }
        return new ApiResponse("user not found", false);
    }

    //    public ApiResponse getUsers(Integer page, Integer size) {
//        try {
//            if (page < 0) {
//                throw new MyExeption("Page 0 dan kichik bo'lishi mumkin emas");
//            }
//            if (size < 1) {
//                throw new MyExeption("Size 1 dan kichik bo'lishi mumkin emas");
//            }
//            Pageable pageable = PageRequest.of(page, size);
//            Page<User> userPage = authRepository.findAll(pageable);
//            return new ApiResponse("Users list", true, new ResPageable(
//                    page, size, userPage.getTotalPages(), userPage.getTotalElements(), userPage.stream().map(this::getOneUser).collect(Collectors.toList())
//            ));
//        } catch (IllegalArgumentException e) {
//            return new ApiResponse(e.getMessage(), false);
//        }
//    }
    public List<ResRegister> getUserList() {
        return authRepository.findAll().stream().map(this::getOneUser).collect(Collectors.toList());
    }

    public List<ResRegister> getIsChecked(boolean isChecked) {
        List<ResRegister> resRegisterList = new ArrayList<>();
        List<ResRegister> resRegisterList1 = new ArrayList<>();
        if (isChecked) {
            for (ResRegister resRegister : getUserList()) {
                if (resRegister.getIsChecked())
                    resRegisterList.add(resRegister);
            }
        } else {
            for (ResRegister resRegister : getUserList()) {
                if (!resRegister.getIsChecked())
                    resRegisterList1.add(resRegister);
            }
        }
        return isChecked ? resRegisterList : resRegisterList1;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
