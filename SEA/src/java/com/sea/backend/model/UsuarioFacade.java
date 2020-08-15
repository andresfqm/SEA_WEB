/*
 * The MIT License
 *
 * Copyright 2017 Depurador.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sea.backend.model;

import com.sea.backend.entities.Ciudad;
import com.sea.backend.entities.Direccion;
import com.sea.backend.entities.Email;
import com.sea.backend.entities.Telefono;
import com.sea.backend.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andres Quintana
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

	@PersistenceContext(unitName = "SEAPU2")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioFacade() {
		super(Usuario.class);
	}

	@Override
	public Usuario iniciarSesion(Usuario us) {

		Usuario usuario = null;
		String consulta;
		try {
			consulta = "FROM Usuario u WHERE u.nombreUsuario = ?1 and u.contrasena = ?2";

			Query query = em.createQuery(consulta);
			query.setParameter(1, us.getNombreUsuario());
			query.setParameter(2, us.getContrasena());

			List<Usuario> lista = query.getResultList();
			if (!lista.isEmpty()) {
				usuario = lista.get(0);
			}
		} catch (Exception e) {

			throw e;

		}
		return usuario;
	}

	@Override
	public List<Usuario> listaUsuario() {
		List<Usuario> lista;
		String jpql = "select u.nombre, u.apellido, u.numeroDocumento, u.idInterno, t.numeroTelefono, e.email, u.nombreUsuario, c.cargo, u.idUsuario, u.habilitado from Usuario  u\n"
				+ "join u.telefonoList t\n"
				+ "join u.emailList e\n"
				+ "join u.tblCargoIdCargo c\n"
				+ "join c.cargoPerfilList cape\n"
				+ "join cape.tblPerfilIdPerfil pe\n"
				+ "GROUP BY u.nombre";
		Query query = em.createQuery(jpql);
		lista = query.getResultList();

		return lista;
	}

	@Override
	public Usuario listaUsuario(String us) {
		Usuario usuario = null;
		List<Usuario> lista;
		String jpql = "FROM Usuario u where u.nombre = ?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, us);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			usuario = lista.get(0);
		}

		return usuario;
	}

	@Override
	public Direccion actualizarCiudad(Usuario ci) {
		Direccion direccion = null;
		List<Direccion> lista;
		String jpql = "FROM Direccion d where d.tblUsuarioIdUsuario = ?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, ci);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			direccion = lista.get(0);
		}

		return direccion;
	}

	@Override
	public void actualizarNumeroCotizacion(int us, int numeroCotizacion) {
		String jpql = "update tbl_usuario set CONSECUTIVO_COTIZACION = ?1 + 1 where id_usuario = ?2";
		Query query = em.createNativeQuery(jpql);
		query.setParameter(1, numeroCotizacion);
		query.setParameter(2, us);
		query.executeUpdate();
	}

	@Override
	public boolean buscarIdInterno(String idInterno) {
		List<Usuario> lista = new ArrayList<>();
		Query query = em.createNamedQuery("Usuario.findByIdInterno", Usuario.class);
		query.setParameter("idInterno", idInterno);
		lista = query.getResultList();

		if (lista != null) {
			if (lista.size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Usuario recuperarContraseña(Usuario us) {
		Usuario usuario = null;
		String consulta;
		try {
			consulta = "FROM Usuario u WHERE u.nombreUsuario = ?1 and u.idInterno = ?2";

			Query query = em.createQuery(consulta);
			query.setParameter(1, us.getNombreUsuario());
			query.setParameter(2, us.getIdInterno());

			List<Usuario> lista = query.getResultList();
			if (!lista.isEmpty()) {
				usuario = lista.get(0);
			}
		} catch (Exception e) {

			throw e;

		}
		return usuario;
	}

	@Override
	public Telefono buscarTelefonoUsuario(Usuario ci) {
		Telefono telefono = null;
		List<Telefono> lista;
		String jpql = "FROM Telefono t where t.tblUsuarioIdUsuario = ?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, ci);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			telefono = lista.get(0);
		}

		return telefono;
	}

	@Override
	public Email buscarEmailUsuario(Usuario ci) {
		Email email = null;
		List<Email> lista;
		String jpql = "FROM Email e where e.tblUsuarioIdUsuario = ?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, ci);
		lista = query.getResultList();
		if (!lista.isEmpty()) {
			email = lista.get(0);
		}

		return email;
	}

	@Override
	public boolean buscarEmail(String email) {
		List<Email> lista = new ArrayList<>();
		Query query = em.createNamedQuery("Email.findByEmail", Email.class);
		query.setParameter("email", email);
		lista = query.getResultList();
		if (lista != null) {
			if (lista.size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean buscarNumeroDocuemnto(String numeroDocumento) {
		List<Usuario> lista = new ArrayList<>();
		Query query = em.createNamedQuery("Usuario.findByNumeroDocumento", Usuario.class);
		query.setParameter("numeroDocumento", numeroDocumento);
		lista = query.getResultList();

		if (lista != null) {
			if (lista.size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean buscarExistensiaDeUsuario(String usuario) {
		List<Usuario> lista = new ArrayList<>();
		Query query = em.createNamedQuery("Usuario.findByNombreUsuario", Usuario.class);
		query.setParameter("nombreUsuario", usuario);
		lista = query.getResultList();

		if (lista != null) {
			if (lista.size() > 0) {
				return true;
			}
		}
		return false;
	}

}
