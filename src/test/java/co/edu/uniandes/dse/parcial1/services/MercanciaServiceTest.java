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

import co.edu.uniandes.dse.parcial1.entities.MercanciaEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(MercanciaService.class)
class MercanciaServiceTest {
    
    // TODO: Escriba las pruebas para la clase MercanciaService

    @Autowired
    private MercanciaService mercanciaService;

    @Autowired
    private EntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<MercanciaEntity> mercanciaList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.createQuery("delete from MercanciaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MercanciaEntity mercancia = factory.manufacturePojo(MercanciaEntity.class);
            mercancia.setUbicacionBodega(null);
            entityManager.persist(mercancia);

        }
    }

    @Test
    void testCreateMercancia() throws IllegalOperationException{
        MercanciaEntity nuevaMercancia = factory.manufacturePojo(MercanciaEntity.class);

        nuevaMercancia.setCodigoBarras(123456789L);
        nuevaMercancia.setNombre("Producto de prueba");
        nuevaMercancia.setFechaRecepcion(nuevaMercancia.getFechaRecepcion().minusDays(1));

        MercanciaEntity resultado = mercanciaService.createMercancia(nuevaMercancia);
        assertNotNull(resultado);

        assertEquals(resultado, nuevaMercancia);     
}

    @Test
    void testIncorrecto() throws IllegalOperationException{
        assertThrows(IllegalOperationException.class, () -> {

        MercanciaEntity nuevaMercancia = factory.manufacturePojo(MercanciaEntity.class);

        nuevaMercancia.setCodigoBarras(123456789L);
        nuevaMercancia.setNombre("");
        nuevaMercancia.setFechaRecepcion(nuevaMercancia.getFechaRecepcion().minusDays(1));

        mercanciaService.createMercancia(nuevaMercancia);

        });
    }
}

        