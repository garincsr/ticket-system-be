package com.st_carollus.ticket_system.model.entity;

import com.st_carollus.ticket_system.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.ROLE_MENU_ACCESS)
public class RoleMenuAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "can_view", nullable = false)
    private Boolean canView;

    @Column(name = "can_create", nullable = false)
    private Boolean canCreate;

    @Column(name = "can_edit", nullable = false)
    private Boolean canEdit;

    @Column(name = "can_delete", nullable = false)
    private Boolean canDelete;
}
