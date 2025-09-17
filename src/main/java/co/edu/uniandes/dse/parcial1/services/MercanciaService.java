package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaService {
    
    // TODO: Cree la lógica de creación de una mercancía

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Transactional
    public MercanciaEntity createMercancia(MercanciaEntity mercancia) throws IllegalOperationException{

        
        if (mercanciaRepository.findByCodigoBarras(mercancia.getCodigoBarras()) != null) {
            throw new IllegalOperationException("Ya existe una mercancía con ese código de barras.");
        }

        if (mercancia.getNombre() == null || mercancia.getNombre().isEmpty()) {
            throw new IllegalOperationException("El nombre de la mercancía es obligatorio y no puede estar vacío.");
        }

        if (mercancia.getFechaRecepcion() != null && mercancia.getFechaRecepcion().isAfter(LocalDateTime.now())) {
            throw new IllegalOperationException("La fecha de recepción no puede ser posterior a la fecha actual.");
        }

        
        return mercanciaRepository.save(mercancia);
    }
}
