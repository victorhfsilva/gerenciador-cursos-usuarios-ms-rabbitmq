package com.example.usuariosms.model;

import com.example.usuariosms.model.enums.Estado;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "usuario")
@ToString(exclude = "usuario")
@Table(name = "enderecos")
public class Endereco {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 8)
        private String cep;

        @Column(nullable = false, length = 10)
        private String numero;

        @Column(length = 100)
        private String complemento;

        @Column(nullable = false, length = 100)
        private String logradouro;

        @Column(nullable = false, length = 100)
        private String bairro;

        @Column(nullable = false, length = 100)
        private String cidade;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private Estado estado;

        @OneToOne(mappedBy = "endereco")
        private Usuario usuario;
}
