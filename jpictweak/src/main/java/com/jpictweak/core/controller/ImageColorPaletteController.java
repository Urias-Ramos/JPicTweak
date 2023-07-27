package com.jpictweak.core.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jpictweak.core.service.ImageColorPaletteService;
import com.jpictweak.ui.ImageColorPalettePanel;
import com.jpictweak.util.Manifest;

/**
 * Esta clase es la que se encarga de manejar toda la iteracion de la UI con los servicios o funciones.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class ImageColorPaletteController {
	
	private ImageColorPaletteService service;
	private ImageColorPalettePanel view;
	
	private BufferedImage bufferdImagePalette;

	public ImageColorPaletteController(ImageColorPalettePanel view, JFrame ventana) {
		this.view = view;
		service = new ImageColorPaletteService();
		
		view.getBtnExtractor().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Manifest.imageDebug.image != null) {
					Manifest.imageDebug.ColorPaletteList.clear();
					service.getPaletteColors(view.getPanelPaletteList(), Integer.parseInt(view.getTxtPaletteCount().getValue().toString()), view.getCombo().getSelectedIndex());
					view.getBtnDownload().setEnabled(true);
				}
				else {
					view.getBtnDownload().setEnabled(false);
					JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("101"), Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		});
		
		view.getBtnDownload().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bufferdImagePalette = service.getImagePaletteColors(Manifest.imageDebug.ColorPaletteList);
				Manifest.imageDebug.downloadImage(bufferdImagePalette, Manifest.imageDebug.file, "palette");
			}
		});
	}
	
	public void getServiceAddNewColorPalette(Color color) {
		service.addNewColorPalette(view.getPanelPaletteList(), color);
		view.getBtnDownload().setEnabled(true);
	}

	public ImageColorPalettePanel getView() {
		return view;
	}
}