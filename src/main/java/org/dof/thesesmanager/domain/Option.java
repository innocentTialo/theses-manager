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
 * A Option.
 */
@Entity
@Table(name = "option")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Option implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "option_name")
    private String optionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "option")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("options")
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public Option optionName(String optionName) {
        this.optionName = optionName;
        return this;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Status getStatus() {
        return status;
    }

    public Option status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Option documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Option addDocument(Document document) {
        this.documents.add(document);
        document.setOption(this);
        return this;
    }

    public Option removeDocument(Document document) {
        this.documents.remove(document);
        document.setOption(null);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Department getDepartment() {
        return department;
    }

    public Option department(Department department) {
        this.department = department;
        return this;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Option)) {
            return false;
        }
        return id != null && id.equals(((Option) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Option{" +
            "id=" + getId() +
            ", optionName='" + getOptionName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
