package com.jpictweak.test;

import javax.swing.JFrame;

import com.jpictweak.ui.MainFrame;
import com.jpictweak.util.Manifest;

/**
 * Clase principal que contiene el metodo main.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class TestJPicTweak {
	
	public static void main(String[] args) {
		JFrame ventana = new MainFrame();
		ventana.setTitle(Manifest.applicationName);
		ventana.setIconImage(Manifest.imageDebug.getAdjustedImage(ventana.getClass().getResource("/icon/icon_app.png"), 64, 64));
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(920, 575);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}