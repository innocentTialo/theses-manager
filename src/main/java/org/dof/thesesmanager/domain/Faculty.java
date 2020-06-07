package org.dof.thesesmanager.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.dof.thesesmanager.domain.enumeration.Status;

/**
 * A Faculty.
 */
@Entity
@Table(name = "faculty")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Faculty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "director_name")
    private String directorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "faculty")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Department> departments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public Faculty facultyName(String facultyName) {
        this.facultyName = facultyName;
        return this;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public Faculty directorName(String directorName) {
        this.directorName = directorName;
        return this;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Status getStatus() {
        return status;
    }

    public Faculty status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public Faculty departments(Set<Department> departments) {
        this.departments = departments;
        return this;
    }

    public Faculty addDepartment(Department department) {
        this.departments.add(department);
        department.setFaculty(this);
        return this;
    }

    public Faculty removeDepartment(Department department) {
        this.departments.remove(department);
        department.setFaculty(null);
        return this;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Faculty)) {
            return false;
        }
        return id != null && id.equals(((Faculty) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Faculty{" +
            "id=" + getId() +
            ", facultyName='" + getFacultyName() + "'" +
            ", directorName='" + getDirectorName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
