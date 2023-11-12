package org.madmotor.apimadmotor.piezas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "PIEZAS")
@EntityListeners(AuditingEntityListener.class)
public class Pieza {
    private static final String IMAGE_DEFAULT = "https://via.placeholder.com/150";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "nombre", nullable = false, length = 50)
    @NotBlank
    private String name;
    @Column(name = "descripcion", nullable = false)
    private String description;
    @Column(name = "precio", nullable = false )
    @Positive
    private Double price;
    private String image=IMAGE_DEFAULT;
}
