package mx.uv.t4is.Prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.prestamo.GuardarPrestamoRequest;
import https.t4is_uv_mx.prestamo.GuardarPrestamoResponse;
import https.t4is_uv_mx.prestamo.VerStatusRequest;
import https.t4is_uv_mx.prestamo.VerStatusResponse;


@Endpoint
public class PrestamoEndPoint {
    
    @Autowired
    IPrestamo iprestamo;

    @PayloadRoot(localPart = "GuardarPrestamoRequest" ,namespace = "https://t4is.uv.mx/prestamo")
    @ResponsePayload
    public GuardarPrestamoResponse guardarLibro(@RequestPayload GuardarPrestamoRequest peticion) {  
        GuardarPrestamoResponse respuesta = new GuardarPrestamoResponse();
        
        Prestamo prestamo = new Prestamo();
        
        prestamo.setFinicio(peticion.getFinicio());
        prestamo.setFfin(peticion.getFfin());
        prestamo.setNomcliente(peticion.getNomcliente());
        prestamo.setTitulolibro(peticion.getTitulolibro());
        prestamo.setStatus(peticion.getStatus());
        prestamo.setResponsable(peticion.getResponsable());
        iprestamo.save(prestamo);
        respuesta.setRespuesta("Hola" +peticion.getNomcliente() + "acaba de apartar el libre "+peticion.getTitulolibro() + " hasta "+peticion.getFfin());
        return respuesta;  
    }

    /*
    @PayloadRoot(localPart = "VerStatusRequest" ,namespace = "https://t4is.uv.mx/prestamo")
    @ResponsePayload
    public VerStatusResponse verstatus(@RequestPayload VerStatusRequest peticion) {  
        VerStatusResponse respuesta = new VerStatusResponse();
        
        String id = Integer.toString(peticion.getId());


        Prestamo prestamo = new Prestamo();
        
        prestamo.setFfin(peticion.getFfin());
        prestamo.setNomcliente(peticion.getNomcliente());
        prestamo.setTitulolibro(peticion.getTitulolibro());
        prestamo.setStatus(peticion.getStatus());
        prestamo.setResponsable(peticion.getResponsable());
        
        respuesta.setRespuesta("Hola" +peticion.getNomcliente() + "acaba de apartar el libre "+peticion.getTitulolibro() + " hasta "+peticion.getFfin());
        return respuesta;  
    }
    */


}
