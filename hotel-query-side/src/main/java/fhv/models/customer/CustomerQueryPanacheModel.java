package fhv.models.customer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CustomerQueryPanacheModel extends PanacheEntity {

    // Panache provides Auto-CRUD for everything
    public String userId;
    public String email;


}
