package auf.group.edu.entity;

import auf.group.edu.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
public class Group extends AbsNameEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany
    private List<User> users;
    @OneToMany
    private List<Subject> subjects;
}
