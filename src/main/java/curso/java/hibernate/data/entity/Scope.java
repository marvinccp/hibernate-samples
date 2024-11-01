package curso.java.hibernate.data.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TBL_SCOPE")
public class Scope implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String scopeName;

    @Column
    private String scopeDescription;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getScopeDescription() {
        return scopeDescription;
    }

    public void setScopeDescription(String scopeDescription) {
        this.scopeDescription = scopeDescription;
    }

    @Override
    public String toString() {
        return "Scope{" +
                "id=" + id +
                ", scopeName='" + scopeName + '\'' +
                ", scopeDescription='" + scopeDescription + '\'' +
                '}';
    }
}
