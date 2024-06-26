package br.com.tech.challenge.domain.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Table
@Generated
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_sequence")
    @SequenceGenerator(name = "categoria_sequence", sequenceName = "categoria_seq", allocationSize = 1)
    private Long id;

    @Column
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="categoria")
    @Transient
    @JsonIgnore
    private Set<Produto> produtos;

}
