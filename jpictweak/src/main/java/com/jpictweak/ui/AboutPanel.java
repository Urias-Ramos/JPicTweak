package com.jpictweak.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.jpictweak.util.Manifest;

/**
 * Esta clase representa un JPanel donde esta toda la UI de Acerca de.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class AboutPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de la clase AboutPanel.
	 * 
	 * Este constructor se utiliza para crear un objeto JPanel que contiene todo la UI.
	 */
	public AboutPanel() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout(5, 5));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		this.add(createUI(), BorderLayout.CENTER);
	}
	
	/**
	 * Este metodo crea un JPanel y distribuye la UI.
	 * 
	 * @return devuelve un JPanel con la UI agregada a el.
	 */
	private JPanel createUI() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		panelPrincipal.add(titlePanel(), BorderLayout.NORTH);
		panelPrincipal.add(panelCenter(), BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea un JPanel con JTabbedPane agregado.
	 * 
	 * @return JPanel
	 */
	private JPanel panelCenter() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new BorderLayout(5, 5));
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setOpaque(false);
		panelContenedor.setLayout(new BorderLayout(5, 5));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		tabbedPane.add(panelApp());
		tabbedPane.setIconAt(0, new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_app.png"), 18, 18)));
		tabbedPane.add(panelContact());
		tabbedPane.setIconAt(1, new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_profile.png"), 18, 18)));
		
		
		panelContenedor.add(tabbedPane, BorderLayout.CENTER);
		
		panelPrincipal.add(panelContenedor, BorderLayout.CENTER);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea toda la seccion de la informacion de la Aplicacion.
	 * 
	 * @return JPanel
	 */
	private JPanel panelApp() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new GridBagLayout());
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setOpaque(false);
		panelContenedor.setLayout(new BorderLayout(5, 5));
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		JLabel lblApp = new JLabel(Manifest.applicationName);
		lblApp.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_app.png"), 128, 128)));
		lblApp.setHorizontalTextPosition(JLabel.CENTER);
		lblApp.setHorizontalAlignment(JLabel.CENTER);
		lblApp.setVerticalAlignment(JLabel.TOP);
		lblApp.setVerticalTextPosition(JLabel.BOTTOM);
		lblApp.setFont(new Font("Arial", Font.BOLD, 24));
		
		JLabel lblDescription = new JLabel();
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDescription.setHorizontalTextPosition(JLabel.CENTER);
		lblDescription.setText("<HTML><CENTER>Versión "+Manifest.version_app
				+ "<BR>Copyright @ 2023 Without Studios.<BR>"
				+ "All rights reserved.</CENTER></HTML>");
		
		panelContenedor.add(lblApp, BorderLayout.NORTH);
		panelContenedor.add(lblDescription, BorderLayout.CENTER);
		
		panelPrincipal.add(panelContenedor, gbc);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea toda la seccion de Acerca de contacto.
	 * 
	 * @return JPanel
	 */
	private JPanel panelContact() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		
		JPanel panelContenedor = new JPanel();
		panelContenedor.setOpaque(false);
		panelContenedor.setLayout(new BorderLayout(5, 5));
		
		JLabel lblProfile = new JLabel();
		lblProfile.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_profile.png"), 128, 128)));
		lblProfile.setHorizontalTextPosition(JLabel.CENTER);
		lblProfile.setHorizontalAlignment(JLabel.CENTER);
		lblProfile.setVerticalAlignment(JLabel.TOP);
		lblProfile.setVerticalTextPosition(JLabel.BOTTOM);
		lblProfile.setFont(new Font("Arial", Font.BOLD, 24));
		
		String tabla = "<html><table>"
	            + "<tr><td><strong>Developer:</strong></td><td>Urias Ramos</td></tr>"
	            + "<tr><td><strong>Email:</strong></td><td>uriasramos507@gmail.com</td></tr>"
	            + "<tr><td><strong>LinkedIn:</strong></td><td>urias-ramos-5a6106271</a></td></tr>"
	            + "</table></html>";
		
		JLabel lblInf = new JLabel();
		lblInf.setFont(new Font("Arial", Font.PLAIN, 14));
		lblInf.setHorizontalTextPosition(JLabel.CENTER);
		lblInf.setText(tabla);
		
		panelContenedor.add(lblProfile, BorderLayout.NORTH);
		panelContenedor.add(lblInf, BorderLayout.CENTER);
		
		panelPrincipal.add(panelContenedor, gbc);
		
		return panelPrincipal;
	}
	
	/**
	 * Este metodo crea toda la seecion de la barra de titulos.
	 * 
	 * @return JToolBar
	 */
	private JToolBar titlePanel() {
		JToolBar panelPrincipal = new JToolBar();
		panelPrincipal.setFloatable(false);
		panelPrincipal.setOpaque(false);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnBack = new JButton();
		btnBack.setOpaque(false);
		btnBack.setFocusPainted(false);
		btnBack.setIcon(new ImageIcon(Manifest.imageDebug.getAdjustedImage(getClass().getClassLoader().getResource("icon/icon_back.png"), 18, 18)));
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.mainCard.show(MainFrame.panelDashboard, "main");
			}
		});
		
		JLabel lblTitle = new JLabel();
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitle.setHorizontalTextPosition(JLabel.LEFT);
		lblTitle.setText(Manifest.IDIOME.getValue("2001"));
		Manifest.translationManager.registerJComponent(lblTitle, "2001");
		
		panelPrincipal.add(btnBack);
		panelPrincipal.add(lblTitle);
		
		return panelPrincipal;
	}
}