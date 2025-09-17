package co.edu.uniandes.dse.parcial1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.MercanciaRepository;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MercanciaUbicacionBodegaService {

    // TODO: Cree la lógica de asociación entre mercancía y ubicación de bodega

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Autowired
    private UbicacionBodegaRepository ubicacionBodegaRepository;

    @Transactional
    public void asociarMercanciaConUbicacion(Long mercanciaId, Long ubicacionBodegaId) throws EntityNotFoundException, IllegalOperationException {
        
        Optional<MercanciaEntity> mercanciaEntity = mercanciaRepository.findById(mercanciaId);
        if (mercanciaEntity.isEmpty())
            throw new EntityNotFoundException("Mercancia no encontrada");

        Optional<UbicacionBodegaEntity> ubicacionEntity = ubicacionBodegaRepository.findById(ubicacionBodegaId);
        if (ubicacionEntity.isEmpty())
            throw new EntityNotFoundException("Ubicación de bodega no encontrada");

        MercanciaEntity mercancia = mercanciaEntity.get();
        UbicacionBodegaEntity ubicacion = ubicacionEntity.get();

        mercancia.setBodega(ubicacion);
        mercanciaRepository.save(mercancia);

    }
    
}
