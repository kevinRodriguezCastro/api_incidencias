package api_incidencias.api_incidencias.Entidades;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "Comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comentario")
    private Long ID_Comentario;

    @ManyToOne
    @JoinColumn(name = "ID_Incidencia")
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;
    @Column(name = "Contenido")
    private String Contenido;
    @Column(name = "Fecha_Publicacion")
    private LocalDateTime FechaPublicacion;
}
