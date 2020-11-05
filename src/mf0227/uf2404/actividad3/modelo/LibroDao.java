package mf0227.uf2404.actividad3.modelo;

import java.util.List;

import mf0227.uf2404.actividad3.pojo.Libro;

public interface LibroDao {

	/**
	 * Obtiene todos los libros ordenados Alfabeticamente
	 * 
	 * @return List<Libro>, si no existen libros Lista vacia pero no nula
	 * @throws Exception si hay problema con la BBDD
	 */
	List<Libro> getAll() throws Exception;

	/**
	 * Recupera un libro por su identificador
	 * 
	 * @param id int identificador
	 * @return Libro, null si no lo encuentra
	 * @throws Exception si hay problema con la BBDD
	 */
	Libro getById(int id) throws Exception;

	/**
	 * Eliminar un Libro por su identificador
	 * 
	 * @param id int identificador
	 * @return true si elimina, false en caso contrario, por ejemplo si no existe el
	 *         id
	 * @throws Exception si hay problema con la BBDD
	 */
	boolean delete(int id) throws Exception;

	/**
	 * Inserta un nuevo Libro y actuliza su id
	 * 
	 * @param l Libro a crear con id == 0, despues de insertar lo debe actualizar
	 * @return true si lo crea, false si no puede, por ejemplo si ya existe el
	 *         nombre
	 * @throws Exception si hay problema con la BBDD
	 */
	boolean insert(Libro l) throws Exception;

	/**
	 * Modifica el Libro
	 * 
	 * @param l Libro a modificar
	 * @return true si modifica, false si no puede, por ejemplo si ya existe el
	 *         nombre
	 * @throws Exception si hay problema con la BBDD
	 */
	boolean modify(Libro l) throws Exception;

}
