package com.cydeo.service;

import com.cydeo.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    List<TeacherDTO> findAll();
}
