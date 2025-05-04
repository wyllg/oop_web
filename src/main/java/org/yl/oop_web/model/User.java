// Java class for the user database

package org.yl.oop_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;


@Setter
@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @Column(length = 255)
    private String bio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(length = 255)
    private String address;

    private String contactNumber;

    @Column(length = 600)
    private String profilePicUrl;

    private String school;

    private String degree;

    private String fieldOfStudy;

    private Month startMonth;

    private Year startYear;

    private Month endMonth;

    private Year endYear;

    private String role;

}
