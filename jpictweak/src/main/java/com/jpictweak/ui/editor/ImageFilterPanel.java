package com.jpictweak.ui.editor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ImageFilterPanel extends JPanel {
	private JComboBox<String> filterCombo;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageFilterPanel() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JPanel panelFlow = new JPanel();
		panelFlow.setOpaque(false);
		panelFlow.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		filterCombo = new JComboBox<String>();
		filterCombo.addItem("Ninguno");
		filterCombo.addItem("Blanco/Negro");
		filterCombo.addItem("Sepia");
		filterCombo.addItem("Azulado");
		filterCombo.addItem("Pixelado");
		
		panelFlow.add(filterCombo);
		
		panelPrincipal.add(panelFlow, BorderLayout.CENTER);
		
		return panelPrincipal;
	}

	public JComboBox<String> getFilterCombo() {
		return filterCombo;
	}
}