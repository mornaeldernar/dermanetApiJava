package com.mornaeldernar.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name="role")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="role", nullable = false)
    private String role;
    @Column(name="enabled", nullable = false)
    private boolean enabled;
    @Column(name="created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name="modified_at")
    @UpdateTimestamp
    private Date modifiedAt;

}
