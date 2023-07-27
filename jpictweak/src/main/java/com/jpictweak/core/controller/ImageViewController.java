package com.jpictweak.core.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jpictweak.ui.ImageViewPanel;
import com.jpictweak.ui.MainFrame;
import com.jpictweak.util.Manifest;

/**
 * Esta clase es la que se encarga de manejar toda la iteracion de la UI con los servicios o funciones.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class ImageViewController {
	private JFrame ventana;
	private JFileChooser fileChooser;
	private EditorController editorController;
	private ColorizadorController colorizadorController;
	private ImageColorPaletteController paletteController;

	public ImageViewController(ImageViewPanel view, ImageColorPaletteController paletteController, ColorizadorController colorizadorController,EditorController editorController, JFrame ventana) {
		this.editorController = editorController;
		this.paletteController = paletteController;
		this.colorizadorController = colorizadorController;
		this.ventana = ventana;
		
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(Manifest.applicationName);
		fileChooser.setMultiSelectionEnabled(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);
		
		DropTargetListener d = new DropTargetListener() {

			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				
			}

			@Override
			public void dragOver(DropTargetDragEvent dtde) {
				
			}

			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {
				
			}

			@Override
			public void dragExit(DropTargetEvent dte) {
				
			}

			@Override
			public void drop(DropTargetDropEvent dtde) {
				File file = null;
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				Transferable transferable = dtde.getTransferable();
				DataFlavor[] flavors = transferable.getTransferDataFlavors();
				for(DataFlavor flavor: flavors) {
					if(flavor.isFlavorJavaFileListType()) {
						try {
							@SuppressWarnings("unchecked")
							List<File> files = (List<File>) transferable.getTransferData(flavor);
							for(File fichero: files) {
								file = fichero.getAbsoluteFile();
							}
						} catch (UnsupportedFlavorException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				dtde.dropComplete(true);
				
				openImageFile(file, view);
			}
		};
		
		view.getPanelDragDrop().setDropTarget(new DropTarget(view.getImageView(), d));
		
		view.getBtnBack().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.mainCard.show(MainFrame.panelDashboard, "main");
			}
		});
		
		//Agrega el color del pixel al panelList
		view.getImageView().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(Manifest.imageDebug.image != null) {
					if(Manifest.viewPanel == Manifest.JPicTweakView.COLORIZAR) {
						colorizadorController.getView().getLblColor().setBackground(view.getRobot().getPixelColor(e.getXOnScreen(), e.getYOnScreen()));
					}
					else if(Manifest.viewPanel == Manifest.JPicTweakView.PALETTE) {
						if(Manifest.maxTotalColorsPalette >= Manifest.imageDebug.ColorPaletteList.size() + 1) {
							Manifest.imageDebug.ColorPaletteList.add(view.getRobot().getPixelColor(e.getXOnScreen(), e.getYOnScreen()));
							paletteController.getServiceAddNewColorPalette(Manifest.imageDebug.ColorPaletteList.get(Manifest.imageDebug.ColorPaletteList.size() - 1));
						}
						else {
							JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("102"), Manifest.applicationName, JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		
		view.getBtnOption().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view.getPopup().updateUI();
				SwingUtilities.updateComponentTreeUI(view.getPopup());
				view.getPopup().pack();
				view.getPopup().show(view.getBtnOption(), 0, 24);
			}
		});
		
		view.getItemPopup()[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFileChooser(view);
			}
		});
		
		view.getItemPopup()[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enableAndDisableControls(false);
				Manifest.imageWidth = 0;
				Manifest.imageHeight = 0;
				view.getCard().show(view.getPanelCard(), "dragdrop");
			}
		});
		
		view.getBtnDirectory().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openFileChooser(view);
			}
		});
	}
	
	private void openFileChooser(ImageViewPanel view) {
		fileChooser.updateUI();
		SwingUtilities.updateComponentTreeUI(fileChooser);
		fileChooser.repaint();
		int optionSelected = fileChooser.showOpenDialog(ventana);
		
		if(JFileChooser.APPROVE_OPTION == optionSelected) {
			File file = fileChooser.getSelectedFile();
			openImageFile(file, view);
		}
	}
	
	private void openImageFile(File file, ImageViewPanel view) {
		if(file.isFile()) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(file);
				if(img != null) {
					Manifest.imageDebug.file = file;
					
					Manifest.imageDebug.image = img;
					Manifest.imageDebug.imageAux = img;
					
					view.getCard().show(view.getPanelCard(), "imageview");
					Manifest.imageDebug.changeView(Manifest.imageDebug.imageAux);
					
					Manifest.imageWidth = Manifest.imageDebug.image.getWidth();
					Manifest.imageHeight = Manifest.imageDebug.image.getHeight();
					
					Manifest.imageFlippedHorizontally = false;
					Manifest.imageFlippedVertically = false;
					
					enableAndDisableControls(true);
				}
				else {
					JOptionPane.showMessageDialog(ventana, Manifest.IDIOME.getValue("103"), Manifest.applicationName, JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				enableAndDisableControls(false);
				e.printStackTrace();
			}
		}
		else {
			enableAndDisableControls(false);
		}
	}
	
	private void enableAndDisableControls(boolean enabled) {
		paletteController.getView().getPanelPaletteList().removeAll();
		paletteController.getView().getPanelPaletteList().revalidate();
		
		paletteController.getView().getBtnExtractor().setEnabled(enabled);
		paletteController.getView().getBtnDownload().setEnabled(false);
		
		colorizadorController.getView().getBtnColorizar().setEnabled(enabled);
		colorizadorController.getView().getBtnDownload().setEnabled(false);
		
		editorController.getView().getBtnDownload().setEnabled(enabled);
	}
}