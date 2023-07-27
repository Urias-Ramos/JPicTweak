package com.jpictweak.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.jpictweak.util.Manifest;

/**
 * Esta clase representa un JPanel donde esta toda la UI de la opcion de colorizac.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class ColorizadorPanel extends JPanel {
	private JTextField txtForce;
	private JSlider slider;
	private JLabel lblColor;
	private JButton btnColorizar, btnDownload;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase.
	 */
	public ColorizadorPanel() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	/**
	 * Este metodo crea un JPanel donde se crea y distribuye toda la UI.
	 * 
	 * @return JPanel
	 */
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 15));
		
		JPanel panelContenido = new JPanel();
		panelContenido.setOpaque(false);
		panelContenido.setLayout(new BorderLayout(5, 5));
		
		JPanel panelTitle = new JPanel();
		panelTitle.setOpaque(false);
		panelTitle.setLayout(new BorderLayout(5, 5));
		
		JPanel panelColor = new JPanel();
		panelColor.setOpaque(false);
		panelColor.setLayout(new BorderLayout(5, 5));
		
		JPanel panelButton = new JPanel();
		panelButton.setOpaque(false);
		panelButton.setLayout(new BorderLayout(5, 5));
		
		JLabel lblTitle = new JLabel();
		lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitle.setHorizontalTextPosition(JLabel.LEFT);
		lblTitle.setText(Manifest.IDIOME.getValue("4001"));
		Manifest.translationManager.registerJComponent(lblTitle, "4001");
		
		txtForce = new JTextField(3);
		txtForce.setText("100");
		txtForce.setOpaque(false);
		txtForce.setBorder(null);
		txtForce.setFont(new Font("Arial", Font.BOLD, 12));
		txtForce.setEditable(false);
		
		slider = new JSlider();
		slider.setOpaque(false);
		slider.setMinimum(0);
		slider.setMaximum(100);
		slider.setValue(100);
		
		JLabel lblTitleColor = new JLabel();
		lblTitleColor.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitleColor.setHorizontalTextPosition(JLabel.LEFT);
		lblTitleColor.setText(Manifest.IDIOME.getValue("4002"));
		Manifest.translationManager.registerJComponent(lblTitleColor, "4002");
		
		lblColor = new JLabel();
		lblColor.setOpaque(true);
		lblColor.setPreferredSize(new Dimension(32, 22));
		lblColor.setBackground(Color.BLACK);
		lblColor.setBorder(new LineBorder(Color.decode("#999999"), 1));
		
		btnColorizar = new JButton();
		btnColorizar.setEnabled(false);
		btnColorizar.setHorizontalAlignment(JLabel.CENTER);
		btnColorizar.setBackground(Color.decode("#e85d47"));
		btnColorizar.setForeground(Color.WHITE);
		btnColorizar.setFont(new Font("Arial", Font.BOLD, 16));
		btnColorizar.setPreferredSize(new Dimension(100, 48));
		btnColorizar.putClientProperty("JButton.buttonType", "roundRect");
		btnColorizar.setFocusPainted(false);
		btnColorizar.setText(Manifest.IDIOME.getValue("4003"));
		Manifest.translationManager.registerJComponent(btnColorizar, "4003");
		
		btnDownload = new JButton();
		btnDownload.setBackground(Color.decode("#5cb18e"));
		btnDownload.setForeground(Color.WHITE);
		btnDownload.setFont(new Font("Arial", Font.BOLD, 16));
		btnDownload.setPreferredSize(new Dimension(100, 48));
		btnDownload.putClientProperty("JButton.buttonType", "roundRect");
		btnDownload.setFocusPainted(false);
		btnDownload.setEnabled(false);
		btnDownload.setText(Manifest.IDIOME.getValue("1"));
		Manifest.translationManager.registerJComponent(btnDownload, "1");
		
		panelButton.add(btnColorizar, BorderLayout.CENTER);
		panelButton.add(btnDownload, BorderLayout.SOUTH);
		
		panelTitle.add(lblTitle, BorderLayout.CENTER);
		panelTitle.add(txtForce, BorderLayout.EAST);
		
		JPanel panelFlowColorTitle = new JPanel();
		panelFlowColorTitle.setOpaque(false);
		panelFlowColorTitle.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panelFlowColor = new JPanel();
		panelFlowColor.setOpaque(false);
		panelFlowColor.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelFlowColorTitle.add(lblTitleColor);
		panelFlowColor.add(lblColor);
		
		panelColor.add(panelFlowColorTitle, BorderLayout.CENTER);
		panelColor.add(panelFlowColor, BorderLayout.EAST);
		
		panelContenido.add(panelTitle, BorderLayout.NORTH);
		panelContenido.add(slider, BorderLayout.CENTER);
		panelContenido.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
		
		panelPrincipal.add(panelContenido, BorderLayout.NORTH);
		panelPrincipal.add(panelColor, BorderLayout.CENTER);
		panelPrincipal.add(panelButton, BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	/**
	 * Obtiene el JTextField asociado.
	 * 
	 * @return JTextField
	 */
	public JTextField getTxtForce() {
		return txtForce;
	}
	
	/**
	 * Obtiene el JSlider asociado.
	 * 
	 * @return JSlider
	 */
	public JSlider getSlider() {
		return slider;
	}
	
	/**
	 * Obtiene el JButton asociado.
	 * 
	 * @return JButton
	 */
	public JButton getBtnDownload() {
		return btnDownload;
	}
	
	/**
	 * obtiene el JLabel asociado.
	 * 
	 * @return JLabel
	 */
	public JLabel getLblColor() {
		return lblColor;
	}
	
	/**
	 * obtiene el JButton asociado.
	 * 
	 * @return JButton.
	 */
	public JButton getBtnColorizar() {
		return btnColorizar;
	}
}