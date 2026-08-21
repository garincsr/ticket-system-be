package com.st_carollus.ticket_system.model.entity;

import com.st_carollus.ticket_system.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.UNIT)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "unit_name", nullable = false, unique = true)
    private String unitName;

    @Builder.Default
    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
