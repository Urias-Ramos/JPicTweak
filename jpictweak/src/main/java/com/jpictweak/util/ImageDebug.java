package com.jpictweak.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Esta clase contiene todos los metodos que seran usados por el programa para la edicion de la imagen.
 * 
 * @author Urias Ramos
 * @version 0.0.1
 * @since 2023-07-22
 * 
 */
public class ImageDebug {
	public File file;
	public BufferedImage image;
	public BufferedImage imageAux;
	
	public ArrayList<Color> ColorPaletteList;
	
	/**
	 * Constructor de la clase.
	 */
	public ImageDebug() {
		ColorPaletteList = new ArrayList<Color>();
	}
	
	/**
	 * Este metodo se encarga de actualizar la vista de la imagen por otra.
	 * 
	 * @param imageOriginal nueva imagen a mostrar
	 */
	public void changeView(BufferedImage imageOriginal) {
		if(imageOriginal != null) {
			Manifest.imageViewPanel.getImageView().setImage(getCloneImage(imageOriginal));
			Manifest.imageViewPanel.getImageView().repaint();
		}
	}
	
	/**
	 * Este metodo clona el BufferedImage dado, para obtener un nuevo BufferedImage independiente.
	 * 
	 * @param imageOriginal BufferedImage a clonar
	 * @return BufferedImage
	 */
	public BufferedImage getCloneImage(BufferedImage imageOriginal) {
		BufferedImage sub = imageOriginal.getSubimage(0, 0, imageOriginal.getWidth(), imageOriginal.getHeight());
		return new BufferedImage(sub.getColorModel(), sub.copyData(null), sub.getColorModel().isAlphaPremultiplied(), null);
	}
	
	/**
	 * Este metodo se encarga de obtener la paleta de colores, usando el enfoque de Cuantizacion.
	 * 
	 * @param imageOriginal imagen a ser evaluada
	 * @param totalColors total de colores que debe encontrar
	 * @return lista con los colores
	 */
	public ArrayList<Color> getPaletteQuantization(BufferedImage imageOriginal, int totalColors) {
		ArrayList<Color> colorList = new ArrayList<Color>();
		int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        
        //distancia para conciderar si son colores distintos
        double minDistance = 50.0;
        
        for(int y=0; y<height; y++) {
        	for(int x=0; x<width; x++) {
        		Color pixelColor = new Color(imageOriginal.getRGB(x, y));
        		
        		int n = imageOriginal.getRGB(x, y);
                int alpha = (n >> 24) & 0xFF;
                
                //se ignoran los pixeles transparentes
                if(alpha != 0) {
                	boolean isUniqueColor = true;
                	for(Color uniqueColor: colorList) {
                		double distance = getColorDistance(pixelColor, uniqueColor);
                		if(distance < minDistance) {
                			isUniqueColor = false;
                            break;
                		}
                	}
                	
                	if(colorList.size() < totalColors) {
                		if(isUniqueColor) {
                			colorList.add(pixelColor);
                		}
                	}
                	else {
                		break;
                	}
                }
            }
        	
        	if(colorList.size() >= totalColors) {
        		break;
        	}
        }
		
		return colorList;
	}
	
	/**
	 * Este metodo se encarga de obtener la paleta de colores, usando el enfoque de colores predominantes. 
	 * 
	 * @param imageOriginal imagen a ser evaluada
	 * @param totalColors total de colores que debe encontrar
	 * @return lista con los colores
	 */
	public ArrayList<Color> getDominantColorPalette(BufferedImage imageOriginal, int totalColors) {
		HashMap<Color, Integer> colorCountMap = new HashMap<>();
		
		int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        
        for(int y=0; y<height; y++) {
        	for(int x=0; x<width; x++) {
        		Color colorValue = new Color(imageOriginal.getRGB(x, y));
                int n = imageOriginal.getRGB(x, y);
                int alpha = (n >> 24) & 0xFF;
                
                //se ignoran los pixeles transparentes
                if(alpha != 0) {
                	colorCountMap.put(colorValue, colorCountMap.getOrDefault(colorValue, 0) + 1);
                }
        	}
        }
        
        //se ordenan de mayor a menor la lista de colores dado la cantidad de apariciones
        ArrayList<Map.Entry<Color, Integer>> sortedColorList = new ArrayList<>(colorCountMap.entrySet());
        sortedColorList.sort(Map.Entry.<Color, Integer>comparingByValue().reversed());
        
        int x = 1;
        ArrayList<Color> colorList = new ArrayList<>();
        for(Map.Entry<Color, Integer> entry : sortedColorList) {
        	Color color = entry.getKey();
            
        	if(!colorList.contains(color)) {
        		colorList.add(color);
        	}
        	
        	if(x == totalColors) {
        		break;
        	}
        	x++;
        }
        
        return colorList;
	}
	
