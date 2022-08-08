package auf.group.edu.payload;


import auf.group.edu.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class ReqRegister {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private Integer roles;
    private String email;
    private Boolean isChecked;
}
