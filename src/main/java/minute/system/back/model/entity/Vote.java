package minute.system.back.model.entity;

import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TBvotes")
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_minute", referencedColumnName = "id", nullable = false)
    private Minute minute;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "vote", nullable = false)    
    private Boolean vote;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
