package com.jpictweak.core.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jpictweak.ui.EditorOptionsPanel;
import com.jpictweak.util.Manifest;

/**
 * Esta clase es la que se encarga de manejar toda la iteracion de la UI con los servicios o funciones.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class EditorController {
	private Color colorSelected;
	private EditorOptionsPanel view;

	public EditorController(EditorOptionsPanel view, JFrame ventana) {
		this.view = view;
		
		view.getBtnOption()[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCard().show(view.getPanelOption(), "cursor");
			}
		});
		
		view.getBtnOption()[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if((Manifest.imageWidth > 0)&&(Manifest.imageHeight > 0)) {
					view.getResizeOption().getTxtWidth().setText(""+Manifest.imageWidth);
					view.getResizeOption().getTxtHeight().setText(""+Manifest.imageHeight);
				}
				else {
					view.getResizeOption().getTxtWidth().setText("0");
					view.getResizeOption().getTxtHeight().setText("0");
				}
				
				view.getCard().show(view.getPanelOption(), "resize");
			}
		});
		
		view.getBtnOption()[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCard().show(view.getPanelOption(), "rotate");
			}
		});
		
		view.getBtnOption()[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.getCard().show(view.getPanelOption(), "filter");
			}
		});
		
		view.getBtnDownload().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Manifest.imageDebug.downloadImage(Manifest.imageDebug.imageAux, Manifest.imageDebug.file, "Editor");
			}
		});
		
		view.getCheckTransparency().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				applyImageAdjustments();
			}
		});
		
		view.getLblColor().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				colorSelected = view.getLblColor().getBackground();
				colorSelected = JColorChooser.showDialog(ventana, Manifest.applicationName, colorSelected);
				if(colorSelected != null) {
					view.getLblColor().setBackground(colorSelected);
					applyImageAdjustments();
				}
			}
		});
		
		//Resize option
		view.getResizeOption().getBtnAceptar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if((Integer.parseInt(view.getResizeOption().getTxtWidth().getText()) > 0)&&(Integer.parseInt(view.getResizeOption().getTxtHeight().getText()) > 0)) {
						Manifest.imageWidth = Integer.parseInt(view.getResizeOption().getTxtWidth().getText());
						Manifest.imageHeight = Integer.parseInt(view.getResizeOption().getTxtHeight().getText());
					}
					else {
						JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("104"), Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
					}
					applyImageAdjustments();
				} catch(Exception ett) {
					JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("104"), Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		view.getResizeOption().getTxtWidth().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(view.getResizeOption().getCheck().isSelected()) {
					try {
						view.getResizeOption().getTxtHeight().setText(""+Manifest.imageDebug.calcularProporcionImagen(Integer.parseInt(view.getResizeOption().getTxtWidth().getText()), Manifest.imageDebug.image.getHeight(), Manifest.imageDebug.image.getWidth()));
					} catch(Exception etw) {
						
					}
				}
			}
		});
		
		view.getResizeOption().getTxtHeight().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(view.getResizeOption().getCheck().isSelected()) {
					try {
						view.getResizeOption().getTxtWidth().setText(""+Manifest.imageDebug.calcularProporcionImagen(Integer.parseInt(view.getResizeOption().getTxtHeight().getText()), Manifest.imageDebug.image.getWidth(), Manifest.imageDebug.image.getHeight()));
					} catch(Exception eth) {
						
					}
				}
			}
		});
		//Resize Option
		
		//Rotate option
		view.getRotateOption().getSlider().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				view.getRotateOption().getTxtRotate().setText(""+view.getRotateOption().getSlider().getValue());
				applyImageAdjustments();
			}
		});
		
		view.getRotateOption().getBtnVoltear()[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Manifest.imageFlippedHorizontally = !Manifest.imageFlippedHorizontally;
				applyImageAdjustments();
			}
		});
		
		view.getRotateOption().getBtnVoltear()[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Manifest.imageFlippedVertically = !Manifest.imageFlippedVertically;
				applyImageAdjustments();
			}
		});
		//Rotate option
		
		//Filtre option
		view.getFilterOption().getFilterCombo().addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				applyImageAdjustments();
			}
		});
		//Filtre option
	}
	
	private void applyImageAdjustments() {
		if(Manifest.imageDebug.image != null) {
			Manifest.imageDebug.imageAux = Manifest.imageDebug.getCloneImage(Manifest.imageDebug.image);
			
			int w = 0;
			int h = 0;
			
			try {
				w = Integer.parseInt(view.getResizeOption().getTxtWidth().getText());
				h = Integer.parseInt(view.getResizeOption().getTxtHeight().getText());
				
			} catch(Exception e1) {
				w = Manifest.imageDebug.image.getWidth();
				h = Manifest.imageDebug.image.getHeight();
			}
			
			double angle = view.getRotateOption().getSlider().getValue();
			
			if((w != Manifest.imageDebug.image.getWidth())||(h != Manifest.imageDebug.image.getHeight())) {
				Manifest.imageDebug.imageAux = Manifest.imageDebug.resizeImage(Manifest.imageDebug.imageAux, w, h);
			}
			
			if(angle != 0 && angle != 360) {
				Manifest.imageDebug.imageAux = Manifest.imageDebug.rotateImage(angle, Manifest.imageDebug.imageAux, view.getCheckTransparency().isSelected(), view.getLblColor().getBackground());
			}
			
			if(Manifest.imageFlippedHorizontally) {
				Manifest.imageDebug.imageAux = Manifest.imageDebug.voltearImagenHorizontal(Manifest.imageDebug.imageAux);
			}
			
			if(Manifest.imageFlippedVertically) {
				Manifest.imageDebug.imageAux = Manifest.imageDebug.voltearImagenVertical(Manifest.imageDebug.imageAux);
			}
			
			switch(view.getFilterOption().getFilterCombo().getSelectedIndex()) {
			case 1:
				Manifest.imageDebug.imageAux = Manifest.imageDebug.blackAndWhiteFilter(Manifest.imageDebug.imageAux);
				break;
			case 2:
				Manifest.imageDebug.imageAux = Manifest.imageDebug.sepiaFilter(Manifest.imageDebug.imageAux);
				break;
			case 3:
				Manifest.imageDebug.imageAux = Manifest.imageDebug.blueFilter(Manifest.imageDebug.imageAux);
				break;
			case 4:
				Manifest.imageDebug.imageAux = Manifest.imageDebug.pixelatedFilter(Manifest.imageDebug.imageAux, 8);
				break;
			}
			
			Manifest.imageDebug.changeView(Manifest.imageDebug.imageAux);
		}
	}

	public EditorOptionsPanel getView() {
		return view;
	}
}