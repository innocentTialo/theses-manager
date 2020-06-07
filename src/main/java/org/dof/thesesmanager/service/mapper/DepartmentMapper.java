package org.dof.thesesmanager.service.mapper;

import org.dof.thesesmanager.domain.*;
import org.dof.thesesmanager.service.dto.DepartmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {FacultyMapper.class})
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {

    @Mapping(source = "faculty.id", target = "facultyId")
    DepartmentDTO toDto(Department department);

    @Mapping(target = "options", ignore = true)
    @Mapping(target = "removeOption", ignore = true)
    @Mapping(source = "facultyId", target = "faculty")
    Department toEntity(DepartmentDTO departmentDTO);

    default Department fromId(Long id) {
        if (id == null) {
            return null;
        }
        Department department = new Department();
        department.setId(id);
        return department;
    }
}
