package com.cydeo.unit_test_review;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.KeycloakService;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.util.Assert;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
/*
 @ExtendWith  is library from Junit5 and Mockito extension is to enable Mockito (fake Objects)
 Why we need fake object? Because of cost, assimilate
 What is JUnit 5? Is very popular and advanced testing Framework. We will  organize our tests,
 When we are doing unit testing we are just checking that functionality.(one function/unit)
 We assimilate behavior of the dependencies in our test.
 we don't need to mock everything, For example UserMapper instead of mocking we can use it for real
 we will create own userMapper object in impl. class private UserMapper = new UserMapper(new ModelMapper());
 and use @Spy annotation because spring is not creating beans from test
 */

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest1 {

    @Mock
    private UserRepository userRepository;// when we call any method then return this

    @Mock
    private ProjectService projectService;

    @Mock
    private TaskService taskService;

    @Mock
    private KeycloakService keycloakService;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy // I created this object by my self that's why i use @Spy annotation to assimilate modelMapper
    private UserMapper userMapper = new UserMapper(new ModelMapper()); // this is the real object that we are going to use for test

// create 2 users one for entity and one for DTO (identically)
   User user;
   UserDTO userDTO;
    // this is for individual user and userDTO

    @BeforeEach // will run for each method
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("user");
        user.setPassWord("Abc1");
        user.setEnabled(true);
        user.setRole(new Role("Manager"));

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setUserName("user");
        userDTO.setPassWord("Abc1");
        userDTO.setEnabled(true);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setDescription("Manager");
        userDTO.setRole(roleDTO); // giving my roleDTO OBJECT
    }
    // creating methods for list<User> and list <UserDTO>
    private List<User> getUsers(){
        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Emily");
        return List.of(user, user2);
    }
    private List<UserDTO> getUsersDTOs(){
        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(2L);
        userDTO2.setFirstName("Emily");
        return  List.of(userDTO, userDTO2);
    }

    //stub - we  stub methods that return something and changing the flow
    // verify- if some methods doesn't return anything/or doesn't change flow in methods(like keycloak) we don't need to stub
    // - we can use for something that is not changing the flow and call arLeastOnes()
    @Test  //calling listAllUsers()
    public void should_list_all_users(){
        //stub
        when(userRepository.findAllByIsDeletedOrderByFirstNameDesc(false)).thenReturn(getUsers());
        List<UserDTO> expectedList = getUsersDTOs();

        List<UserDTO> actualList = userService.listAllUsers();

        //assertEqual() is comparing if is the same object in memory  that why our test fails
        // with JUnit assertion we can use assertEqual() for the field to compare
        //We have another library assertJ if you want to check the values not the objects
  //     Assertions.assertEquals(expectedList, actualList);
        //AssertJ is coming with spring-boot -starter- test library
        //usingRecursiveComparison() is comparing value of each field objects
        assertThat(actualList).usingRecursiveComparison().isEqualTo(expectedList);
    }
    // 'lenient' error - means we create stub we don't use
    @Test
    void should_find_user_by_username() {//row meaning we are providing value by our self
        //stub the repo // if we are using anyString(), we have to use anyBoolean() together or will be error // (row matches)
        when(userRepository.findByUserNameAndIsDeleted(anyString(), anyBoolean())).thenReturn(user);
        UserDTO expectedUser = userService.findByUserName("user");
        assertThat(expectedUser).usingRecursiveComparison().isEqualTo(userDTO);
    }
    @Test
    void should_throw_exception_when_user_not_found(){// this is template for verifying any exceptions
        // no stub needed, first line will be null and will be assigned to the user field
        Throwable throwable = catchThrowable(()->userService.findByUserName("someUsername"));
        assertInstanceOf(NoSuchElementException.class, throwable);
        assertEquals("User not found", throwable.getMessage());

    }
    @Test
    void should_save_user(){
        when(userRepository.save(any())).thenReturn(user);
        UserDTO actualDTO =  userService.save(userDTO);
        assertThat(actualDTO).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(userDTO);
        verify(keycloakService, atLeastOnce()).userCreate(any());
    }








}
