import java.awt.*;
import java.awt.image.*;

/**
 * Class to represent a pixel (Picture Element).  
 * @author Cay Horstman
 * @author Barb Ericson - modified to change setRed, setGreen
 * and setBlue to reset the color value to between 0 and 255.
 */
public class Pixel
{  
   /** the picture this pixel is part of */
   private Picture picture;  
   
   /** the x location (column) that this pixel is in */
   private int x; 
   
   /** the y location (row) that this pixel is in */
   private int y; 
  
   /** 
    * Constructs a pixel at a given location in a picture.
    * @param picture the picture containing the pixel
    * @param x the x-coordinate of the pixel
    * @param y the y-coordinate of the pixel
    */
   public Pixel(Picture picture, int x, int y)
   {
      if (x < 0 || x >= picture.getImage().getWidth())
         throw new IllegalArgumentException("x value " + x + " is not in range 0.." + (picture.getImage().getWidth() - 1));
      if (y < 0 || y >= picture.getImage().getHeight())
         throw new IllegalArgumentException("y value " + y + " is not in range 0.." + (picture.getImage().getHeight() - 1));
      this.picture = picture;
      this.x = x;
      this.y = y;
   }
  
   /**
    * Gets the x location of this pixel.  
    * @return the x location of this pixel
    */
   public int getX() { return x; }
   
   /**
   * Gets the y location of this pixel.  
   * @return the y location of this pixel
   */
   public int getY() { return y; }     
   
   /**
    * Gets the red value of this pixel.
    * @return the red value (0..255)
    */
   public int getRed() 
   { 
      return picture.getImage().getColorAt(x, y).getRed();
   }
   
   /**
    * Gets the green value of this pixel.
    * @return the green value (0..255)
    */
   public int getGreen() 
   { 
      return picture.getImage().getColorAt(x, y).getGreen();
   }
   
   /**
    * Gets the blue value of this pixel.
    * @return the blue value (0..255)
    */
   public int getBlue() 
   { 
      return picture.getImage().getColorAt(x, y).getBlue();
   }
   
   /**
    * Gets the blue value of this pixel.
    * @return the blue value (0..255)
    */
   public int getAlpha() 
   { 
      return picture.getImage().getColorAt(x, y).getAlpha();
   }
   
   /**
    * Gets the Color value of this pixel.
    * @return the Color value
    */
   public Color getColor() 
   { 
      return new Color(picture.getImage().getColorAt(x, y));
   }
   
   /**
    * Sets the red value of this pixel.
    * @param value the red value (0..255)
    */
   public void setRed(double value)
   {
      if (value < 0)
        value = 0;
      if (value > 255)
        value = 255;
      java.awt.Color color = picture.getImage().getColorAt(x, y);
      picture.getImage().setColorAt(x, y, new Color((int) value, color.getGreen(), color.getBlue(), color.getAlpha()));
      picture.repaint();
   } 
   
   /**
    * Sets the red value of this pixel.
    * @param value the red value (0..255)
    */
   public void setGreen(double value)
   {
      if (value < 0)
        value = 0;
      if (value > 255)
        value = 255;
      java.awt.Color color = picture.getImage().getColorAt(x, y);
      picture.getImage().setColorAt(x, y, new Color(color.getRed(), (int) value, color.getBlue(), color.getAlpha()));
      picture.repaint();
   } 
   
   /**
    * Sets the red value of this pixel.
    * @param value the red value (0..255)
    */
   public void setBlue(double value)
   {
      if (value < 0)
        value = 0;
      if (value > 255)
        value = 255;
      java.awt.Color color = picture.getImage().getColorAt(x, y);
      picture.getImage().setColorAt(x, y, new Color(color.getRed(), color.getGreen(), (int) value, color.getAlpha()));
      picture.repaint();
   } 

   /**
    * Sets the red value of this pixel.
    * @param value the red value (0..255)
    */
   public void setAlpha(double value)
   {
      if (value < 0)
        value = 0;
      if (value > 255)
        value = 255;
      java.awt.Color color = picture.getImage().getColorAt(x, y);
      picture.getImage().setColorAt(x, y, new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) value));
      picture.repaint();
   } 
   
   /**
    * Sets the Color value of this pixel.
    * @param value the Color value
    */
   public void setColor(java.awt.Color value) 
   {
      picture.getImage().setColorAt(x, y, value);
      picture.repaint();
   }
   
   /**
    * Gets the distance between this pixel's color and another color.
    * @param other the other color
    * @return the distance between this pixel's color and the other color
    */
   public double colorDistance(java.awt.Color other)
   {
      int dr = getRed() - other.getRed();
      int dg = getGreen() - other.getGreen();
      int db = getBlue() - other.getBlue();
      return Math.sqrt(dr * dr + dg * dg + db * db);
   }
   
   /**
    * Gets the average of the color values of this pixel.
    * @return the average of the red, green, and blue values
    */
   public double getAverage()
   {
      return (getRed() + getGreen() + getBlue()) / 3.0;
   }
   
   public String toString()
   {
      return "Pixel[x=" + x + ",y=" + y 
         + ",red=" + getRed() 
         + ",green=" + getGreen() 
         + ",blue=" + getBlue() + "]" ;
   }
     
}
