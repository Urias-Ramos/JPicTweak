package com.jpictweak.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase que se encarga de establecer el idioma de la aplicacion.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class Idiome {
	private ResourceBundle bundle;
	
	/**
	 * Constructor de la clase por defecto aplica el idioma Español a la aplicacion.
	 */
	public Idiome() {
		changedIdiome(0);
	}
	
	/**
	 * Este metodo establece el idioma a la aplicacion dado un parametro.
	 * 
	 * @param id valor del idioma a aplicar.
	 */
	public void changedIdiome(int id) {
		switch(id) {
		case 0://si da error de idioma, hay que eliminar y volver a escribir "idiome/Spanish"
			bundle = ResourceBundle.getBundle("idiome/Spanish", Locale.getDefault());
			break;
		case 1:
			bundle = ResourceBundle.getBundle("idiome/English", Locale.getDefault());
			break;
		case 2:
			bundle = ResourceBundle.getBundle("idiome/Portugues", Locale.getDefault());
			break;
		}
		
		Manifest.translationManager.updateIdiome();
	}
	
	/**
	 * Devuelve el texto en el idioma dado una llave.
	 * 
	 * @param key llave que se usa para obtener el texto asociado.
	 * @return String el texto en el idioma dado una llave
	 */
	public String getValue(String key) {
		return bundle.getString(key);
	}
}