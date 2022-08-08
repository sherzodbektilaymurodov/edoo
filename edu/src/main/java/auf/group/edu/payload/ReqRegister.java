package auf.group.edu.payload;


import lombok.Data;

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
