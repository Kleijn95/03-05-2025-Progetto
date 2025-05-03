package it.epicode.__05_2025_Progetto.eventi;

import it.epicode.__05_2025_Progetto.common.CommonResponse;
import it.epicode.__05_2025_Progetto.utenti.Utente;
import it.epicode.__05_2025_Progetto.utenti.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    public CommonResponse save(EventoRequest request, Long idUtente) {
        Utente utente = utenteRepository.findById(idUtente)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        // Controllo sul ruolo
        if (!utente.getRuolo().name().equalsIgnoreCase("ORGANIZZATORE")) {
            throw new IllegalStateException("Solo un organizzatore può creare eventi");
        }

        Evento evento = new Evento();
        BeanUtils.copyProperties(request, evento);
        evento.setOrganizzatore(utente); 
        eventoRepository.save(evento);
        return new CommonResponse(evento.getId());
    }




}
