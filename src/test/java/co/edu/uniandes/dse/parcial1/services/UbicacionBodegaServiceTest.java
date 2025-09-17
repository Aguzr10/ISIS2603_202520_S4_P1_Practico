package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.UbicacionBodegaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(UbicacionBodegaService.class)
class UbicacionBodegaServiceTest {

    @Autowired
    private UbicacionBodegaService ubicacionBodegaService;

    @Autowired
    private EntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<UbicacionBodegaEntity> ubicacionBodegaList = new ArrayList<>();

    

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.createQuery("delete from UbicacionBodegaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UbicacionBodegaEntity bodega = factory.manufacturePojo(UbicacionBodegaEntity.class);
            bodega.setMercancia(null);
            entityManager.persist(bodega);
        }
    }

    @Test
    void testCreateUbicacionBodega() throws IllegalOperationException {
        UbicacionBodegaEntity nuevaUbicacion = new UbicacionBodegaEntity();

        nuevaUbicacion.setNumeroEstante(32);

        UbicacionBodegaEntity resultado = ubicacionBodegaService.createUbicacionBodega(nuevaUbicacion);
        assertNotNull(resultado);

        assertEquals(resultado, nuevaUbicacion);
           
}
    @Test
    void testCreateUbicacionBodegaConEstanteInvalido()  {
        assertThrows(IllegalOperationException.class, () ->{ 

        UbicacionBodegaEntity nuevaUbicacion = new UbicacionBodegaEntity();

        nuevaUbicacion.setNumeroEstante(-3);

        ubicacionBodegaService.createUbicacionBodega(nuevaUbicacion);
        });

    }
}


    
