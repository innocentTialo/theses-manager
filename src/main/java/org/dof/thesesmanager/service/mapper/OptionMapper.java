package org.dof.thesesmanager.service.mapper;

import org.dof.thesesmanager.domain.*;
import org.dof.thesesmanager.service.dto.OptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Option} and its DTO {@link OptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface OptionMapper extends EntityMapper<OptionDTO, Option> {

    @Mapping(source = "department.id", target = "departmentId")
    OptionDTO toDto(Option option);

    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "removeDocument", ignore = true)
    @Mapping(source = "departmentId", target = "department")
    Option toEntity(OptionDTO optionDTO);

    default Option fromId(Long id) {
        if (id == null) {
            return null;
        }
        Option option = new Option();
        option.setId(id);
        return option;
    }
}
