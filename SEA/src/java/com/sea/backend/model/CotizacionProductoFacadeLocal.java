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

import com.sea.backend.entities.CotizacionProducto;
import com.sea.frontend.controller.CotizacionProductoAuxiliar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Depurador
 */
@Local
public interface CotizacionProductoFacadeLocal {

	void create(CotizacionProducto cotizacionProducto);

	void edit(CotizacionProducto cotizacionProducto);

	void remove(CotizacionProducto cotizacionProducto);

	CotizacionProducto find(Object id);

	List<CotizacionProducto> findAll();

	List<CotizacionProducto> findRange(int[] range);

	int count();

	List<CotizacionProductoAuxiliar> datosCotizacionProducto(String numeroCotizacion) throws Exception;

	List<CotizacionProducto> productosCotizados(String numeroCotizacion) throws Exception;
}
