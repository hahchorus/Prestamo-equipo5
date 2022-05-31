package mx.uv.t4is.Prestamo;

import org.springframework.data.repository.CrudRepository;

public interface IPrestamo extends CrudRepository<Prestamo,Integer>{
    Iterable<Prestamo> findByIdAndTitulolibrpo (int id, String titulolibro);
}
