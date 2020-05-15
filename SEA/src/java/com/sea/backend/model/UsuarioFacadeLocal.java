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

import com.sea.backend.entities.Direccion;
import com.sea.backend.entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Depurador
 */
@Local
public interface UsuarioFacadeLocal {

	void create(Usuario usuario);

	void edit(Usuario usuario);

	void remove(Usuario usuario);

	void actualizarNumeroCotizacion(int us, int numeroCotizacion);

	Usuario find(Object id);

	List<Usuario> findAll();

	List<Usuario> findRange(int[] range);

	int count();

	Usuario iniciarSesion(Usuario us);

	Usuario recuperarContraseña(Usuario us);

	List<Usuario> listaUsuario();

	public Usuario listaUsuario(String us);

	Direccion actualizarCiudad(Usuario ci);

	String buscarIdInterno(String idInterno);

}
