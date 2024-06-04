package com.cydeo.controller;

import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TeacherDTO;
import com.cydeo.service.AddressService;
import com.cydeo.service.ParentService;
import com.cydeo.service.StudentService;
import com.cydeo.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {

    private final TeacherService teacherService;
    private final ParentService parentService;
    private final StudentService studentService;
    private final AddressService addressService;

    public SchoolController(TeacherService teacherService, ParentService parentService, StudentService studentService, AddressService addressService) {
        this.teacherService = teacherService;
        this.parentService = parentService;
        this.studentService = studentService;
        this.addressService = addressService;
    }


    @GetMapping("/teachers")
    public List<TeacherDTO> readAllTeacher(){
    List<TeacherDTO> teacherDTOS = teacherService.findAll();
    return teacherDTOS;

    }
    /*create an endpoint for parents, where json includes
    "Students are successfully retrieved." message
    code:202 Accepted
    success:true
    and student data
   */
    @GetMapping("/parents")
    public ResponseEntity<ResponseWrapper> readAllParents(){
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Parents are successfully retrieved"
                , HttpStatus.ACCEPTED.value(), parentService.findAll());
        return  ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Parent","Returned")
                .body(responseWrapper);

    }

    @GetMapping("/students")
    public ResponseEntity<ResponseWrapper> readAllStudents(){
        return ResponseEntity.ok(new ResponseWrapper("students are successfully retrieved",
                studentService.findAll()));
    }
    /*
    create endpoint for individual address information
    "/address/1"
    return status code 200
    "address is successfully retrieved
    and address information
     */
    @GetMapping("/address/{id}")
    public ResponseEntity<ResponseWrapper> findById(@PathVariable("id") Long id) throws Exception {
        //Find the address to return
        AddressDTO addressToReturn = addressService.findById(id);
        return ResponseEntity.ok( new ResponseWrapper("Address " + id + " is successfully retrieve ", addressToReturn));
    }
    @PutMapping("/address/{id}")// I'm accepting my @PathVariable as (id) long id,and I will accept JSON RequestBody as AddressDTO
    public AddressDTO updateAddress(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) throws Exception{
        // the id that i'm capturing from url i need to set to my dto (which address we are going to update)
        addressDTO.setId(id);
        // and then call address service.update(what we are going to update /addressDTO)
        // store local variable
        AddressDTO updateAddress = addressService.update(addressDTO);
        // then return
        return updateAddress;


    }




}
