package mf0227.uf2404.actividad3.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import mf0227.uf2404.actividad3.pojo.Libro;

public class LibroDaoHashMap implements LibroDao {

	private HashMap<Integer, Libro> hmLibros = new HashMap<Integer, Libro>();
	private int indice = 0;
	private static LibroDaoHashMap INSTANCE = null;

	private LibroDaoHashMap() {
		super();
		hmLibros.put(1, new Libro(1, "Harry Potter y la piedra filosofal", 255));
		hmLibros.put(2, new Libro(2, "El silencio de la ciudad blanca", 480));
		hmLibros.put(3, new Libro(3, "En llamas", 407));
		hmLibros.put(4, new Libro(4, "El código da Vinci", 624));
		hmLibros.put(5, new Libro(5, "Don Quijote", 1345));
		indice = 6;
	}

	public static synchronized LibroDaoHashMap getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new LibroDaoHashMap();
		}

		return INSTANCE;
	}

	@Override
	public List<Libro> getAll() {
		return new ArrayList<>(hmLibros.values());
	}

	@Override
	public Libro getById(int id) {
		return hmLibros.get(id);
	}

	@Override
	public boolean delete(int id) {
		return hmLibros.remove(id) == null ? false : true;
	}

	@Override
	public boolean insert(Libro l) {
		boolean isAnadido = true;

		for (Iterator<Integer> iterator = hmLibros.keySet().iterator(); iterator.hasNext();) {
			int key = (Integer) iterator.next();
			Libro libro = hmLibros.get(key);
			isAnadido = libro.getNombre().equalsIgnoreCase(l.getNombre()) ? false : true;
			if (!isAnadido) {
				break;
			}
		}

		if (isAnadido) {
			l.setId(indice);
			hmLibros.put(l.getId(), l);
			indice++;
		}

		return isAnadido;
	}

	@Override
	public boolean modify(Libro l) {
		boolean nombreCoincide = true;

		for (Iterator<Integer> iterator = hmLibros.keySet().iterator(); iterator.hasNext();) {
			int key = (Integer) iterator.next();
			Libro libro = hmLibros.get(key);
			nombreCoincide = (libro.getNombre().equalsIgnoreCase(l.getNombre()) && libro.getId() != l.getId()) ? true
					: false;
			if (nombreCoincide) {
				break;
			}
		}

		if (!nombreCoincide) {
			hmLibros.put(l.getId(), l);
		}

		return nombreCoincide;
	}

}
