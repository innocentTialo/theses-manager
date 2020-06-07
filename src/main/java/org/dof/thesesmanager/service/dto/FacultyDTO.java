package org.dof.thesesmanager.service.dto;
import java.io.Serializable;
import java.util.Objects;
import org.dof.thesesmanager.domain.enumeration.Status;

/**
 * A DTO for the {@link org.dof.thesesmanager.domain.Faculty} entity.
 */
public class FacultyDTO implements Serializable {

    private Long id;

    private String facultyName;

    private String directorName;

    private Status status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacultyDTO facultyDTO = (FacultyDTO) o;
        if (facultyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facultyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FacultyDTO{" +
            "id=" + getId() +
            ", facultyName='" + getFacultyName() + "'" +
            ", directorName='" + getDirectorName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
