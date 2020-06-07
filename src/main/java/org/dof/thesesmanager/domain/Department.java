package org.dof.thesesmanager.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.dof.thesesmanager.domain.enumeration.Status;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "hod_name")
    private String hodName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "department")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Option> options = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("departments")
    private Faculty faculty;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Department departmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHodName() {
        return hodName;
    }

    public Department hodName(String hodName) {
        this.hodName = hodName;
        return this;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public Status getStatus() {
        return status;
    }

    public Department status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public Department options(Set<Option> options) {
        this.options = options;
        return this;
    }

    public Department addOption(Option option) {
        this.options.add(option);
        option.setDepartment(this);
        return this;
    }

    public Department removeOption(Option option) {
        this.options.remove(option);
        option.setDepartment(null);
        return this;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Department faculty(Faculty faculty) {
        this.faculty = faculty;
        return this;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return id != null && id.equals(((Department) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            ", hodName='" + getHodName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
