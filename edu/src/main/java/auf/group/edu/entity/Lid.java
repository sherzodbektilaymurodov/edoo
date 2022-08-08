package auf.group.edu.entity;

import auf.group.edu.entity.enums.FromWhere;
import auf.group.edu.entity.enums.LidStatus;
import auf.group.edu.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lid extends AbsEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer phoneNumber;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FromWhere fromWhere;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LidStatus lidStatus;
    @OneToOne
    private Subject subject;
}
