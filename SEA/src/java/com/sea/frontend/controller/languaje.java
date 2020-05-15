/*
 * The MIT License
 *
 * Copyright 2017 homero.
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
package com.sea.frontend.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class languaje implements Serializable{
        
        private static final long serialVersionUID = 1L;
        
        private String localeCode;
        
        private static Map<String,Object> countries;// creamos un map donde esperamos una cadena y un objeto
        static{
                countries = new LinkedHashMap<String,Object>();
                Locale espanol=new Locale("ES");
                countries.put("Español", espanol);
                countries.put("English", Locale.ENGLISH); // donde English es el valor que aparecera en el selectOnemenu y el objeto en que se convertira o traducira.
                
                
        }
        public Map<String, Object> getCountriesInMap() {
                return countries;
        }
        
        public String getLocaleCode() {
                return localeCode;
        }
        public void setLocaleCode(String localeCode) {
                this.localeCode = localeCode;
        }
        public void countryLocaleCodeChanged(ValueChangeEvent e){
                
                String newLocaleValue = e.getNewValue().toString();
                
        
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
        
                if(entry.getValue().toString().equals(newLocaleValue)){
                        
                        FacesContext.getCurrentInstance()
                                .getViewRoot().setLocale((Locale)entry.getValue());
                        
                }
        }
        }
}
