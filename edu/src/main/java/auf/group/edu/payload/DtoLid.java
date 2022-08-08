package auf.group.edu.payload;

import auf.group.edu.entity.Subject;
import auf.group.edu.entity.enums.FromWhere;
import auf.group.edu.entity.enums.LidStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DtoLid {
    private UUID id;
    private String name;
    private Integer phoneNumber;
    private LidStatus lidStatus;
    private FromWhere fromWhere;
    private Subject subject;
    private Integer subjectId;

}
