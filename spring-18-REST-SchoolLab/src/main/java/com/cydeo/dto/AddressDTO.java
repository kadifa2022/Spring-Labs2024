package com.cydeo.dto;

import com.cydeo.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.eac.Flags;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {

    @JsonIgnore
    private Long id;

    private String street;
    private String country;
    private String state;
    private String city;
    private String postalCode;

    private AddressType addressType;
    // if we have more than one back reference, Jason will be confused that's why we add
    //names structure to each object
    @JsonBackReference(value = "student-address-reference")
    private StudentDTO student;
    @JsonBackReference(value = "parent-address-reference")
    private ParentDTO parent;
    @JsonBackReference(value = "teacher-address-reference")
    private TeacherDTO teacher;

    private Integer currentTemperature;

    private String flag;
}
