package mx.uv.t4is.Prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.prestamo.GuardarPrestamoRequest;
import https.t4is_uv_mx.prestamo.GuardarPrestamoResponse;
import https.t4is_uv_mx.prestamo.MostrarPrestamoResponse;
//import https.t4is_uv_mx.prestamo.PrestamoPorClienteRequest;
//import https.t4is_uv_mx.prestamo.PrestamoPorClienteResponse;
import https.t4is_uv_mx.prestamo.RenovarPrestamoRequest;
import https.t4is_uv_mx.prestamo.RenovarPrestamoResponse;
//import https.t4is_uv_mx.prestamo.StatusPrestamoRequest;
//import https.t4is_uv_mx.prestamo.StatusPrestamoResponse;


@Endpoint
public class PrestamoEndPoint {
    
    @Autowired
    IPrestamo iprestamo;

    //Realizar un prestamo
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
        respuesta.setRespuesta("Hola " +peticion.getNomcliente() + " acaba de apartar el libro "+peticion.getTitulolibro() + " hasta "+peticion.getFfin());
        return respuesta;  
    }

    //Ver todos los prestamos
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

    //Renovar prestamo
    @PayloadRoot(localPart = "RenovarPrestamoRequest" ,namespace = "https://t4is.uv.mx/prestamo")
    @ResponsePayload
    public RenovarPrestamoResponse renovarprestamo (@RequestPayload RenovarPrestamoRequest peticion){
        
        RenovarPrestamoResponse respuesta = new RenovarPrestamoResponse();

        Prestamo prestamo = new Prestamo();
        prestamo.setId(peticion.getId());
        prestamo.setFinicio(peticion.getFinicio());
        prestamo.setFfin(peticion.getFfin());
        prestamo.setNomcliente(peticion.getNomcliente());
        prestamo.setTitulolibro(peticion.getTitulolibro());
        prestamo.setStatus(peticion.getStatus());
        prestamo.setResponsable(peticion.getResponsable());
        iprestamo.save(prestamo);
        respuesta.setRespuesta("Ha renovado el prestamo del libro " +peticion.getTitulolibro() + " hasta el "+peticion.getFfin());
        
        return respuesta;
    }

    //Ver status del prestamo
    /*@PayloadRoot(localPart = "StatusPrestamoRequest" ,namespace = "https://t4is.uv.mx/prestamo")
    @ResponsePayload
    public StatusPrestamoResponse statusprestamo (@RequestPayload StatusPrestamoRequest peticion){

        StatusPrestamoResponse respuesta = new StatusPrestamoResponse();
        String id = Integer.toString(peticion.getId());
        Iterable<Prestamo> listaprestamos = iprestamo.findByIdAndTitulolibro(peticion.getId(), peticion.getTitulolibro());
        for (Prestamo prestamo : listaprestamos) {
            
           // StatusPrestamoResponse.Prestamo p = new StatusPrestamoResponse.Prestamo();
            

        }

        return null;
    }

    //Buscar prestamo por cliente
    @PayloadRoot(localPart = "PrestamoPorClienteRequest", namespace = "https://t4is.uv.mx/prestamo")
    @ResponsePayload
    public PrestamoPorClienteResponse prestamocliente (@RequestPayload PrestamoPorClienteRequest peticion){

        return null;
    }
    */
    

}

//Método buscar libro
/*@PayloadRoot(localPart = "BuscarLibroRequest" ,namespace = "https://t4is.uv.mx/libro")
@ResponsePayload
public BuscarLibroResponse buscarLibro(@RequestPayload BuscarLibroRequest peticion) {
    BuscarLibroResponse respuesta = new BuscarLibroResponse();
    String id = Integer.toString(peticion.getId());
    
    Iterable<Libro> listaLibros = ilibro.findByIdAndTitulo(peticion.getId(), peticion.getTitulo());
    for (Libro libro : listaLibros){
        BuscarLibroResponse.Libros l = new BuscarLibroResponse.Libros();
        l.setId(libro.getId());
        l.setTitulo(libro.getTitulo());
        l.setAutor(libro.getAutor());
        l.setEditorial(libro.getEditorial());
        l.setCategoria(libro.getCategoria());
        l.setDescripcion(libro.getDescripcion());
        l.setEdadR(libro.getEdadR());
        respuesta.getLibros().add(l);
    }
    return respuesta;

}
*/
