package ecopark.id.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "costumer")
public class Costumer extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "costumerSequence", sequenceName = "costumer_sequence", allocationSize = 1, initialValue = 1)// untuk otomatis id
    @GeneratedValue(generator = "costumerSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_costumer", nullable = false)
    public Long id;
    @ManyToOne()
    @JoinColumn(name = "id_cashier")
    public Cashier cashier;
    @ManyToOne()
    @JoinColumn(name = "id_operasional")
    public Operasional operasional;

    @Column(name = "name")
    public String name;
    @Column(name = "gender")
    public String gender;
    @Column(name = "height")
    public String height;
    @Column(name = "age")
    public String age;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;
}

