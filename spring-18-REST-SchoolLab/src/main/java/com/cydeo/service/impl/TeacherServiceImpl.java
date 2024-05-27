package com.cydeo.service.impl;

import com.cydeo.dto.TeacherDTO;
import com.cydeo.repository.TeacherRepository;
import com.cydeo.service.TeacherService;
import com.cydeo.utill.MapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final MapperUtil mapperUtil;
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl( MapperUtil mapperUtil, TeacherRepository teacherRepository) {
        this.mapperUtil = mapperUtil;

        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> mapperUtil.convert(teacher, new TeacherDTO()))
                .collect(Collectors.toList());
    }
}
