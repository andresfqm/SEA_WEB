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

import com.sea.backend.entities.Pagina;
import com.sea.backend.entities.ViewPaginasUsuario;
import com.sea.backend.entities.ViewSubmenusUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Depurador
 */
@Local
public interface PaginaFacadeLocal {

	void create(Pagina pagina);

	void edit(Pagina pagina);

	void remove(Pagina pagina);

	Pagina find(Object id);

	List<Pagina> findAll();

	List<ViewPaginasUsuario> obtenerPagianasPermitidas(int idUsuario);

	List<Pagina> findRange(int[] range);

	int count();
	
	List<ViewSubmenusUsuario> obtenerSubMenus(int idUsuario, String seccion) throws Exception;

}
