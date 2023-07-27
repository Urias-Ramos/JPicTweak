package com.jpictweak.core.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jpictweak.ui.ColorizadorPanel;
import com.jpictweak.util.Manifest;

/**
 * Esta clase es la que se encarga de manejar toda la iteracion de la UI con los servicios o funciones.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class ColorizadorController {
	private Color colorSelected;
	private ColorizadorPanel view;

	public ColorizadorController(ColorizadorPanel colorizerView, JFrame ventana) {
		this.view = colorizerView;
		
		view.getSlider().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				view.getTxtForce().setText(""+view.getSlider().getValue());
			}
		});
		
		view.getLblColor().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				colorSelected = view.getLblColor().getBackground();
				colorSelected = JColorChooser.showDialog(ventana, Manifest.applicationName, colorSelected);
				if(colorSelected != null) {
					view.getLblColor().setBackground(colorSelected);
				}
			}
		});
		
		view.getBtnColorizar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Manifest.imageDebug.imageAux = Manifest.imageDebug.replacePixels(Manifest.imageDebug.image, view.getLblColor().getBackground(), view.getSlider().getValue());
				Manifest.imageDebug.changeView(Manifest.imageDebug.imageAux);
				view.getBtnDownload().setEnabled(true);
			}
		});
		
		view.getBtnDownload().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Manifest.imageDebug.downloadImage(Manifest.imageDebug.imageAux, Manifest.imageDebug.file, "Colorizar");
			}
		});
	}

	public ColorizadorPanel getView() {
		return view;
	}
}