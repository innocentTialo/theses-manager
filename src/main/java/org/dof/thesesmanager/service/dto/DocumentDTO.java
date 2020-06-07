package org.dof.thesesmanager.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import org.dof.thesesmanager.domain.enumeration.Status;

/**
 * A DTO for the {@link org.dof.thesesmanager.domain.Document} entity.
 */
public class DocumentDTO implements Serializable {

    private Long id;

    private String title;

    private LocalDate defenseDate;

    private String authorName;

    private String supervisorName;

    @Lob
    private byte[] file;

    private String fileContentType;
    private Status status;


    private Long optionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDefenseDate() {
        return defenseDate;
    }

    public void setDefenseDate(LocalDate defenseDate) {
        this.defenseDate = defenseDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentDTO documentDTO = (DocumentDTO) o;
        if (documentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", defenseDate='" + getDefenseDate() + "'" +
            ", authorName='" + getAuthorName() + "'" +
            ", supervisorName='" + getSupervisorName() + "'" +
            ", file='" + getFile() + "'" +
            ", status='" + getStatus() + "'" +
            ", optionId=" + getOptionId() +
            "}";
    }
}