	/**
	 * Este metodo devuelve el valor HTML de un color
	 * 
	 * @param color Color al cual se le obtendra el valor HTML
	 * @return cadena de texto con el valor HTML
	 */
	public String convertColorToHTML(Color color) {
		String red = Integer.toHexString(color.getRed());
        String green = Integer.toHexString(color.getGreen());
        String blue = Integer.toHexString(color.getBlue());
        
        //se verifica que los valores sean de 2 digitos
        if (red.length() == 1) {
            red = "0" + red;
        }
        if (green.length() == 1) {
            green = "0" + green;
        }
        if (blue.length() == 1) {
            blue = "0" + blue;
        }
        
        return "#" + red + green + blue;
	}
	
	/**
	 * Este metodo se utiliza para obtener el Color que debe tener un texto dado un Color.
	 * 
	 * @param color color a verificar
	 * @return Color que debe tener el texto
	 */
	public Color getForegroundColor(Color color) {
		if(isLightColor(color)) {
			return Color.BLACK;
		}
		else if(isDarkColor(color)) {
			return Color.WHITE;
		}
		
		//por si existe un error se aplica un color intermedio
		return Color.decode("#999999");
	}
	
	/**
	 * Este metodo se utiliza para saber si un color es claro.
	 * 
	 * @param color color a verificar
	 * @return true si es un color claro, false de lo contrario
	 */
	private boolean isLightColor(Color color) {
		//se Calcula el brillo relativo del color (valor entre 0 y 255)
        double brightness = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        
        // Verificar si el color es muy claro (brillo >= 0.5)
        return brightness >= 0.5;
	}
	
	/**
	 * Este metodo se utiliza para saber si un color es oscuro.
	 * 
	 * @param color color a verificar
	 * @return true si es un color oscuro, false de lo contrario
	 */
	private boolean isDarkColor(Color color) {
		//se calcula el brillo relativo del color (valor entre 0 y 255)
        double brightness = (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;
        
        //Verificar si el color es muy oscuro (brillo < 0.5)
        return brightness < 0.5;
	}

	/**
	 * Este metodo se utiliza para calcular la distancia entre dos colores.
	 * 
	 * @param color1 Color 1 a verificar.
	 * @param color2 Color 2 a verificar.
	 * @return devuelve la distancia que existen entre ambos
	 */
	private double getColorDistance(Color color1, Color color2) {
		int redDiff = color1.getRed() - color2.getRed();
        int greenDiff = color1.getGreen() - color2.getGreen();
        int blueDiff = color1.getBlue() - color2.getBlue();
        
        return Math.sqrt(redDiff * redDiff + greenDiff * greenDiff + blueDiff * blueDiff);
	}
	
	/**
	 * Este metodo se utiliza para ajustar una imagen, dado el ancho y alto establecido.
	 * 
	 * @param url direccion de la imagen
	 * @param width nuevo ancho a establecer
	 * @param height nuevo alto a establecer
	 * @return devuelve un Image con la imagen ajustada.
	 */
	public Image getAdjustedImage(URL url, int width, int height) {
		ImageIcon imgIcon = new ImageIcon(url);
		
		if(width == -1 && height == -1) {
			width = imgIcon.getIconWidth();
			height = imgIcon.getIconHeight();
		}
		
		Image imagen = imgIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return imagen;
	}
	
	/**
	 * Este metodo se encarga de cambiar el color de los pixeles por un color especifico.
	 * 
	 * @param originalImage imagen a reemplazar el color de los pixeles
	 * @param replacementColor nuevo color que tendran los pixeles
	 * @param intensity opacidad que tendran los pixeles 0-100
	 * @return devuelve un BufferedImage con los cambios de piexeles
	 */
	public BufferedImage replacePixels(BufferedImage originalImage, Color replacementColor, int intensity) {
		int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        intensity = Math.max(0, Math.min(100, intensity));
        int alpha = (intensity * 255) / 100;
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
            	int pixel = originalImage.getRGB(x, y);
            	int originalAlpha = (pixel >> 24) & 0xFF;
            	
            	if(originalAlpha != 0) {
            		int newAlpha = (originalAlpha * alpha) / 255;
            		int newPixel = (newAlpha << 24) | (replacementColor.getRGB() & 0x00FFFFFF);
            		newImage.setRGB(x, y, newPixel);
            	}
            	else {
            		newImage.setRGB(x, y, pixel);
            	}
            }
        }
        
