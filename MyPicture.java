import java.io.*;       // for file input and output
import java.util.*;     // for ArrayList
import javax.swing.*;   // the GUI items - modal dialog boxes, etc.
import java.lang.*;     // for maths

/**
 * Class to edit for your project
 */

public class MyPicture extends Picture
{
    private final static int MULTIPLIER1  =  263;
    private final static int MULTIPLIER2  =  419;
    
    /** 
     * Constructor that takes no arguments 
     */
    public MyPicture()
    {
        super();
    }

    /**
     * Constructor that takes the filename to read the displyed picture data
     * 
     * @param filename the filename to read the picture data from
     */
    public MyPicture(String filename)
    {
        super(filename);
    }
    
    /**
     * Method to extract hidden image from lowest 4 bits.
     * 
     * This method uses 1D arrays and will produce a restored image 
     * with the same dimensions as the displayed image.
     * 
     */
   
    public void restore4bitImage()
    {
        Pixel[] pixelArray = this.getPixels();
        
        Pixel pixelObj = null;

        int redChannel   = 0;
        int greenChannel = 0;
        int blueChannel  = 0;
        int alphaChannel = 0;

        // loop through all the pixels
    
        for (int i = 0; i < pixelArray.length; i++)
        {
      
            // get the current pixel
      
            pixelObj = pixelArray[i];

            // get the red, blue and green intensities
      
            redChannel   = pixelObj.getRed();
            greenChannel = pixelObj.getGreen();
            blueChannel  = pixelObj.getBlue();
            alphaChannel = pixelObj.getAlpha();
      
            redChannel   = Math.min(((redChannel   % 16) * 16), 255);
            greenChannel = Math.min(((greenChannel % 16) * 16), 255);
            blueChannel  = Math.min(((blueChannel  % 16) * 16), 255);
            alphaChannel = Math.min(((alphaChannel % 16) * 16), 255);
            
            // set the pixel colour
      
            pixelObj.setColor(new Color( redChannel, greenChannel, blueChannel, alphaChannel));
        }
    }
   /**
     * Method to extract hidden image from lowest x bits.
     * 
     * This method uses 1D arrays and will produce a restored image 
     * with the same dimensions as the displayed image.
     * 
     */
    public void restoreXbitImage(int bit)
    {
        Pixel[] pixelArray = this.getPixels();
        
        Pixel pixelObj = null;

        int redChannel   = 0;
        int greenChannel = 0;
        int blueChannel  = 0;
        int alphaChannel = 0;

        // loop through all the pixels
    
        for (int i = 0; i < pixelArray.length; i++)
        {
      
            // get the current pixel
      
            pixelObj = pixelArray[i];

            // get the red, blue and green intensities
      
            redChannel   = pixelObj.getRed();
            greenChannel = pixelObj.getGreen();
            blueChannel  = pixelObj.getBlue();
            alphaChannel = pixelObj.getAlpha();
            
            redChannel   = Math.min(((redChannel   % (int)Math.pow(2, bit)) * (int)Math.pow(2, 8 - bit)), 255);
            greenChannel = Math.min(((greenChannel % (int)Math.pow(2, bit)) * (int)Math.pow(2, 8 - bit)), 255);
            blueChannel  = Math.min(((blueChannel  % (int)Math.pow(2, bit)) * (int)Math.pow(2, 8 - bit)), 255);
            alphaChannel = Math.min(((alphaChannel % (int)Math.pow(2, bit)) * (int)Math.pow(2, 8 - bit)), 255);
            
            // set the pixel colour
      
            pixelObj.setColor(new Color( redChannel, greenChannel, blueChannel, alphaChannel));
        }
    }
    /**
     * Method to extract short array data from the low 4 bits of this picture.
     * 
     * Each pixel contains one short value.
     * 
     */
       public void restore4bitData()
    {
        Pixel[] pixelArray = this.getPixels();
        
        Pixel pixelObj = null;

        int redChannel   = 0;
        int greenChannel = 0;
        int blueChannel  = 0;
        int alphaChannel = 0;
        
        int outRed = 0;
        int outGreen = 0;
        int outBlue = 0;
        int outAlpha = 0;

        // loop through all the pixels
        
        short[] outArray = new short[pixelArray.length];        
    
        for (int i = 0; i < pixelArray.length; i++)
        {
      
            // get the current pixel
      
            pixelObj = pixelArray[i];

            // get the red, blue and green intensities
      
            redChannel   = pixelObj.getRed();
            greenChannel = pixelObj.getGreen();
            blueChannel  = pixelObj.getBlue();
            alphaChannel = pixelObj.getAlpha();
      
            outRed   = (redChannel   % 16);
            outGreen = (greenChannel % 16);
            outBlue  = (blueChannel  % 16);
            outAlpha = (alphaChannel % 16);
            
            outArray[i] = (short)(outAlpha + (16*outBlue) + (256*outGreen) + (4096*outRed));
        }
        writeDataFile( outArray);
    }
    

