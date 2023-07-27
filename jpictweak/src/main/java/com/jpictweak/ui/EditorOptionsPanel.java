package com.jpictweak.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import com.jpictweak.ui.editor.ImageFilterPanel;
import com.jpictweak.ui.editor.ResizeImage;
import com.jpictweak.ui.editor.RotateImage;
import com.jpictweak.util.Manifest;

/**
 * Esta clase representa un JPanel donde estara toda la UI de la opcion Editor.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class EditorOptionsPanel extends JPanel {
	private JButton[] btnOption;
	private JButton btnDownload;
	
	private ResizeImage resizeOption;
	private RotateImage rotateOption;
	private ImageFilterPanel filterOption;
	
	private JCheckBox checkTransparency;
	private JLabel lblColor;
	
	private CardLayout card;
	private JPanel panelOption;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase
	 */
	public EditorOptionsPanel() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	/**
	 * Este metodo crea un JPanel con toda la UI distribuida.
	 * 
	 * @return JPanel
	 */
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JToolBar panelListOption = new JToolBar();
		panelListOption.setFloatable(false);
		panelListOption.setOpaque(false);
		panelListOption.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		panelBotones.setLayout(new BorderLayout(5, 5));
		
		JPanel panelDesign = new JPanel();
		panelDesign.setOpaque(false);
		panelDesign.setLayout(new BorderLayout(5, 5));
		
		JPanel panelFlowColor = new JPanel();
		panelFlowColor.setOpaque(false);
		panelFlowColor.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		card = new CardLayout();
		
		panelOption = new JPanel(card);
		panelOption.setOpaque(false);
		
		btnOption = new JButton[4];
		for(int i=0; i<btnOption.length; i++) {
			btnOption[i] = new JButton();
			btnOption[i].setPreferredSize(new Dimension(24, 24));
			btnOption[i].setFocusPainted(false);
			btnOption[i].setOpaque(false);
			
			panelListOption.add(btnOption[i]);
		}
		
		int sizeIcon = 20;
		
		btnOption[0].setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getResource("/icon/icon_cursor.png"), sizeIcon, sizeIcon)));
		btnOption[1].setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getResource("/icon/icon_resize.png"), sizeIcon, sizeIcon)));
		btnOption[2].setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getResource("/icon/icon_girar.png"), sizeIcon, sizeIcon)));
		btnOption[3].setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getResource("/icon/icon_filtro.png"), sizeIcon, sizeIcon)));
		
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
		
		checkTransparency = new JCheckBox();
		checkTransparency.setSelected(true);
		checkTransparency.setFocusPainted(false);
		checkTransparency.setOpaque(false);
		checkTransparency.setText(Manifest.IDIOME.getValue("3001"));
		Manifest.translationManager.registerJComponent(checkTransparency, "3001");
		
		lblColor = new JLabel();
		lblColor.setOpaque(true);
		lblColor.setPreferredSize(new Dimension(32, 22));
		lblColor.setBackground(Color.BLACK);
		lblColor.setBorder(new LineBorder(Color.decode("#999999"), 1));
		
		panelFlowColor.add(lblColor);
		panelDesign.add(checkTransparency, BorderLayout.CENTER);
		panelDesign.add(panelFlowColor, BorderLayout.EAST);
		
		panelBotones.add(panelDesign, BorderLayout.CENTER);
		panelBotones.add(btnDownload, BorderLayout.SOUTH);
		
		resizeOption = new ResizeImage();
		rotateOption = new RotateImage();
		filterOption = new ImageFilterPanel();
		
		panelOption.add(new JPanel(), "cursor");
		panelOption.add(resizeOption, "resize");
		panelOption.add(rotateOption, "rotate");
		panelOption.add(filterOption, "filter");
		
		card.show(panelOption, "cursor");
		
		panelPrincipal.add(panelListOption, BorderLayout.NORTH);
		panelPrincipal.add(panelOption, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
		
		return panelPrincipal;
	}
	
	/**
	 * Obteniene el JButton[] asociado.
	 * 
	 * @return JButton[]
	 */
	public JButton[] getBtnOption() {
		return btnOption;
	}
	
	/**
	 * Obtiene el JCheckBox asociado.
	 * 
	 * @return JCheckBox
	 */
	public JCheckBox getCheckTransparency() {
		return checkTransparency;
	}
	
	/**
	 * Obtiene el JLabel asociado.
	 * 
	 * @return JLabel
	 */
	public JLabel getLblColor() {
		return lblColor;
	}
	
	/**
	 * Obtiene el JButtton asociado.
	 * 
	 * @return JButton.
	 */
	public JButton getBtnDownload() {
		return btnDownload;
	}
	
	/**
	 * Obtiene el CardLayout asociado.
	 * 
	 * @return CardLayout
	 */
	public CardLayout getCard() {
		return card;
	}
	
	/**
	 * Obtiene el JPanel asociado.
	 * 
	 * @return JPanel
	 */
	public JPanel getPanelOption() {
		return panelOption;
	}
	
	/**
	 * Obtiene el ResizeImage asociado.
	 * 
	 * @return ResizeImage
	 */
	public ResizeImage getResizeOption() {
		return resizeOption;
	}
	
	/**
	 * Obtiene el RotateImage asociado.
	 * 
	 * @return RotateImage
	 */
	public RotateImage getRotateOption() {
		return rotateOption;
	}
	
	/**
	 * Obtiene el ImageFilterPanel asociado.
	 * 
	 * @return ImageFilterPanel
	 */
	public ImageFilterPanel getFilterOption() {
		return filterOption;
	}
}