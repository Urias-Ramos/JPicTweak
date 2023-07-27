package com.jpictweak.util;

import com.jpictweak.ui.ImageViewPanel;

/**
 * Clase con atributos staticos que se usaran para tener un mejor acceso a la aplicacion.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class Manifest {
	public static String applicationName = "JPicTweak";
	public static String version_app = "0.0.1";
	
	public enum JPicTweakView {
		DASHBOARD,
		ABOUT,
		EDITOR,
		COLORIZAR,
		PALETTE;
	}
	
	public static JPicTweakView viewPanel = JPicTweakView.DASHBOARD;
	
	public static int maxTotalColorsPalette = 64;
	public static int imageWidth = 0, imageHeight = 0;
	public static boolean imageFlippedVertically = false, imageFlippedHorizontally = false;
	
	public static TranslationManager translationManager = new TranslationManager();
	public static Idiome IDIOME = new Idiome();
	
	public static ImageDebug imageDebug = new ImageDebug();
	public static ImageViewPanel imageViewPanel = new ImageViewPanel();
}