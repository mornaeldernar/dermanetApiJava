package com.mornaeldernar.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="doctor")
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable =false)
    @NotBlank
    private String name;

    @Column(name="lastName", nullable = false)
    @NotBlank
    private String lastName;
    
    @Column(name="created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name="modified_at")
    @UpdateTimestamp
    private Date modifiedAt;

    @ManyToMany
    Set<Patient> patients;

    @ManyToOne
    @JoinColumn(name="speciality_id", referencedColumnName = "id", nullable = false)
    private Speciality speciality;

}
