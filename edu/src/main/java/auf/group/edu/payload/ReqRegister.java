package auf.group.edu.payload;


import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class ReqRegister {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private Set<Integer> roles;
    private String email;
    private Boolean isChecked;
}
