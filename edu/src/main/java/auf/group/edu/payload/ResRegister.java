package auf.group.edu.payload;


import auf.group.edu.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResRegister {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private Set<Role> role;
    private String email;
    private Boolean isChecked;
}
