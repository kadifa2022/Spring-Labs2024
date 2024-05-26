package com.cydeo.dto;

import com.cydeo.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class CourseDTO extends BaseEntity {

    private String courseName;
    @Column(columnDefinition = "text")
    private String description;
}
