import greenfoot.*; 
/**
 * Class that extends World and holds Pictures that can act
 */ 
public class PictureWorld extends World
{

    /**
     * Constructor that creates a world to show pictures in
     */
    public PictureWorld()
    {    
        super(1400,800, 1);
        
        getBackground().setColor(java.awt.Color.WHITE);
        getBackground().fill();
        
        Picture pict = new MyPicture("images/green-forest.png");
        this.addObject(pict,700,400);
    }
}