        return newImage;
	}
	
	/**
	 * Este metodo se encarga de cambiar el tamaño de la imagen a los nuevos valores dados.
	 * 
	 * @param originalImage imagen a cambiar de dimensiones
	 * @param width nuevo ancho que se le aplicara
	 * @param height nuevo alto que se le aplicara
	 * @return devuelve un BufferedImage con la imagen redimensionada
	 */
	public BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, originalImage.getType());
		Graphics2D g2d = image.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		g2d.drawImage(originalImage, 0, 0, width, height, null);
		g2d.dispose();
		
		return image;
	}
	
	/**
	 * Este metodo se encarga de rotar la imagen.
	 * 
	 * @param angle angulo que se desea aplicar
	 * @param imageOriginal Imagen que le debera aplicar los cambios
	 * @param enableTransparency si es true la imagen tendra transparencia, de lo contario no
	 * @param backgroundColor si la imagen no tiene transparencia se le aplicara un color
	 * @return devuelve el BufferedImage con los cambios aplicados
	 */
	public BufferedImage rotateImage(double angle, BufferedImage imageOriginal, boolean enableTransparency, Color backgroundColor) {
		double rotationAngle = Math.toRadians(angle);

  	    double sin = Math.abs(Math.sin(rotationAngle));
  	    double cos = Math.abs(Math.cos(rotationAngle));

  	    int w = (int) Math.floor((imageOriginal.getWidth() * cos) + (imageOriginal.getHeight() * sin));
  	    int h = (int) Math.floor((imageOriginal.getWidth() * sin) + (imageOriginal.getHeight() * cos));

  	    BufferedImage imageCopy;
  	    if (enableTransparency) {
  	        imageCopy = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
  	    } else {
  	        imageCopy = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
  	    }

  	    Graphics2D g2d = imageCopy.createGraphics();

  	    if (!enableTransparency) {
  	        g2d.setColor(backgroundColor);
  	        g2d.fillRect(0, 0, w, h);
  	    }

  	    int x = (w - imageOriginal.getWidth()) / 2;
  	    int y = (h - imageOriginal.getHeight()) / 2;

  	    AffineTransform transform = new AffineTransform();
  	    transform.rotate(rotationAngle, w / 2, h / 2);
  	    transform.translate(x, y);
  	    g2d.setTransform(transform);

  	    g2d.drawImage(imageOriginal, 0, 0, null);
  	    g2d.dispose();

  	    return imageCopy;
	}
	
	/**
	 * Este metodo se encarga de voltear la imagen de forma horizontal.
	 * 
	 * @param imageOriginal imagen a voltear
	 * @return devuelve BufferedImage con los cambios dados
	 */
	public BufferedImage voltearImagenHorizontal(BufferedImage imageOriginal) {
		int width = imageOriginal.getWidth();
  		int height = imageOriginal.getHeight();
  		
  		BufferedImage voltear = new BufferedImage(width, height, imageOriginal.getType());
  		
  		for(int y=0; y<height; y++) {
  			for(int x=0; x<width; x++) {
  				voltear.setRGB(width - 1 - x, y, imageOriginal.getRGB(x, y));
  			}
  		}
  		
  		return voltear;
	}
	
	/**
	 * Este metodo se encarga de voltear la imagen de forma vertical.
	 * 
	 * @param imageOriginal imagen a voltear
	 * @return devuelve BufferedImage con los cambios dados
	 */
	public BufferedImage voltearImagenVertical(BufferedImage imageOriginal) {
		int width = imageOriginal.getWidth();
  		int height = imageOriginal.getHeight();
  		
  		BufferedImage voltear = new BufferedImage(width, height, imageOriginal.getType());
  		
  		for(int y=0; y<height; y++) {
  			for(int x=0; x<width; x++) {
  				voltear.setRGB(x, height - 1 - y, imageOriginal.getRGB(x, y));
  			}
  		}
  		
  		return voltear;
	}
	
	/**
	 * Este metodo aplica a la imagen el filtro blanco y negro.
	 * 
	 * @param imageOriginal imagen a aplicar el filtro
	 * @return devuelve la imagen modificada
	 */
	public BufferedImage blackAndWhiteFilter(BufferedImage imageOriginal) {
		BufferedImage image = getCloneImage(imageOriginal);
		
        for(int y=0; y<image.getHeight(); y++) {
            for (int x=0; x<image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF; // Extraer el valor alfa (transparencia) del píxel

                //se comprueba si el píxel es transparente
                if(alpha == 0) {
                    continue;
                }

                Color color = new Color(rgb);
                int valorGris = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                Color gris = new Color(valorGris, valorGris, valorGris);
                image.setRGB(x, y, gris.getRGB());
            }
        }
        
        return image;
	}
	
	/**
	 * Este metodo aplica a la imagen el filtro sepia.
	 * 
	 * @param imageOriginal imagen a aplicar el filtro
	 * @return devuelve la imagen modificada
	 */
	public BufferedImage sepiaFilter(BufferedImage imageOriginal) {
		BufferedImage image = getCloneImage(imageOriginal);

        for(int y=0; y<image.getHeight(); y++) {
            for (int x=0; x<image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;

                //se comprueba si el píxel es transparente
                if(alpha == 0) {
                    continue;
                }

                Color color = new Color(rgb);

                int r = (int) (color.getRed() * 0.393 + color.getGreen() * 0.769 + color.getBlue() * 0.189);
                int g = (int) (color.getRed() * 0.349 + color.getGreen() * 0.686 + color.getBlue() * 0.168);
                int b = (int) (color.getRed() * 0.272 + color.getGreen() * 0.534 + color.getBlue() * 0.131);

                r = Math.min(r, 255);
                g = Math.min(g, 255);
                b = Math.min(b, 255);

                Color sepia = new Color(r, g, b);
                image.setRGB(x, y, sepia.getRGB());
            }
        }
        
        return image;
	}
	
	/**
	 * Este metodo aplica a la imagen el filtro azul.
	 * 
	 * @param imageOriginal imagen a aplicar el filtro
	 * @return devuelve la imagen modificada
	 */
	public BufferedImage blueFilter(BufferedImage imageOriginal) {
		BufferedImage image = getCloneImage(imageOriginal);
		
        for(int y=0; y<image.getHeight(); y++) {
            for (int x=0; x<image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);

                //se Verifica si el pixel es transparente
                if((rgb >> 24) == 0x00) {
                    image.setRGB(x, y, 0x00);
                }
                else {
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    //Aplicar el filtro azulado
                    int newRed = (int) (0.6 * red);
                    int newGreen = (int) (0.8 * green);
                    int newBlue = (int) (1.0 * blue);

                    //Asegurar que los valores estén dentro del rango (0 - 255)
                    newRed = Math.min(Math.max(newRed, 0), 255);
                    newGreen = Math.min(Math.max(newGreen, 0), 255);
                    newBlue = Math.min(Math.max(newBlue, 0), 255);
                    
                    //Crear el nuevo color filtrado
                    Color newColor = new Color(newRed, newGreen, newBlue);
                    
                    //Establecer el nuevo color en el pixel del BufferedImage filtrado
                    image.setRGB(x, y, newColor.getRGB());
                }
            }
        }

        return image;
	}
	
	/**
	 * Este metodo aplica a la imagen el filtro pixelado.
	 * 
	 * @param imageOriginal imagen a aplicar el filtro
	 * @param pixelSize tamaño que tendran los pixeles
	 * @return devuelve la imagen modificada
	 */
	public BufferedImage pixelatedFilter(BufferedImage imageOriginal, int pixelSize) {
		BufferedImage image = getCloneImage(imageOriginal);
		
        for(int y=0; y<image.getHeight(); y+=pixelSize) {
            for(int x=0; x<image.getWidth(); x+=pixelSize) {
                int pixel = image.getRGB(x, y);
                
                for(int i=y; i<y+pixelSize && i<image.getHeight(); i++) {
                    for(int j=x; j<x+pixelSize && j<image.getWidth(); j++) {
                        int alpha = (pixel >> 24) & 0xFF;

                        //se verifica si el pixel es transparente
                        if (alpha == 0) {
                            continue;
                        }
                        
                        image.setRGB(j, i, pixel);
                    }
                }
            }
        }
        
        return image;
	}
	
	/**
	 * Este metodo se encarga de calcular las porpociones que debe tener una imagen si se modifica el ancho o alto.
	 * 
	 * @param newSize nuevo tamaño a aplicar
	 * @param oppositeDimension lado opuesto de la imagen
	 * @param originalSize tamaño actual del lado a modificar
	 * @return devuelve la proporcion
	 */
	public int calcularProporcionImagen(int newSize, int oppositeDimension, int originalSize) {
		return ((newSize * oppositeDimension) / originalSize);
	}
	
	/**
	 * Este metodo se encarga de guardar la imagen.
	 * 
	 * @param image imagen a guardar
	 * @param file ruta de guardado
	 * @param abrev abreviatura que se le agrega al nombre de la imagen, para no modificar la imagen original.
	 */
	public void downloadImage(BufferedImage imageOriginal, File file, String abrev) {
		try {
    		String name = "";
    		int lastIndex = file.getName().lastIndexOf(".");
    		if(lastIndex == -1) {
    			name = file.getName()+"("+abrev+")";
    		}
    		else {
    			name = file.getName().substring(0, lastIndex) + "("+abrev+")" + file.getName().substring(lastIndex);
    		}
    		
			ImageIO.write(imageOriginal, "png", new File(file.getParent()+"\\"+name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}