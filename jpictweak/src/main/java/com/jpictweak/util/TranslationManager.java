package com.jpictweak.util;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

/**
 * Esta clase es la que se encarga de guardar en una lista los Componentes y sus llaves para luego cambiar el idioma.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class TranslationManager {
	private ArrayList<TranslatableComponent> translatableComponentList;
	
	/**
	 * Constructor de la case, crea la lista para guardar los componentes.
	 */
	public TranslationManager() {
		translatableComponentList = new ArrayList<TranslatableComponent>();
	}
	
	/**
	 * Este metodo se utiliza para agregar a la lista un nuevo TranslatableComponent.
	 * 
	 * @param component componente a registrar
	 * @param key llave del texto
	 */
	public void registerJComponent(JComponent component, String key) {
		translatableComponentList.add(new TranslatableComponent(component, key));
	}
	
	/**
	 * Este metodo se usa para actualizar el texto de todos los componentes registrado.
	 */
	public void updateIdiome() {
		if(translatableComponentList.size() > 0) {
			for(TranslatableComponent component: translatableComponentList) {
				if(component.getComponent() instanceof JLabel) {
					((JLabel) component.getComponent()).setText(Manifest.IDIOME.getValue(component.getKey()));
					if(component.getKey().contentEquals("1007")) {
						((JLabel) component.getComponent()).setText(Manifest.IDIOME.getValue(component.getKey())+Manifest.version_app);
					}
				}
				else if(component.getComponent() instanceof JButton) {
					((JButton) component.getComponent()).setText(Manifest.IDIOME.getValue(component.getKey()));
				}
				else if(component.getComponent() instanceof JCheckBox) {
					((JCheckBox) component.getComponent()).setText(Manifest.IDIOME.getValue(component.getKey()));
				}
				else  if(component.getComponent() instanceof JPanel) {
					TitledBorder title = (TitledBorder) ((JPanel) component.getComponent()).getBorder();
					title.setTitle(Manifest.IDIOME.getValue(component.getKey()));
				}
				else if(component.getComponent() instanceof JTextField) {
					((JTextField) component.getComponent()).putClientProperty("JTextField.placeholderText", Manifest.IDIOME.getValue(component.getKey()));
				}
				else if(component.getComponent() instanceof JPasswordField) {
					((JTextField) component.getComponent()).putClientProperty("JTextField.placeholderText", Manifest.IDIOME.getValue(component.getKey()));
				}
				else if(component.getComponent() instanceof JTextPane) {
					((JTextPane) component.getComponent()).setText(Manifest.IDIOME.getValue(component.getKey()));
				}
				component.getComponent().updateUI();
			}
		}
	}
}