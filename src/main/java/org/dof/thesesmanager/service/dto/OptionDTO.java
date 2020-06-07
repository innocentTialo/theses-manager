package org.dof.thesesmanager.service.dto;
import java.io.Serializable;
import java.util.Objects;
import org.dof.thesesmanager.domain.enumeration.Status;

/**
 * A DTO for the {@link org.dof.thesesmanager.domain.Option} entity.
 */
public class OptionDTO implements Serializable {

    private Long id;

    private String optionName;

    private Status status;


    private Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OptionDTO optionDTO = (OptionDTO) o;
        if (optionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), optionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OptionDTO{" +
            "id=" + getId() +
            ", optionName='" + getOptionName() + "'" +
            ", status='" + getStatus() + "'" +
            ", departmentId=" + getDepartmentId() +
            "}";
    }
}
