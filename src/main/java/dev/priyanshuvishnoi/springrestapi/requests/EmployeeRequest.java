package dev.priyanshuvishnoi.springrestapi.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private String name;

    private String location;

    private String email;

    private String department;

    private Long age;
}
