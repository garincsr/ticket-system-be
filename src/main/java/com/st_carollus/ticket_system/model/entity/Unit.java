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
@Table(name = ConstantTable.UNIT)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "unit_name", nullable = false, unique = true)
    private String unitName;
}
