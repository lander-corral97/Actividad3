package mf0227.uf2404.actividad3.pojo;

/**
 * Clase para representar un libro.<br>
 * Tiene los siguientes atributos:
 * <ul>
 * <li>id: <code>int</code> [<b>Identificador</b>]</li>
 * <li>nombre: <code>String</code></li>
 * <li>numPag: <code>int</code></li>
 * </ul>
 * 
 * @author Lander Corral
 *
 */

public class Libro {

	private int id;
	private String nombre;
	private int numPag;

	public Libro() {
		super();
		this.id = 0;
		this.nombre = "";
		this.numPag = 0;
	}

	public Libro(int id, String nombre, int numPag) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numPag = numPag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumPag() {
		return numPag;
	}

	public void setNumPag(int numPag) {
		this.numPag = numPag;
	}

	@Override
	public String toString() {
		return String.format("Libro (id: %s):\nNombre: %s, Número de páginas: %s\n---------------------------------\n",
				this.id, this.nombre, this.numPag);
	}

}
