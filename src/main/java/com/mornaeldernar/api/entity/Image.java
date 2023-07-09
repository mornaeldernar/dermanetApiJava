package com.mornaeldernar.api.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name="doctor")
@RestResource(rel="images",path="image")
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="location")
    private String location;
    
    @Column(name="created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name="modified_at")
    @UpdateTimestamp
    private Date modifiedAt;

    @ManyToOne
    @JoinColumn(name="patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;


}
