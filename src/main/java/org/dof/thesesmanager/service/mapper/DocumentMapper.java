package org.dof.thesesmanager.service.mapper;

import org.dof.thesesmanager.domain.*;
import org.dof.thesesmanager.service.dto.DocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Document} and its DTO {@link DocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {OptionMapper.class})
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {

    @Mapping(source = "option.id", target = "optionId")
    DocumentDTO toDto(Document document);

    @Mapping(source = "optionId", target = "option")
    Document toEntity(DocumentDTO documentDTO);

    default Document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Document document = new Document();
        document.setId(id);
        return document;
    }
}
