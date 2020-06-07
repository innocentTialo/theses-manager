package org.dof.thesesmanager.service.mapper;

import org.dof.thesesmanager.domain.*;
import org.dof.thesesmanager.service.dto.FacultyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Faculty} and its DTO {@link FacultyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FacultyMapper extends EntityMapper<FacultyDTO, Faculty> {


    @Mapping(target = "departments", ignore = true)
    @Mapping(target = "removeDepartment", ignore = true)
    Faculty toEntity(FacultyDTO facultyDTO);

    default Faculty fromId(Long id) {
        if (id == null) {
            return null;
        }
        Faculty faculty = new Faculty();
        faculty.setId(id);
        return faculty;
    }
}
