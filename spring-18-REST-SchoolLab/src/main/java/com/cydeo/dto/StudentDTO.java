package com.cydeo.dto;

import com.cydeo.enums.Status;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    @JsonIgnore
    private Long id;
    private String firstName;
    private String phoneNumber;

    private String email;
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;//we need to se password,but we don't need to show
    private LocalDate birthday;
    private Status status;
    @JsonManagedReference(value = "student-address-reference")
    private AddressDTO address;

    private ParentDTO parent;

}
