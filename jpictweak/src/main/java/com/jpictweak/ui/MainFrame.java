package com.jpictweak.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.jpictweak.core.controller.ColorizadorController;
import com.jpictweak.core.controller.EditorController;
import com.jpictweak.core.controller.ImageColorPaletteController;
import com.jpictweak.core.controller.ImageViewController;
import com.jpictweak.util.Manifest;

/**
 * Esta clase representa el JFrame principal del programa, donde mostrara toda la UI.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class MainFrame extends JFrame {
	public static CardLayout mainCard;
	private CardLayout optionCard;
	
	public static JPanel panelDashboard;
	private JPanel panelOption;
	
	private JComboBox<String> comboLenguaje;
	private JPopupMenu popupMenu;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constrcutor de la clase, que crean un JFrame.
	 */
	public MainFrame() {
		lookAndFeel(0);
		
		mainCard = new CardLayout();
		optionCard = new CardLayout();
		
		this.setLayout(new BorderLayout(5, 5));
		
		panelDashboard = new JPanel(mainCard);
		panelDashboard.setOpaque(false);
		
		panelOption = new JPanel(optionCard);
		panelOption.setOpaque(false);
		
		EditorOptionsPanel editor = new EditorOptionsPanel();
		EditorController editorController = new EditorController(editor, this);
		
		ColorizadorPanel colorzadorPanel = new ColorizadorPanel();
		ColorizadorController colorizadorController = new ColorizadorController(colorzadorPanel, this);
		
		ImageColorPalettePanel imageColorPalettePanel = new ImageColorPalettePanel();
		ImageColorPaletteController paletteController = new ImageColorPaletteController(imageColorPalettePanel, this);
		
		JPanel panelDashOption = new JPanel();
		panelDashOption.setOpaque(false);
		panelDashOption.setLayout(new BorderLayout(5, 5));
		panelDashOption.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED), new EmptyBorder(10, 10, 10, 10)));
		
		JPanel panelDescargar = new JPanel();
		panelDescargar.setOpaque(false);
		panelDescargar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		new ImageViewController(Manifest.imageViewPanel, paletteController, colorizadorController, editorController, this);
		
		panelOption.add(editor, "edit");
		panelOption.add(colorzadorPanel, "colorizador");
		panelOption.add(imageColorPalettePanel, "palette");
		
		optionCard.show(panelOption, "colorizador");
		
		panelDashOption.add(panelOption, BorderLayout.CENTER);
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setLeftComponent(Manifest.imageViewPanel);
		split.setRightComponent(panelDashOption);
		split.setDividerLocation(530);
		
		panelDashboard.add(createUI(), "main");
		panelDashboard.add(split, "dashboard");
		panelDashboard.add(new AboutPanel(), "about");
		
		mainCard.show(panelDashboard, "main");
		
		this.add(panelDashboard, BorderLayout.CENTER);
		this.add(panelStatusBar(), BorderLayout.SOUTH);
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
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setOpaque(false);
		panelContenedor.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		JPanel panelContainer = new JPanel();
		panelContainer.setOpaque(false);
		panelContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		DashboardCardOption card1 = new DashboardCardOption(Manifest.IDIOME.getValue("1001"), Manifest.IDIOME.getValue("1002"), Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_editor.png"), 72, 72));
		panelContainer.add(card1);
		Manifest.translationManager.registerJComponent(card1.getLblTitle(), "1001");
		Manifest.translationManager.registerJComponent(card1.getTxtDescription(), "1002");
		
		DashboardCardOption card2 = new DashboardCardOption(Manifest.IDIOME.getValue("1003"), Manifest.IDIOME.getValue("1004"), Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_colorizar.png"), 72, 72));
		panelContainer.add(card2);
		Manifest.translationManager.registerJComponent(card2.getLblTitle(), "1003");
		Manifest.translationManager.registerJComponent(card2.getTxtDescription(), "1004");
		
		DashboardCardOption card3 = new DashboardCardOption(Manifest.IDIOME.getValue("1005"), Manifest.IDIOME.getValue("1006"), Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_palette_color.png"), 72, 72));
		panelContainer.add(card3);
		Manifest.translationManager.registerJComponent(card3.getLblTitle(), "1005");
		Manifest.translationManager.registerJComponent(card3.getTxtDescription(), "1006");
		
		card1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Manifest.imageDebug.changeView(Manifest.imageDebug.image);
				optionCard.show(panelOption, "edit");
				mainCard.show(panelDashboard, "dashboard");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		card2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Manifest.viewPanel = Manifest.JPicTweakView.COLORIZAR;
				Manifest.imageDebug.changeView(Manifest.imageDebug.image);
				optionCard.show(panelOption, "colorizador");
				mainCard.show(panelDashboard, "dashboard");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		card3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Manifest.viewPanel = Manifest.JPicTweakView.PALETTE;
				Manifest.imageDebug.changeView(Manifest.imageDebug.image);
				optionCard.show(panelOption, "palette");
				mainCard.show(panelDashboard, "dashboard");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		panelContenedor.add(panelContainer, gbc);
		
		panelPrincipal.add(panelTitle(), BorderLayout.NORTH);
		panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea un JPanel con toda la UI distribuida.
	 * 
	 * @return JPanel
	 */
	private JPanel panelTitle() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JPanel panelTitle = new JPanel();
		panelTitle.setOpaque(false);
		panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JToolBar panelOptions = new JToolBar();
		panelOptions.setOpaque(false);
		panelOptions.setFloatable(false);
		panelOptions.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblTitle = new JLabel();
		lblTitle.setHorizontalAlignment(JLabel.LEFT);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 42));
		lblTitle.setText(Manifest.applicationName);
		lblTitle.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_app.png"), 72, 72)));
		
		JButton btnAbout = new JButton();
		btnAbout.setFocusPainted(false);
		btnAbout.setOpaque(false);
		btnAbout.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_help.png"), 18, 18)));
		btnAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Manifest.viewPanel = Manifest.JPicTweakView.ABOUT;
				mainCard.show(panelDashboard, "about");
			}
		});
		
		panelTitle.add(lblTitle);
		panelOptions.add(btnAbout);
		
		panelPrincipal.add(panelTitle, BorderLayout.CENTER);
		panelPrincipal.add(panelOptions, BorderLayout.EAST);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea un JPanel con toda la UI distribuida.
	 * 
	 * @return JPanel
	 */
	private JPanel panelStatusBar() {
		JPanel panelStatusBar = new JPanel();
		panelStatusBar.setOpaque(false);
		panelStatusBar.setLayout(new BorderLayout(5, 5));
		panelStatusBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblVersion = new JLabel();
		lblVersion.setText(Manifest.IDIOME.getValue("1007")+Manifest.version_app);
		lblVersion.setFont(new Font("Arial", Font.PLAIN, 12));
		Manifest.translationManager.registerJComponent(lblVersion, "1007");
		
		JLabel lblAppearance = new JLabel();
		lblAppearance.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_appearance.png"), 16, 16)));
		lblAppearance.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAppearance.setText(Manifest.IDIOME.getValue("1008"));
		Manifest.translationManager.registerJComponent(lblAppearance, "1008");
		
		lblAppearance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((e.getButton() == MouseEvent.BUTTON1)) {
					popupMenu.updateUI();
					SwingUtilities.updateComponentTreeUI(popupMenu);
					popupMenu.pack();
					popupMenu.show(e.getComponent(), e.getComponent().getX(), e.getComponent().getY() - 64);
				}
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		
		JCheckBoxMenuItem profile = new JCheckBoxMenuItem();
		profile.setSelected(true);
		profile.setText(Manifest.IDIOME.getValue("1009"));
		Manifest.translationManager.registerJComponent(profile, "1009");
		profile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lookAndFeel(0);
			}
		});
		
		JCheckBoxMenuItem salir = new JCheckBoxMenuItem();
		salir.setText(Manifest.IDIOME.getValue("1010"));
		Manifest.translationManager.registerJComponent(salir, "1010");
		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lookAndFeel(1);
			}
		});
		
		
		group.add(profile);
		group.add(salir);
		
		popupMenu = new JPopupMenu();
		popupMenu.add(profile);
		popupMenu.add(salir);
		
		JPanel panelOption = new JPanel();
		panelOption.setOpaque(false);
		panelOption.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		comboLenguaje = new JComboBox<String>();
		comboLenguaje.setBorder(null);
		comboLenguaje.setOpaque(false);
		comboLenguaje.addItem("Español");
		comboLenguaje.addItem("English");
		comboLenguaje.addItem("Portugues");
		comboLenguaje.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Manifest.IDIOME.changedIdiome(comboLenguaje.getSelectedIndex());
			}
		});
		
		panelOption.add(lblAppearance);
		panelOption.add(comboLenguaje);
		
		panelPrincipal.add(lblVersion, BorderLayout.CENTER);
		panelPrincipal.add(panelOption, BorderLayout.EAST);
		
		panelStatusBar.add(panelPrincipal, BorderLayout.CENTER);
		
		return panelStatusBar;
	}
	
	/**
	 * Metodo que se usa para establecer el look and feel de la aplicacion.
	 * 
	 * @param theme valor del lookAndFeel a establecer
	 */
	public void lookAndFeel(int theme) {
		switch(theme) {
		case 0:
			try {
				FlatLightLaf.setup();
				UIManager.setLookAndFeel(new FlatLightLaf());
				SwingUtilities.updateComponentTreeUI(this);
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			break;
		case 1:
			try {
				FlatDarkLaf.setup();
				UIManager.setLookAndFeel(new FlatDarkLaf());
				SwingUtilities.updateComponentTreeUI(this);
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			break;
		}
	}
}