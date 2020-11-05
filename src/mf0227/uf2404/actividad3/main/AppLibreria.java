package mf0227.uf2404.actividad3.main;

import java.util.ArrayList;
import java.util.Scanner;

import mf0227.uf2404.actividad3.modelo.LibroDAOSqlite;
import mf0227.uf2404.actividad3.modelo.LibroDao;
import mf0227.uf2404.actividad3.pojo.Libro;

/**
 * Realizar un programa para gestionar una librería.<br>
 * Un libro se compone de los siguientes campos ( id, nombre y número de páginas
 * ).<br>
 * Hay que realizar una interfaz gráfica donde se muestre un menú con las
 * siguientes opciones:<br>
 * Listar todos los libros, dar de baja un libro, modificar un libro y añadir
 * uno nuevo.
 * 
 * 
 * @author Lander Corral
 *
 */
public class AppLibreria extends AppGestion {

	private final static String POJO = "Libros";

	private final static String OP_NOMBRE = "1";
	private final static String OP_NUMPAG = "2";

	private final static String SI_BORRAR = "S";
	private final static String NO_SEGUIR = "N";

	private static Scanner sc = new Scanner(System.in);
	private static String opc = "";
	private static LibroDao dao = LibroDAOSqlite.getInstance();

	public static void main(String[] args) {

		do {
			pintarMenu(POJO);

			opc = sc.nextLine().toLowerCase();

			switch (opc) {
			case OP_LISTAR:
				listar();
				break;
			case OP_CREAR:
				crear();
				break;
			case OP_ELIMINAR:
				eliminar();
				break;
			case OP_MODIFICAR:
				try {
					modificar();
				} catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
				}
				break;
			case OP_SALIR:
				break;
			default:
				System.err.println("No has introducido una opción correcta, por eso, seguirás en el bucle");
				break;
			}
		} while (!OP_SALIR.equals(opc));

	}

	/**
	 * Modifica el Recurso<br>
	 * 
	 * <b>NO HACER EN ESTA ACTIVIDAD</b>
	 * 
	 * @throws Exception si no mete un número
	 */
	private static void modificar() throws Exception {
		int id;
		boolean isContinuar = true;
		String siNo = "";

		System.out.println("Introduce el id del libro a modificar");
		try {
			id = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			throw new Exception("No has metido un número");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ha ocurrido un error: ");
		}

		Libro l = dao.getById(id);

		if (l != null) {
			do {
				System.out.println("¿Qué quieres modificar?");
				System.out.println("************************************");
				System.out.println("1. Nombre");
				System.out.println("2. Número de páginas");
				System.out.println("************************************");

				opc = sc.nextLine();

				switch (opc) {
				case OP_NOMBRE:
					System.out.println("Introduce el nuevo nombre");
					l.setNombre(sc.nextLine());
					break;
				case OP_NUMPAG:
					System.out.println("Introduce el número de páginas");
					try {
						l.setNumPag(Integer.parseInt(sc.nextLine()));
					} catch (NumberFormatException e) {
						System.err.println("No has metido un número");
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println("Ha ocurrido un error: ");
					}
					break;
				default:
					System.err.println("No has introducido una opción correcta");
					break;
				}

				System.out.println("Si quieres salir pulsa N");
				siNo = sc.nextLine().toUpperCase();

				if (NO_SEGUIR.equals(siNo)) {
					isContinuar = false;
				}

			} while (isContinuar);

			if (dao.modify(l)) {
				System.err.println("Hay un libro con ese nombre");
			} else {
				System.out.println("Libro modificado");
			}
		} else {
			System.err.println("No hay un libro con el id " + id);
		}
	}

	/**
	 * Elimina un Recurso
	 */
	private static void eliminar() {
		int id = 0;
		boolean correcto = false;
		boolean eliminado;
		String borrar = "";

		do {
			System.out.println("Introduce el id a eliminar");
			try {
				id = Integer.parseInt(sc.nextLine());
				correcto = true;
			} catch (NumberFormatException e) {
				System.err.println("No has metido un número");
			} catch (Exception e) {
				System.err.println("Ha ocurrido un error: ");
				e.printStackTrace();
			}
		} while (!correcto);

		System.out.println("¿Estás seguro de que quieres borrar? (S/N)");
		borrar = sc.nextLine().toUpperCase();

		if (SI_BORRAR.equals(borrar)) {
			try {
				eliminado = !dao.delete(id);

				if (eliminado) {
					System.out.println("Libro eliminado");
				} else {
					System.err.println("No se ha encontrado al libro cuyo id es " + id);
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage()); // Sin BBDD no entra aquí
			}
		}
	}

	/**
	 * Lista todos los recursos
	 */
	private static void listar() {
		ArrayList<Libro> libros;
		try {
			libros = (ArrayList<Libro>) dao.getAll();
			for (Libro libro : libros) {
				System.out.println(libro);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());// Sin BBDD no entra aquí
		}
	}

	/**
	 * Pide por pantalla y crea un nuevo recurso
	 */
	private static void crear() {
		Libro l = new Libro();
		boolean correcto = false;
		boolean insertado;

		System.out.println("Introduce el título del libro: ");
		l.setNombre(sc.nextLine());
		do {
			System.out.println("Introduce el número de páginas: ");
			try {
				l.setNumPag(Integer.parseInt(sc.nextLine()));
				correcto = true;
			} catch (NumberFormatException e) {
				System.err.println("No has metido un número");
			} catch (Exception e) {
				System.err.println("Ha ocurrido un error: ");
				e.printStackTrace();
			}
		} while (!correcto);

		try {
			insertado = dao.insert(l);
			if (insertado) {
				System.out.println("Libro insertado");
			} else {
				System.err.println("Ya hay un libro con ese nombre");
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

}
