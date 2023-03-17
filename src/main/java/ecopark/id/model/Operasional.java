package ecopark.id.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "operasional")
public class Operasional extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "operasionalSequence", sequenceName = "operasional_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "operasionalSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_operasional", nullable = false)
    public Long id;
    @ManyToOne()
    @JoinColumn(name = "id_cashier")
    public Cashier cashier;
    @Column(name = "name")
    public String name;
    @Column(name = "gender")
    public String gender;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

}
