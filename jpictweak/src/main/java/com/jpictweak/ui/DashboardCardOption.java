package com.jpictweak.ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/**
 * Esta clase representa un JPanel que crea la UI de las cartillas del menu principal.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class DashboardCardOption extends JPanel {
	private JLabel lblTitle;
	private JTextPane txtDescription;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase, crea un Objeto JPanel con los datos suministrados.
	 * 
	 * @param title titulo que contendra la cartilla
	 * @param description descripcion que contendra la cartilla.
	 * @param image imagen que contendra la cartilla.
	 */
	public DashboardCardOption(String title, String description, Image image) {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		panelPrincipal.setPreferredSize(new Dimension(200, 250));
		panelPrincipal.setBorder(new BevelBorder(BevelBorder.RAISED));
		panelPrincipal.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		JPanel panelContenido = new JPanel();
		panelContenido.setOpaque(false);
		panelContenido.setLayout(new BorderLayout(5, 5));
		panelContenido.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		lblTitle = new JLabel();
		lblTitle.setText(title);
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblIcon = new JLabel();
		lblIcon.setHorizontalAlignment(JLabel.CENTER);
		lblIcon.setVerticalAlignment(JLabel.CENTER);
		lblIcon.setIcon(new ImageIcon(image));
		
		txtDescription = new JTextPane();
		txtDescription.setContentType("text/html");
		txtDescription.setOpaque(false);
		txtDescription.setEditable(false);
		txtDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		txtDescription.setText("<HTML><p style=\"text-align: justify;\">"+description+"</p></HTML>");
		txtDescription.setPreferredSize(new Dimension(240, 100));
		
		panelContenido.add(lblTitle, BorderLayout.NORTH);
		panelContenido.add(lblIcon, BorderLayout.CENTER);
		panelContenido.add(txtDescription, BorderLayout.SOUTH);
		
		panelPrincipal.add(panelContenido, BorderLayout.CENTER);
		
		this.add(panelPrincipal, BorderLayout.CENTER);
	}
	
	/**
	 * Obteniene el JLabel asociado.
	 * 
	 * @return JLabel
	 */
	public JLabel getLblTitle() {
		return lblTitle;
	}
	
	/**
	 * Obtiene el JTextPane asociado.
	 * 
	 * @return JTextPane
	 */
	public JTextPane getTxtDescription() {
		return txtDescription;
	}
}