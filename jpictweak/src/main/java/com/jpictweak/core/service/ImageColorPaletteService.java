package com.jpictweak.core.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import com.jpictweak.ui.ColorPaletteCard;
import com.jpictweak.util.Manifest;

/**
 * Esta clase contiene todas las funciones que ara la opcion de Paletta.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class ImageColorPaletteService {
	
	/**
	 * Constructor de la clase.
	 */
	public ImageColorPaletteService() {
		
	}
	
	/**
	 * Este metodo se encarga de obtener la paleta de colores y luego mostralos.
	 * 
	 * @param panelList panel que guardara los colores.
	 * @param count total de colores a mostrar.
	 * @param method metodo a utilizar para obtener la paleta.
	 */
	public void getPaletteColors(JPanel panelList, int count, int method) {
		switch(method) {
		case 0:
			Manifest.imageDebug.ColorPaletteList = Manifest.imageDebug.getDominantColorPalette(Manifest.imageDebug.image, count);
			break;
		case 1:
			Manifest.imageDebug.ColorPaletteList = Manifest.imageDebug.getPaletteQuantization(Manifest.imageDebug.image, count);
			break;
		}
		
		//elimina los colores mostrados anteriormente
		panelList.removeAll();
		
		//agrega los nuevos colores
		for (Color color : Manifest.imageDebug.ColorPaletteList) {
			panelList.add(new ColorPaletteCard(color));
        }
		//para que se actualize el panel
		panelList.revalidate();
	}
	
	/**
	 * Este metodo agrega un nuevo color a la lista.
	 * 
	 * @param panelList lista con los colores
	 * @param color nuevo color a agregar
	 */
	public void addNewColorPalette(JPanel panelList, Color color) {
		panelList.add(new ColorPaletteCard(color));
		panelList.revalidate();
	}
	
	/**
	 * Este metodo se encarga de convertir la lista de colores en un 
	 * BufferedImage para luego poder guardarlo en el dispositivo.
	 * 
	 * @param colors lista de colores
	 * @return BufferedImage devuelve una imagen con la paleta de colores.
	 */
	 public BufferedImage getImagePaletteColors(ArrayList<Color> colors) {
	        // Tamaño de los rectángulos
	        int rectWidth = 150;
	        int rectHeight = 75;

	        // Espacio entre rectángulos
	        int spacingX = 5;
	        int spacingY = 5;

	        // Cantidad de columnas y filas
	        int columns = 8;
	        int rows = (colors.size() + columns - 1) / columns;

	        // Tamaño del logo y la palabra "JPicTweak"
	        int logoWidth = 100;
	        int logoHeight = 100;

	        // Calcular el tamaño del BufferedImage
	        int imageWidth = (rectWidth + spacingX) * columns + spacingX;
	        int imageHeight = (rectHeight + spacingY) * rows + spacingY + logoHeight + spacingY;

	        // Crear el BufferedImage
	        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2d = image.createGraphics();

	        // Configurar el fondo blanco
	        g2d.setColor(Color.WHITE);
	        g2d.fillRect(0, 0, imageWidth, imageHeight);

	        // Cargar el logo desde el archivo .jar
	        try {
	            InputStream logoStream = getClass().getResourceAsStream("/icon/icon_app.png");
	            BufferedImage logoImage = ImageIO.read(logoStream);
	            int logoX = spacingX;
	            int logoY = spacingY;
	            g2d.drawImage(logoImage, logoX, logoY, logoWidth, logoHeight, null);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Dibujar la palabra "JPicTweak" en la parte superior
	        g2d.setColor(Color.BLACK);
	        Font logoFont = new Font("Arial", Font.BOLD, 42);
	        g2d.setFont(logoFont);
	        String logoText = Manifest.applicationName;
	        int logoTextX = spacingX + logoWidth + spacingX;
	        int logoTextY = spacingY + (logoHeight / 2);
	        g2d.drawString(logoText, logoTextX, logoTextY);

	        // Dibujar la línea horizontal
	        int lineY = logoHeight + spacingY;
	        g2d.setColor(Color.BLACK);
	        g2d.drawLine(logoTextX, lineY, imageWidth - spacingX, lineY);

	        // Dibujar los rectángulos con los colores y los valores RGB/Hex
	        int x = spacingX;
	        int y = lineY + spacingY;
	        for (int i = 0; i < colors.size(); i++) {
	            Color color = colors.get(i);
	            g2d.setColor(color);
	            g2d.fillRect(x, y, rectWidth, rectHeight);
	            g2d.setColor(Color.BLACK);
	            g2d.drawRect(x, y, rectWidth, rectHeight);

	            // Obtener los valores RGB
	            int red = color.getRed();
	            int green = color.getGreen();
	            int blue = color.getBlue();
	            String rgbValue = String.format("RGB: %d, %d, %d", red, green, blue);

	            // Obtener el valor hexadecimal del color
	            String hexValue = String.format("Hex: #%02X%02X%02X", red, green, blue);

	            // Dibujar los valores RGB y Hex en el centro del rectángulo
	            g2d.setColor(Manifest.imageDebug.getForegroundColor(color));
	            Font font = new Font("Arial", Font.BOLD, 12);
	            g2d.setFont(font);
	            FontMetrics fm = g2d.getFontMetrics();
	            int stringWidth = fm.stringWidth(rgbValue);
	            int stringHeight = fm.getHeight();
	            int stringX = x + (rectWidth - stringWidth) / 2;
	            int stringY = y + (rectHeight - stringHeight) / 2 + fm.getAscent();
	            g2d.drawString(rgbValue, stringX, stringY);
	            g2d.drawString(hexValue, stringX, stringY + fm.getHeight());

	            x += rectWidth + spacingX;
	            if (i % columns == columns - 1) {
	                x = spacingX;
	                y += rectHeight + spacingY;
	            }
	        }

	        // Mostrar la cantidad de colores
	        g2d.setColor(Color.BLACK);
	        String colorsCountText = Manifest.IDIOME.getValue("5001")+": " + colors.size();
	        int colorsCountX = imageWidth - spacingX - g2d.getFontMetrics().stringWidth(colorsCountText);
	        int colorsCountY = lineY - g2d.getFontMetrics().getHeight();
	        g2d.drawString(colorsCountText, colorsCountX, colorsCountY);

	        g2d.dispose();

	        return image;
	    }
}