package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.UbicacionBodegaRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UbicacionBodegaService {

    // TODO: Cree la lógica de creación de una ubicación de bodega

    @Autowired
    private UbicacionBodegaRepository ubicacionBodegaRepository;

    @Transactional
    public UbicacionBodegaEntity createUbicacionBodega(UbicacionBodegaEntity ubicacionBodega) throws IllegalOperationException {

        if (ubicacionBodega.getNumeroEstante() <= 0) {
            throw new IllegalOperationException("El número de estante debe ser un valor positivo.");
        }

        return ubicacionBodegaRepository.save(ubicacionBodega);
    }

}