    /**
     * Method to hide newBg image in the low 4 bits of this picture.
     * 
     * This method uses 2D arrays and use different sized images.
     * 
     */
   
    public void hide4bitImage()
    {
        JFileChooser chooser = new JFileChooser(".");
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;
                
        Picture hiddenImage = new Picture(chooser.getSelectedFile().getAbsolutePath());
        
        // get the pixels of the displayed carrier image as a 2D Pixel array 
    
        Pixel[][] pixelArray = this.get2DPixels();
        Pixel pixelObj = null;
    
        // gets the height and width of the displayed carrier image
    
        int picWidth  = this.getWidth();
        int picHeight = this.getHeight();
    
        // declare 2D arrays to hold the red, green and blue components of the carrier image pixels
    
        int[][] redChannel   = new int [picWidth][picHeight];
        int[][] greenChannel = new int [picWidth][picHeight];
        int[][] blueChannel  = new int [picWidth][picHeight];
        int[][] alphaChannel = new int [picWidth][picHeight];
        
        // declare a 2D Pixel array for the hidden image
    
        Pixel[][] pixelArray2 = hiddenImage.get2DPixels();
        Pixel pixelObj2 = null;

        int[][] redChannel2   = new int [picWidth][picHeight];
        int[][] greenChannel2 = new int [picWidth][picHeight];
        int[][] blueChannel2  = new int [picWidth][picHeight];
        int[][] alphaChannel2 = new int [picWidth][picHeight];
        
        // gets the height and width of the background image
    
        int hiddenWidth  = hiddenImage.getWidth();
        int hiddenHeight = hiddenImage.getHeight();
    
        // choose the smallest of the carrier image and hidden image width
    
        int width  = Math.min(picWidth,  hiddenWidth);
    
        // choose the smallest of the carrier image and hidden image height
    
        int height = Math.min(picHeight, hiddenHeight);
    
        // loop through the carrier image pixels and shift image into higher bits
    
        for (int i = 0; i < picWidth; i++)
            for (int j = 0; j < picHeight; j++)
            { 
                // get the current pixel
      
                pixelObj  = pixelArray[i][j];

                // get the red, blue and green intensities for the current pixel
      
                redChannel[i][j]   = pixelObj.getRed();
                greenChannel[i][j] = pixelObj.getGreen();
                blueChannel[i][j]  = pixelObj.getBlue();  
                alphaChannel[i][j] = pixelObj.getAlpha();
                
                // scale the intensities of the carrier image to fit in the highest 4 bits
           
                redChannel[i][j]   = Math.max(((redChannel[i][j]   / 16) * 16), 0);
                greenChannel[i][j] = Math.max(((greenChannel[i][j] / 16) * 16), 0);
                blueChannel[i][j]  = Math.max(((blueChannel[i][j]  / 16) * 16), 0);
                alphaChannel[i][j] = Math.max(((alphaChannel[i][j] / 16) * 16), 0);
            }
    
        // now loop through the pixels shared by the two images pixels
    
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
            {
        
                // get the current pixel from the image to be hidden
      
                pixelObj2 = pixelArray2[i][j];

                // get the red, blue and green intensities
      
                redChannel2[i][j]   = pixelObj2.getRed();
                greenChannel2[i][j] = pixelObj2.getGreen();
                blueChannel2[i][j]  = pixelObj2.getBlue();
                alphaChannel2[i][j] = pixelObj2.getAlpha();
                
                /* Scale the intensities down so they fit in the lowest 4 bits.
                 * Use Math.max test to check the intensities are not negative.
                 */
      
                redChannel2[i][j]   = Math.max((redChannel2[i][j]   / 16), 0);
                greenChannel2[i][j] = Math.max((greenChannel2[i][j] / 16), 0);
                blueChannel2[i][j]  = Math.max((blueChannel2[i][j]  / 16), 0);
                alphaChannel2[i][j] = Math.max((alphaChannel2[i][j] / 16), 0);                
            }
     
        // now loop through the pixels shared by the two images pixels again
           
        for (int i = 0; i < picWidth; i++)
            for (int j = 0; j < picHeight; j++)
            {
        
                // get the current displayed pixel (We are going to set it's colour at the bottom of the loop.)
      
                pixelObj  = pixelArray[i][j];

                /*  Add the high and low bit components together for the current pixel.
                 *  Use Math.min check in case they exceed 255.
                 */
          
                redChannel[i][j]   = Math.min((redChannel[i][j]   + redChannel2  [i][j]), 255);
                greenChannel[i][j] = Math.min((greenChannel[i][j] + greenChannel2[i][j]), 255);
                blueChannel[i][j]  = Math.min((blueChannel[i][j]  + blueChannel2 [i][j]), 255);
                alphaChannel[i][j] = Math.min((alphaChannel[i][j] + alphaChannel2[i][j]), 255);
                
                // set the displayed pixel colour
      
                pixelObj.setColor(new Color( redChannel[i][j], greenChannel[i][j], blueChannel[i][j], alphaChannel[i][j]));
            }
    }      
    /**
     * Method to hide newBg image in the low x bits of this picture.
     * 
     * This method uses 2D arrays and use different sized images.
     * 
     */
    public void hideXbitImage(int bit)
    {
        JFileChooser chooser = new JFileChooser(".");
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;
                
        Picture hiddenImage = new Picture(chooser.getSelectedFile().getAbsolutePath());
        
        // get the pixels of the displayed carrier image as a 2D Pixel array 
    
        Pixel[][] pixelArray = this.get2DPixels();
        Pixel pixelObj = null;
    
        // gets the height and width of the displayed carrier image
    
        int picWidth  = this.getWidth();
        int picHeight = this.getHeight();
    
        // declare 2D arrays to hold the red, green and blue components of the carrier image pixels
    
        int[][] redChannel   = new int [picWidth][picHeight];
        int[][] greenChannel = new int [picWidth][picHeight];
        int[][] blueChannel  = new int [picWidth][picHeight];
        int[][] alphaChannel = new int [picWidth][picHeight];
        
        // declare a 2D Pixel array for the hidden image
    
        Pixel[][] pixelArray2 = hiddenImage.get2DPixels();
        Pixel pixelObj2 = null;

        int[][] redChannel2   = new int [picWidth][picHeight];
        int[][] greenChannel2 = new int [picWidth][picHeight];
        int[][] blueChannel2  = new int [picWidth][picHeight];
        int[][] alphaChannel2 = new int [picWidth][picHeight];
        
        // gets the height and width of the background image
    
        int hiddenWidth  = hiddenImage.getWidth();
        int hiddenHeight = hiddenImage.getHeight();
    
        // choose the smallest of the carrier image and hidden image width
    
        int width  = Math.min(picWidth,  hiddenWidth);
    
        // choose the smallest of the carrier image and hidden image height
    
        int height = Math.min(picHeight, hiddenHeight);
        
        ArrayList<ArrayList<Pixel>> pictureArray = new ArrayList<ArrayList<Pixel>>();
        System.out.println("Storing to arraylist~");
         // store rows in arraylist
        for(int i = 0; i < height; i++)
        {
           ArrayList<Pixel> tempRow = new ArrayList<Pixel>();
            for(int j = 0; j < width; j++)
            {
                System.out.println(pixelArray2[j][i]);
                 tempRow.add(pixelArray2[j][i]);
            }
           pictureArray.add(tempRow);
        }
        System.out.println("Done");
        // Jumble the image
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                 pixelArray2[((10 * i) + j) % width][i] = pictureArray.get(i).get(j);
            }
        }
        
        // loop through the carrier image pixels and shift image into higher bits
        for (int i = 0; i < picWidth; i++)
            for (int j = 0; j < picHeight; j++)
            { 
                // get the current pixel
      
                pixelObj  = pixelArray[i][j];

                // get the red, blue and green intensities for the current pixel
      
                redChannel[i][j]   = pixelObj.getRed();
                greenChannel[i][j] = pixelObj.getGreen();
                blueChannel[i][j]  = pixelObj.getBlue();  
                alphaChannel[i][j] = pixelObj.getAlpha();
                
                // scale the intensities of the carrier image to fit in the highest x bits
           
                redChannel[i][j]   = Math.max(((redChannel[i][j]   / (int)Math.pow(2, bit)) * (int)Math.pow(2, bit)), 0);
                greenChannel[i][j] = Math.max(((greenChannel[i][j] / (int)Math.pow(2, bit)) * (int)Math.pow(2, bit)), 0);
                blueChannel[i][j]  = Math.max(((blueChannel[i][j]  / (int)Math.pow(2, bit)) * (int)Math.pow(2, bit)), 0);
                alphaChannel[i][j] = Math.max(((alphaChannel[i][j] / (int)Math.pow(2, bit)) * (int)Math.pow(2, bit)), 0);
            }
    
        // now loop through the pixels shared by the two images pixels
    
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
            {
        
                // get the current pixel from the image to be hidden
      
                pixelObj2 = pixelArray2[i][j];

                // get the red, blue and green intensities
      
                redChannel2[i][j]   = pixelObj2.getRed();
                greenChannel2[i][j] = pixelObj2.getGreen();
                blueChannel2[i][j]  = pixelObj2.getBlue();
                alphaChannel2[i][j] = pixelObj2.getAlpha();
                
                /* Scale the intensities down so they fit in the lowest x bits.
                 * Use Math.max test to check the intensities are not negative.
                 */
      
                redChannel2[i][j]   = Math.max((redChannel2[i][j]   / (int)Math.pow(2, 8 - bit)), 0);
                greenChannel2[i][j] = Math.max((greenChannel2[i][j] / (int)Math.pow(2, 8 - bit)), 0);
                blueChannel2[i][j]  = Math.max((blueChannel2[i][j]  / (int)Math.pow(2, 8 - bit)), 0);
                alphaChannel2[i][j] = Math.max((alphaChannel2[i][j] / (int)Math.pow(2, 8 - bit)), 0);                
            }
     
        // now loop through the pixels shared by the two images pixels again
           
        for (int i = 0; i < picWidth; i++)
            for (int j = 0; j < picHeight; j++)
            {
        
                // get the current displayed pixel (We are going to set it's colour at the bottom of the loop.)
      
                pixelObj  = pixelArray[i][j];

                /*  Add the high and low bit components together for the current pixel.
                 *  Use Math.min check in case they exceed 255.
                 */
          
                redChannel[i][j]   = Math.min((redChannel[i][j]   + redChannel2  [i][j]), 255);
                greenChannel[i][j] = Math.min((greenChannel[i][j] + greenChannel2[i][j]), 255);
                blueChannel[i][j]  = Math.min((blueChannel[i][j]  + blueChannel2 [i][j]), 255);
                alphaChannel[i][j] = Math.min((alphaChannel[i][j] + alphaChannel2[i][j]), 255);
                
                // set the displayed pixel colour
      
                pixelObj.setColor(new Color( redChannel[i][j], greenChannel[i][j], blueChannel[i][j], alphaChannel[i][j]));
            }
    }    
    /**
     * Method to hide short array data in the low 4 bits of this picture.
     * 
     * Each pixel contains one short value.
     */
   
    public void hide4bitData()
    {
        short inData[] = readDataFile();        // needs a null check
        int inDataLength = inData.length;
        
        Pixel[] pixelArray = this.getPixels();
        
        Pixel pixelObj = null;

        int redChannel    = 0;
        int greenChannel  = 0;
        int blueChannel   = 0;
        int alphaChannel  = 0;        
        
        int redChannel2   = 0;
        int greenChannel2 = 0;
        int blueChannel2  = 0;
        int alphaChannel2 = 0;

        // loop through all the pixels
    
        for (int i = 0; i < pixelArray.length; i++)
        //for (int i = 0; i < inDataLength; i++)
        {
      
            if (i<inDataLength)
            {
                redChannel2   = (inData[i] >> 12) & 0xF;
                greenChannel2 = (inData[i] >>  8) & 0xF;
                blueChannel2  = (inData[i] >>  4) & 0xF;                
                alphaChannel2 = (inData[i]) & 0xF;
                //System.out.println("r2 = " + redChannel2);    // Diagnostic only.
                //System.out.println("g2 = " + greenChannel2);  // Expect values in range
                //System.out.println("b2 = " + blueChannel2);   // 0 to 15 for each Channel2
                //System.out.println("a2 = " + alphaChannel2);
            }
            else
            {
                redChannel2   = 0;
                greenChannel2 = 0;
                blueChannel2  = 0;
                alphaChannel2 = 0;
            }
                
            // get the current pixel
      
            pixelObj = pixelArray[i];

            // get the red, blue and green intensities
      
            redChannel    = pixelObj.getRed();
            greenChannel  = pixelObj.getGreen();
            blueChannel   = pixelObj.getBlue();  
            alphaChannel  = pixelObj.getAlpha();
      
            redChannel    = Math.max(((redChannel   / 16) * 16), 0);
            greenChannel  = Math.max(((greenChannel / 16) * 16), 0);
            blueChannel   = Math.max(((blueChannel  / 16) * 16), 0);
            alphaChannel  = Math.max(((alphaChannel / 16) * 16), 0);
      
            redChannel    = Math.min((redChannel   + redChannel2),   255);
            greenChannel  = Math.min((greenChannel + greenChannel2), 255);
            blueChannel   = Math.min((blueChannel  + blueChannel2),  255);
            alphaChannel  = Math.min((alphaChannel + alphaChannel2), 255);

            // set the pixel colour
      
            pixelObj.setColor(new Color( redChannel, greenChannel, blueChannel, alphaChannel));

            //System.out.println("pixels processed = " + i);   // diagnostic only
        }
    }
    
    /**
     *   Method to read to a short type array from a data file
     * 
     *   The code uses a DataInputStream to read short type data from the file data/completeData.dat
     *   All samples in the file are read to an ArrayList before being copied to the short data array.
     */
    
    private short[] readDataFile()
    {
        System.out.println("\n***** Starting to read data from file *****");  // diagnostic o/p only

        ArrayList<Short> inList = new ArrayList<Short>();
        
        short inTest = 0;

        try 
        {
            JFileChooser chooser = new JFileChooser(".");
            if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return null;
            
            DataInputStream istream = new DataInputStream( new FileInputStream(chooser.getSelectedFile().getAbsolutePath()));
            System.out.println("istream is open");
            
            boolean eof = false;
            
            while( !eof)
            {
                try
                {    
                    inTest = istream.readShort();
                    inList.add(inTest);
                }
                catch (EOFException e)
                {
                    eof = true;
                }
            }
            System.out.println("short data read");
            istream.close();
            System.out.println("istream is closed");
            
            short[] inArray = new short[inList.size()];
            
            for (int i=0; i<inList.size(); i++)
            {
                inArray[i] = inList.get(i);
            }
            System.out.println("shorts processed = " + inList.size());
            return inArray;
        }
        catch( IOException e)
        {
            System.err.println("Read file not opened");
            return null;
        }
    }
    
    /**
     *   Method to save the short array to a data file
     * 
     *   The code uses a DataOutputStream to send short type data to the file data/completeData.dat
     *   All samples in the extracted short array are written to file
     */
    
    private void writeDataFile(short[] outArray)
    {
        System.out.println("\n***** starting to write data file *****");  // diagnostic o/p only
                
        try 
        {
            JFileChooser chooser = new JFileChooser(".");

            if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return;
            
            DataOutputStream ostream = new DataOutputStream( new FileOutputStream(chooser.getSelectedFile().getAbsolutePath()));
            System.out.println("ostream is open");
        
            for (int i = 0; i < outArray.length; i++) {
                ostream.writeShort(outArray[i]);
            }

            System.out.println("short data written");
            ostream.close();
            System.out.println("ostream is closed");
        }
        catch( IOException e)
        {
            System.err.println("Write file not opened");
        }
    }

    public void saveImageFile()
    {
        try 
        {
            JFileChooser chooser = new JFileChooser(".");
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("dat");

            if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) return;
            
            write(chooser.getSelectedFile().getAbsolutePath());
        }
                catch( IOException e)
        {
            System.err.println("Write file not opened");
        }
    }

    
    /**
     * This method is called by the Greenfoot framework. 
     * 
     */
   
    public void act()
    {

    }
}
