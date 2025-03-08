package org.mertguler.cinemium.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mertguler.cinemium.util.validator.EnumValidator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @EnumValidator(enumClass = UserRole.class)
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
