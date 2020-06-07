package org.dof.thesesmanager.repository;

import org.dof.thesesmanager.domain.Document;
import org.dof.thesesmanager.domain.enumeration.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Document entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Page<Document> findByStatus(Pageable pageable, Status enabled);
}
