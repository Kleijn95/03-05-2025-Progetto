package it.epicode.__05_2025_Progetto.eventi;

import it.epicode.__05_2025_Progetto.utenti.Utente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventi")

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    @Column(nullable = false)
    private String titolo;
    @Column(nullable = false)
    private String descrizione;
    @Column(nullable = false)
    private String luogo;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private int numeroPostiDisponibili;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente organizzatore;



}