package com.jpictweak.ui.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jpictweak.util.Manifest;

public class ResizeImage extends JPanel {
	private JTextField txtWidth, txtHeight;
	private JCheckBox check;
	private JButton btnAceptar;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResizeImage() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelContent = new JPanel();
		panelContent.setOpaque(false);
		panelContent.setLayout(new BorderLayout(5, 5));
		
		JPanel panelCheck = new JPanel();
		panelCheck.setOpaque(false);
		panelCheck.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panelGrid = new JPanel();
		panelGrid.setOpaque(false);
		panelGrid.setLayout(new GridLayout(2, 2, 5, 5));
		
		JLabel lblWidth = new JLabel();
		lblWidth.setHorizontalAlignment(JLabel.LEFT);
		lblWidth.setFont(new Font("Arial", Font.BOLD, 12));
		lblWidth.setText(Manifest.IDIOME.getValue("3002"));
		Manifest.translationManager.registerJComponent(lblWidth, "3002");
		
		JLabel lblHeight = new JLabel();
		lblHeight.setHorizontalAlignment(JLabel.LEFT);
		lblHeight.setFont(new Font("Arial", Font.BOLD, 12));
		lblHeight.setText(Manifest.IDIOME.getValue("3003"));
		Manifest.translationManager.registerJComponent(lblHeight, "3003");
		
		txtWidth = new JTextField(10);
		txtHeight = new JTextField(10);
		
		check = new JCheckBox();
		check.setOpaque(false);
		check.setText(Manifest.IDIOME.getValue("3004"));
		Manifest.translationManager.registerJComponent(check, "3004");
		
		btnAceptar = new JButton();
		btnAceptar.setFocusPainted(false);
		btnAceptar.setText(Manifest.IDIOME.getValue("3005"));
		Manifest.translationManager.registerJComponent(btnAceptar, "3005");
		
		panelCheck.add(check);
		
		panelGrid.add(lblWidth);
		panelGrid.add(txtWidth);
		panelGrid.add(lblHeight);
		panelGrid.add(txtHeight);
		
		panelContent.add(panelGrid, BorderLayout.NORTH);
		panelContent.add(panelCheck, BorderLayout.CENTER);
		panelContent.add(btnAceptar, BorderLayout.SOUTH);
		
		panelPrincipal.add(panelContent, BorderLayout.CENTER);
		
		return panelPrincipal;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public JCheckBox getCheck() {
		return check;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}
}