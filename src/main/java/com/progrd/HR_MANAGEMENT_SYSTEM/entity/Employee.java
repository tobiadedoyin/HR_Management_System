package com.progrd.HR_MANAGEMENT_SYSTEM.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "date_of_birth")
    String dateOfBirth;

    String gender;

    @Column(name = "contact_number")
    String contactNumber;

    @Email(message = "please enter a unique email address")
    String email;

    String address;

    String role;

    @Column(name = "joining_date")
    String joiningDate;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    Department department;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }
    public void setDepartment(Department department){
        this.department = department;
    }

    public String toString() {
        return "Employee{\n" +
                "id= " + id +"\n"+
                "firstName= " + firstName + '\n' +
                "lastName= " + lastName + '\n' +
                "dateOfBirth= " + dateOfBirth + '\n' +
                "gender= " + gender + '\n' +
                "contactNumber= " + contactNumber + '\n' +
                "email= " + email + '\n' +
                "address= " + address + '\n' +
                "role= " + role + '\n' +
                "joiningDate= " + joiningDate + '\n' +
                "department = " + department +"\n"+
                '}';
    }
}
