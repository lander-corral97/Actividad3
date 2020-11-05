package mf0227.uf2404.actividad3.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mf0227.uf2404.actividad3.pojo.Libro;

public class LibroDAOSqlite implements LibroDao {

	private static final String DRIVER_URL = "jdbc:sqlite:ddbb/libros.db";
	private static LibroDAOSqlite INSTANCE = null;

	private LibroDAOSqlite() {
		super();
	}

	public synchronized static LibroDAOSqlite getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LibroDAOSqlite();
		}
		return INSTANCE;
	}

	@Override
	public List<Libro> getAll() throws Exception {
		ArrayList<Libro> libros = new ArrayList<Libro>();
		String sql = "Select * From libro;";

		try (Connection cnn = DriverManager.getConnection(DRIVER_URL);
				PreparedStatement pst = cnn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				Libro l = new Libro();
				l.setId(rs.getInt("id"));
				l.setNombre(rs.getString("name"));
				l.setNumPag(rs.getInt("page_number"));

				libros.add(l);
			}

		} catch (SQLException e) {
			throw new Exception("Ha habido un problema con la conexión a la BBDD...");
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}

		return libros;
	}

	@Override
	public Libro getById(int id) throws Exception {
		Libro l = null;
		String sql = "Select * From libro Where id = ?";

		try (Connection cnn = DriverManager.getConnection(DRIVER_URL);
				PreparedStatement pst = cnn.prepareStatement(sql);) {

			pst.setInt(1, id);

			System.out.println(pst);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					l = new Libro();
					l.setId(id);
					l.setNombre(rs.getString("name"));
					l.setNumPag(rs.getInt("page_number"));
				}
			}

		} catch (SQLException e) {
			throw new Exception("Ha habido un problema con la conexión a la BBDD...");
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}

		return l;
	}

	@Override
	public boolean delete(int id) throws Exception {

		boolean resul = false;
		String sql = "Delete From libro Where id = ?;";

		try (Connection cnn = DriverManager.getConnection(DRIVER_URL);
				PreparedStatement pst = cnn.prepareStatement(sql);) {

			pst.setInt(1, id);

			if (pst.executeUpdate() == 0) {
				resul = true;
			}

		} catch (SQLException e) {
			throw new Exception("Ha habido un problema con la conexión a la BBDD...");
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}

		return resul;
	}

	@Override
	public boolean insert(Libro l) throws Exception {
		boolean resul = false;
		String sql = "Insert into libro (name, page_number) Values (?, ?);";

		try (Connection cnn = DriverManager.getConnection(DRIVER_URL);
				PreparedStatement pst = cnn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {

			pst.setString(1, l.getNombre());
			pst.setInt(2, l.getNumPag());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 1) {
				resul = true;
				try (ResultSet rsKeys = pst.getGeneratedKeys()) {
					if (rsKeys.next()) {
						int id = rsKeys.getInt(1);
						l.setId(id);
					}
				}
			}

		} catch (SQLException e) {
			throw new Exception("Ha habido un problema con la conexión a la BBDD...");
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}

		return resul;
	}

	@Override
	public boolean modify(Libro l) throws Exception {
		boolean resul = true;
		String sql = "Update libro set name = ?, page_number = ? Where id = ?;";

		try (Connection cnn = DriverManager.getConnection(DRIVER_URL);
				PreparedStatement pst = cnn.prepareStatement(sql);) {

			pst.setString(1, l.getNombre());
			pst.setInt(2, l.getNumPag());
			pst.setFloat(3, l.getId());

			if (pst.executeUpdate() == 1) {
				resul = false;
			}

		} catch (SQLException e) {
			throw new Exception("Ha habido un problema con la conexión a la BBDD...");
		} catch (Exception e) {
			throw new Exception("Ha ocurrido un error");
		}

		return resul;
	}

}
