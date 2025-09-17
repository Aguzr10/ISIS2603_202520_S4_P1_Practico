package co.edu.uniandes.dse.parcial1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class UbicacionBodegaEntity extends BaseEntity{
   
    private int numeroEstante;
    private String canastaMercancia;
    private int pesoMaximocanasta;
    
    @PodamExclude
    @OneToMany(mappedBy = "ubicacionBodega", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MercanciaEntity> mercancia = new ArrayList<>();

}
