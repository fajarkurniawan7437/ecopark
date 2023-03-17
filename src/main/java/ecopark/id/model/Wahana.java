package ecopark.id.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wahana")
public class Wahana extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "wahanaSequence", sequenceName = "wahana_sequence", allocationSize = 1, initialValue = 1)// untuk otomatis id
    @GeneratedValue(generator = "wahanaSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_wahana", nullable = false)
    public Long id;
    @OneToOne()
    @JoinColumn(name = "id_operasional")
    public Operasional operasional;
    @ManyToOne
    @JoinColumn(name = "id_costumer")
    public Costumer costumer;
    @ManyToOne
    @JoinColumn(name = "id_cleaning")
    public Cleaning cleaning;
    @OneToOne()
    @JoinColumn(name = "id_cashier")
    public Cashier cashier;

    @Column(name = "name")
    public String name;
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

