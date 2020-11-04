package mf0227.uf2404.actividad3.modelo;

import java.util.List;

import mf0227.uf2404.actividad3.pojo.Libro;

public interface LibroDao {

	/**
	 * Obtiene todos los libros ordenados Alfabeticamente
	 * 
	 * @return List<Libro>, si no existen libros Lista vacia pero no nula
	 */
	List<Libro> getAll();

	/**
	 * Recupera un libro por su identificador
	 * 
	 * @param id int identificador
	 * @return Libro, null si no lo encuentra
	 */
	Libro getById(int id);

	/**
	 * Eliminar un Libro por su identificador
	 * 
	 * @param id int identificador
	 * @return true si elimina, false en caso contrario, por ejemplo si no existe el
	 *         id
	 */
	boolean delete(int id);

	/**
	 * Inserta un nuevo Libro y actuliza su id
	 * 
	 * @param l Libro a crear con id == 0, despues de insertar lo debe actualizar
	 * @return true si lo crea, false si no puede, por ejemplo si ya existe el
	 *         nombre
	 */
	boolean insert(Libro l);

	// modificar queda fuera del alcance de este proyecto

}
