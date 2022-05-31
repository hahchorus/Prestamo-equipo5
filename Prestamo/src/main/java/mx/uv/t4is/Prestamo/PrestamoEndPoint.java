package mx.uv.t4is.Prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.prestamo.GuardarPrestamoRequest;
import https.t4is_uv_mx.prestamo.GuardarPrestamoResponse;
import https.t4is_uv_mx.prestamo.MostrarPrestamoResponse;
import https.t4is_uv_mx.prestamo.StatusPrestamoRequest;
import https.t4is_uv_mx.prestamo.StatusPrestamoResponse;


@Endpoint
public class PrestamoEndPoint {
    
    @Autowired
    IPrestamo iprestamo;

    @PayloadRoot(localPart = "GuardarPrestamoRequest" ,namespace = "https://t4is.uv.mx/prestamo")
    @ResponsePayload
    public GuardarPrestamoResponse guardarPrestamo(@RequestPayload GuardarPrestamoRequest peticion) {  
        GuardarPrestamoResponse respuesta = new GuardarPrestamoResponse();
        
        Prestamo prestamo = new Prestamo();
        
        prestamo.setFinicio(peticion.getFinicio());
        prestamo.setFfin(peticion.getFfin());
        prestamo.setNomcliente(peticion.getNomcliente());
        prestamo.setTitulolibro(peticion.getTitulolibro());
        prestamo.setStatus(peticion.getStatus());
        prestamo.setResponsable(peticion.getResponsable());
        iprestamo.save(prestamo);
        respuesta.setRespuesta("Hola " +peticion.getNomcliente() + " acaba de apartar el libre "+peticion.getTitulolibro() + " hasta "+peticion.getFfin());
        return respuesta;  
    }

    @PayloadRoot(localPart = "MostrarPrestamoRequest" ,namespace = "https://t4is.uv.mx/prestamo")
    @ResponsePayload
    public MostrarPrestamoResponse mostrarprestamo() {
        
        MostrarPrestamoResponse respuesta = new MostrarPrestamoResponse();
        Iterable<Prestamo> lista = iprestamo.findAll();
        
        for (Prestamo o : lista) {
            MostrarPrestamoResponse.Prestamo e = new MostrarPrestamoResponse.Prestamo();
            e.setId(o.getId());
            e.setFinicio(o.getFinicio());
            e.setFfin(o.getFfin());
            e.setNomcliente(o.getNomcliente());
            e.setTitulolibro(o.getTitulolibro());
            e.setStatus(o.getStatus());
            e.setResponsable(o.getResponsable());
            respuesta.getPrestamo().add(e);
        }

        return respuesta;
    }

    @PayloadRoot(localPart = "StatusPrestamoRequest" ,namespace = "https://t4is.uv.mx/prestamo")
    @ResponsePayload
    public StatusPrestamoResponse statusprestamo (@RequestPayload StatusPrestamoRequest peticion){

        StatusPrestamoResponse respuesta = new StatusPrestamoResponse();
        String id = Integer.toString(peticion.getId());
        Iterable<Prestamo> listaprestamos = iprestamo.findByIdAndTitulolibrpo(peticion.getId(), peticion.getTitulolibro());
        for (Prestamo prestamo : listaprestamos) {
            
            StatusPrestamoResponse.Prestamo p = new StatusPrestamoResponse.Prestamo();
            

        }

        return null;
    }

}
