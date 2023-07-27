package com.jpictweak.ui.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.jpictweak.util.Manifest;

public class RotateImage extends JPanel {
	private JTextField txtRotate;
	private JSlider slider;
	private JButton[] btnVoltear;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RotateImage() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JPanel panelTitle = new JPanel();
		panelTitle.setOpaque(false);
		panelTitle.setLayout(new BorderLayout(5, 5));
		
		JPanel panelFlowSlider = new JPanel();
		panelFlowSlider.setOpaque(false);
		panelFlowSlider.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTitle = new JLabel();
		lblTitle.setHorizontalAlignment(JLabel.LEFT);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 12));
		lblTitle.setText(Manifest.IDIOME.getValue("3006"));
		Manifest.translationManager.registerJComponent(lblTitle, "3006");
		
		txtRotate = new JTextField(3);
		txtRotate.setEditable(false);
		txtRotate.setText("0");
		
		slider = new JSlider();
		slider.setOpaque(false);
		slider.setMinimum(0);
		slider.setMaximum(360);
		slider.setValue(0);
		
		panelTitle.add(lblTitle, BorderLayout.CENTER);
		panelTitle.add(txtRotate, BorderLayout.EAST);
		
		panelFlowSlider.add(slider);
		
		panelPrincipal.add(panelTitle, BorderLayout.NORTH);
		panelPrincipal.add(panelFlowSlider, BorderLayout.CENTER);
		panelPrincipal.add(panelVoltear(), BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	private JPanel panelVoltear() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelGrid = new JPanel();
		panelGrid.setOpaque(false);
		panelGrid.setLayout(new GridLayout(4, 1, 5, 5));
		
		btnVoltear = new JButton[2];
		for(int i=0; i<btnVoltear.length; i++) {
			btnVoltear[i] = new JButton();
			btnVoltear[i].setOpaque(false);
			btnVoltear[i].setPreferredSize(new Dimension(150, 32));
			btnVoltear[i].setFocusPainted(false);
			
			panelGrid.add(btnVoltear[i]);
		}
		
		btnVoltear[0].setText(Manifest.IDIOME.getValue("3007"));
		Manifest.translationManager.registerJComponent(btnVoltear[0], "3007");
		
		btnVoltear[1].setText(Manifest.IDIOME.getValue("3008"));
		Manifest.translationManager.registerJComponent(btnVoltear[1], "3008");
		
		panelPrincipal.add(panelGrid);
		
		return panelPrincipal;
	}

	public JTextField getTxtRotate() {
		return txtRotate;
	}

	public JSlider getSlider() {
		return slider;
	}

	public JButton[] getBtnVoltear() {
		return btnVoltear;
	}
}