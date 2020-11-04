package mf0227.uf2404.actividad3.main;

import java.util.ArrayList;
import java.util.Scanner;

import mf0227.uf2404.actividad3.modelo.LibroDao;
import mf0227.uf2404.actividad3.modelo.LibroDaoHashMap;
import mf0227.uf2404.actividad3.pojo.Libro;

public class AppLibreria extends AppGestion {

	private final static String POJO = "Libros";

	private static Scanner sc = new Scanner(System.in);
	private static String opc = "";
	private static LibroDao dao = LibroDaoHashMap.getInstance();

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
				modificar();
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
	 */
	private static void modificar() {
		System.out.println("Aún no está implementado...");
	}

	/**
	 * Elimina un Recurso
	 */
	private static void eliminar() {
		int id = 0;
		boolean correcto = false;
		boolean eliminado;

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

		eliminado = dao.delete(id);

		if (eliminado) {
			System.out.println("Libro eliminado");
		} else {
			System.err.println("No se ha encontrado al libro cuyo id es " + id);
		}
	}

	/**
	 * Lista todos los recursos
	 */
	private static void listar() {
		ArrayList<Libro> libros = (ArrayList<Libro>) dao.getAll();
		for (Libro libro : libros) {
			System.out.println(libro);
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

		insertado = dao.insert(l);

		if (insertado) {
			System.out.println("Libro insertado");
		} else {
			System.err.println("Ya hay un libro con ese nombre");
		}
	}

}
