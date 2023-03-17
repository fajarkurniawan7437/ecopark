package ecopark.id.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "cashier")
public class Cashier extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "cashierSequence", sequenceName = "cashier_sequence", allocationSize = 1, initialValue = 1)// untuk otomatis id
    @GeneratedValue(generator = "cashierSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cashier", nullable = false)
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "gender")
    public String gender;
}

