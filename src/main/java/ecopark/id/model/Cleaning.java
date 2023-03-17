package ecopark.id.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.inject.Inject;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cleaning")
public class Cleaning extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "cleaningSequence", sequenceName = "cleaning_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "cleaningSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cleaning", nullable = false)
    public Long id;
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
