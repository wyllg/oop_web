// Java class for the user database

package org.yl.oop_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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

    @Past(message = "Date of birth must be in the past")
    private LocalDate birthday;

    @Column(length = 255)
    private String address;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String contactNumber;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @NotBlank(message = "School is required")
    private String school;

    private String degree;

    private String fieldOfStudy;

    private Month startMonth;

    private Year startYear;

    private Month endMonth;

    private Year endYear;

}
