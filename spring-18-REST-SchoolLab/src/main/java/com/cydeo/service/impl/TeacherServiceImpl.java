package com.cydeo.service.impl;

import com.cydeo.dto.TeacherDTO;
import com.cydeo.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final ModelMapper modelMapper;

    public TeacherServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TeacherDTO> findAll() {
        return null;
    }
}
