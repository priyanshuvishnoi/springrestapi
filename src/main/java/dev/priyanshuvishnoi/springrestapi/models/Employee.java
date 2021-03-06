package dev.priyanshuvishnoi.springrestapi.models;

import dev.priyanshuvishnoi.springrestapi.requests.EmployeeRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name should not be null!")
    private String name;

    private Long age = 0L;

    private String location;

    @Email
    private String email;

    @JoinColumn(name = "id")
    @OneToOne
    private Department department;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;

    public Employee(EmployeeRequest request) {
        this.name = request.getName();
        this.age = request.getAge();
        this.email = request.getEmail();
        this.location = request.getLocation();
    }
}
