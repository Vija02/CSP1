/**
 * Just like the standard java.awt.Color class, but can be
 * constructed with double values.
 * 
 */
public class Color extends java.awt.Color
{ 
    /** Color Constants */
    
    public final static Color WHITE      = new Color(255, 255, 255);
    public final static Color LIGHT_GRAY = new Color(192, 192, 192);
    public final static Color GRAY       = new Color(128, 128, 128);
    public final static Color DARK_GRAY  = new Color( 64,  64,  64);
    public final static Color BLACK      = new Color(  0,   0,   0);
    public final static Color RED        = new Color(255,   0,   0);
    public final static Color PINK       = new Color(255, 175, 175);
    public final static Color ORANGE     = new Color(255, 200,   0);
    public final static Color YELLOW     = new Color(255, 255,   0);
    public final static Color GREEN      = new Color(  0, 255,   0);
    public final static Color MAGENTA    = new Color(255,   0, 255);
    public final static Color CYAN       = new Color(  0, 255, 255);
    public final static Color BLUE       = new Color(  0,   0, 255);
    
    public Color(double red, double green, double blue, double alpha) 
    {
        super((int) red, (int) green, (int) blue, (int) alpha);
    }

    public Color(double red, double green, double blue) 
    {
        super((int) red, (int) green, (int) blue);
    }

    public Color(java.awt.Color color)
    {
        super(color.getRGB());
    }   
}
