package school.sptech.EncantoPersonalizados.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class StatusPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    @ManyToOne
    private Pedido pedido;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
