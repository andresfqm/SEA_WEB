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

import com.sea.backend.dto.ClienteDTO;
import com.sea.backend.entities.Cliente;
import com.sea.backend.entities.Email;
import com.sea.backend.entities.Telefono;
import com.sea.backend.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Depurador
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

	@PersistenceContext(unitName = "SEAPU2")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ClienteFacade() {
		super(Cliente.class);
	}

	@Override
	public ClienteDTO datosCliente(int idCliente) {
		/*
        Nomenclatura de la consulta
        c: cliente
        o: origen
        ca: cambio asesor
        td: Tipo documento
        t: telefono
        e: e-mail
        tt: tipo teléfono
        te: tipo e-mail
        d: direccion
        tdi: tipo direccion
        ci: ciudad
        de: departamento
		 */
		ClienteDTO cli = new ClienteDTO();
		List<Object[]> ls;

		String consulta = "SELECT ci.NOMBRE,de.NOMBRE, e.email, t.numero_telefono FROM "
				+ "TBL_CLIENTE c "
				+ "INNER JOIN "
				+ "TBL_ORIGEN o ON c.TBL_ORIGEN_ID_ORIGEN = o.ID_ORIGEN AND c.ID_CLIENTE=?1 "
				+ "LEFT JOIN "
				+ "TBL_COMENTARIO_CAMBIO_ASESOR ca ON c.TBL_COMENTARIO_CAMBIO_ASESOR_ID_COMENTARIO_CAMBIO_ASESOR = ca.ID_COMENTARIO_CAMBIO_ASESOR "
				+ "INNER JOIN "
				+ "TBL_TIPO_DOCUMENTO td ON c.TBL_TIPO_DOCUMENTO_ID_TIPO_DOCUMENTO = td.ID_TIPO_DOCUMENTO "
				+ "INNER JOIN "
				+ "TBL_TELEFONO t ON c.ID_CLIENTE = t.TBL_CLIENTE_ID_CLIENTE "
				+ "INNER JOIN "
				+ "TBL_EMAIL e ON c.ID_CLIENTE = e.TBL_CLIENTE_ID_CLIENTE "
				+ "INNER JOIN "
				+ "TBL_TIPO_TELEFONO tt ON t.TBL_TIPO_TELEFONO_ID_TIPO_TELEFONO = tt.ID_TIPO_TELEFONO "
				+ "INNER JOIN "
				+ "TBL_TIPO_EMAIL te ON e.TBL_TIPO_EMAIL_ID_TIPO_EMAIL = te.ID_TIPO_EMAIL "
				+ "INNER JOIN "
				+ "TBL_DIRECCION d ON d.TBL_CLIENTE_ID_CLIENTE = c.ID_CLIENTE "
				+ "INNER JOIN "
				+ "TBL_TIPO_DIRECCION tdi ON d.TBL_TIPO_DIRECCION_ID_TIPO_DIRECCION = tdi.ID_TIPO_DIRECCION "
				+ "INNER JOIN "
				+ "TBL_CIUDAD ci ON d.TBL_CIUDAD_ID_CIUDAD = ci.ID_CIUDAD "
				+ "INNER JOIN "
				+ "TBL_DEPARTAMENTO de ON ci.TBL_DEPARTAMENTO_ID_DEPARTAMENTO = de.ID_DEPARTAMENTO";
		Query query = em.createNativeQuery(consulta);
		query.setParameter(1, idCliente);

		ls = query.getResultList();
		int count = 0;
		if (ls.size() > 0) {
			for (Object[] ob : ls) {
				if (count == 1) {
					if (!(cli.getEmail1().equals(ob[2].toString())));
					cli.setEmail2(ob[2].toString());
					if (!(cli.getTelefono1().equals(ob[3].toString()))) {
						cli.setTelefono2(ob[3].toString());
					}
				} else if (count == 2) {
					if (cli.getEmail2() != null) {
						if (!(cli.getEmail2().equals(ob[2].toString()) || cli.getEmail1().equals(ob[2].toString()))) {
							cli.setEmail2(ob[2].toString());
						}
					}

					if (cli.getTelefono2() != null) {
						if (!(cli.getTelefono2().equals(ob[3].toString()) || cli.getTelefono1().equals(ob[3].toString()))) {
							cli.setTelefono2(ob[3].toString());
						}
					}
				} else if (count == 3) {
					if (cli.getEmail2() != null) {
						if (!(cli.getEmail2().equals(ob[2].toString()) || cli.getEmail1().equals(ob[2].toString()))) {
							cli.setEmail2(ob[2].toString());
						}
					}

					if (cli.getTelefono2() != null) {
						if (!(cli.getTelefono2().equals(ob[3].toString()) || cli.getTelefono1().equals(ob[3].toString()))) {
							cli.setTelefono2(ob[3].toString());
						}
					}
				} else if (count == 4) {
					if (cli.getEmail2() != null) {
						if (!(cli.getEmail2().equals(ob[2].toString()) || cli.getEmail1().equals(ob[2].toString()))) {
							cli.setEmail2(ob[2].toString());
						}
					}

					if (cli.getTelefono2() != null) {
						if (!(cli.getTelefono2().equals(ob[3].toString()) || cli.getTelefono1().equals(ob[3].toString()))) {
							cli.setTelefono2(ob[3].toString());
						}
					}
				} else {
					cli.setCiudad(ob[0].toString());
					cli.setDepartamento(ob[1].toString());
					cli.setEmail1(ob[2].toString());
					cli.setEmail2("");
					cli.setTelefono1(ob[3].toString());
					cli.setTelefono2("");
					System.out.println("OK");
				}
				count = count + 1;
			}
		}
		//Object datosCliente = query.getSingleResult();

		return cli;
	}

	@Override
	public List<Cliente> listaClienteCotizacion(Usuario id) {
		List<Cliente> lista;
		String jpql = "SELECT c.idCliente, c.nombreORazonSocial, c.tblUsuarioIdUsuario FROM Cliente c\n"
				+ "WHERE c.tblUsuarioIdUsuario = ?1 AND c.activo = 1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, id);
		lista = query.getResultList();

		return lista;
	}

	@Override
	public List<Cliente> listaClientes() {
		List<Cliente> lista;
		String jpql = "SELECT cl.numeroDocumento, cl.nombreORazonSocial, d.direccion, t.numeroTelefono, e.email, cl.nombreContacto, u.nombre, u.apellido,cl.digitoVerificacion, cl.idCliente, cl.activo FROM Cliente cl\n"
				+ "JOIN cl.direccionList d\n"
				+ "JOIN cl.telefonoList t\n"
				+ "JOIN cl.emailList e\n"
				+ "JOIN cl.tblUsuarioIdUsuario u GROUP BY cl.numeroDocumento\n";
		Query query = em.createQuery(jpql);
		lista = query.getResultList();

		return lista;
	}

//	@Override
//	public void eliminarCliente(int idCliente) {
//		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("eliminarCliente");
//		query.setParameter("idCliente", idCliente);
//		query.execute();
//	}
	@Override
	public boolean consultarRegistroIdentificacion(String numeroDocumento) {
		List<Cliente> lista = new ArrayList<>();

		boolean existencia = false;
		Query query = em.createNamedQuery("Cliente.findByNumeroDocumento", Cliente.class);
		query.setParameter("numeroDocumento", numeroDocumento);
		lista = query.getResultList();

		if (lista.size() > 0) {
			existencia = true;
		}
		return existencia;
	}

	@Override
	public List<Telefono> listaTelefonosCliente(Cliente cl) {
		List<Telefono> lista;
		String jpql = "FROM Telefono t where t.tblClienteIdCliente = ?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, cl);
		lista = query.getResultList();
		return lista;
	}

	@Override
	public List<Email> listaEmailsCliente(Cliente cl) {
		List<Email> lista;
		String jpql = "FROM Email e where e.tblClienteIdCliente = ?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, cl);
		lista = query.getResultList();
		return lista;
	}

}
