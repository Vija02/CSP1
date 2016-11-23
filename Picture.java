import greenfoot.*;         // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;          // Abstract Window Toolkit
import java.awt.image.*;    // BufferedImage
import javax.imageio.*;
import javax.swing.*;       // GUI - modal dialogs, etc.
import java.net.*;
import java.io.*;           // File input and output

/**
 * A class that represents a picture.  Pictures have pixels.  A pixel has a color.  A color object 
 * has a red, green, and blue value from 0-255 (inclusive).
 */

public class Picture extends Actor
{ 

   /** the name of the file the picture was created from */
   private String source;
   
   /** the count of how many pixels have been displayed */
   private int count;
   
   /**
    * Constructor that creates an empty picture
    */
   public Picture()
   {
   }
   
   /**
    * Constructor that creates a picture from the passed file name
    * @param fileName
    */
   public Picture(String fileName)
   {
       source = fileName;
       load(fileName);
   }
   
   /**
    * Gets a pixel for the given x and y location
    * @param x the x location of the pixel in the picture
    * @param y the y location of the pixel in the picture
    * @return a Pixel object for the given location
    */
   public Pixel getPixel(int x, int y)
   {
      return new Pixel(this, x, y);
   }

   /**
    * Gets all pixels in this picture
    * @return a one-dimensional array of Pixel objects, traversed in
    * row-major order.
    */
   public Pixel[] getPixels()
   {
      int width = getImage().getWidth();
      int height = getImage().getHeight();
      Pixel[] pixels = new Pixel[width * height];
      
      for (int y = 0; y < height; y++) 
         for (int x = 0; x < width; x++) 
            pixels[y * width + x] = new Pixel(this, x, y);
      
      return pixels;
   }
   
   /** 
    * Gets all the pixels in the picture and returns
    * them in a two dimensional array in column-major order
    * @return the two dimensional array of pixels in column-
    * major order (ie [col][row].
    */
   public Pixel[][] get2DPixels()
   {
      int width = getImage().getWidth();
      int height = getImage().getHeight();
      Pixel[][] pixels = new Pixel[width][height];
      
      for (int y = 0; y < height; y++) 
      {
         for (int x = 0; x < width; x++)
         {
           pixels[x][y] = new Pixel(this, x, y);
         }
      }
      return pixels;
   }
   
   /** 
    * Gets all the pixels in the picture and returns
    * them in a two dimensional array in row-major order
    * @return the two dimensional array of pixels in row-
    * major order (ie [row][col]).
    */
   public Pixel[][] get2DPixelsRowMajor()
   {
      int width = getImage().getWidth();
      int height = getImage().getHeight();
      Pixel[][] pixels = new Pixel[height][width];
      
      for (int y = 0; y < height; y++) 
      {
         for (int x = 0; x < width; x++)
         {
           pixels[y][x] = new Pixel(this, x, y);
         }
      }
      return pixels;
   }
   
   /**
    * Loads a picture from a given source. 
    * @param source the image source. If the source starts
    * with http://, it is a URL, otherwise, a filename.
    */
   public void load(String source)
   {
      try 
      {
         if (source.startsWith("http://"))
         {
            BufferedImage img = ImageIO.read(new URL(source).openStream());
            int width = img.getWidth();
            int height = img.getHeight();
            GreenfootImage gimg = new GreenfootImage(width, height);
            for (int x = 0; x < width; x++)
                for (int y = 0; y < height; y++)
                    gimg.setColorAt(x, y, new java.awt.Color(img.getRGB(x, y)));
            setImage(gimg);        
         }
         else 
         {
             setImage(new GreenfootImage(source));
             World world = getWorld();
             if (world != null) world.repaint();
         }
         this.source = source;
      }
      catch (Exception ex)
      {
         this.source = null;
         ex.printStackTrace();
      }
   }      

   /**
    * Reloads this picture, undoing any manipulations.
    */
   public void reload()
   {
      load(source);
   }
   
   /**
    * Displays a file chooser for picking a picture.
    */
   public void pick()
   {
      JFileChooser chooser = new JFileChooser(".");
      if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
      {
         load(chooser.getSelectedFile().getAbsolutePath());
      }
   }   
   
   /**
    * This method is called by the Greenfoot framework.
    */
   public void repaint()
   {
      count++;
      if (count == 10 * getImage().getWidth()) 
      {
         getWorld().repaint();
         count = 0;
      }
   }
   
   /**
    * Method to return the width of the picture
    * @return the width in pixels
    */
   public int getWidth() 
   {
       return getImage().getWidth();
   }
   
   /**
    * Method to return the height of the picture
    * @return the height in pixels
    */
   public int getHeight()
   {
       return getImage().getHeight();
   }
   
   /**
    * Method to write the picture to a file
    */
   public void write(String fileName) throws IOException
   {
      String extension = ".png"; // the default is png
      int pos = fileName.indexOf(".");
      if (pos >= 0) 
         extension = fileName.substring(pos+1);
   
      // create the file object
      File file = new File(fileName);
   
      // write the contents of the buffered image to the file 
      ImageIO.write(getImage().getAwtImage(), extension, file);
     
   }

   /**
    * This method is called by the Greenfoot framework. You may want to override it 
    * to automatically load a picture and carry out manipulations when the user clicks
    * on the act button in Greenfoot.
    */
   public void act() 
   {
   }   
}
