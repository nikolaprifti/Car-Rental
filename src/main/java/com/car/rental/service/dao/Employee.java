package com.car.rental.service.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(unique = true)
    private String username;
    private String name;
    private String email;
    private String password;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "branch")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

}