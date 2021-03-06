import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set the green to 0 */
  public void zeroGreen()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to set the red to 0 */
  public void zeroRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to set the red and green to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to set the blue and red to 0 */
  public void keepOnlyGreen()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to set the red and green to 0 */
  public void keepOnlyRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to set the RGB values to 255-value */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }
  
  /** Method to set RGB values to their average */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int avg = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
  }
  
  /** Method to set RGB values to their average */
  public void sepia()
  {
    Pixel[][] pixels = this.getPixels2D();
    this.grayscale();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int red = pixelObj.getRed();
        int green = pixelObj.getGreen();
        int blue = pixelObj.getBlue();
        if (red < 60)
        {
            pixelObj.setRed((int)(red * 0.90));
            pixelObj.setGreen((int)(green * 0.90));
            pixelObj.setBlue((int)(blue * 0.90));
        }
        else if (red < 190)
        {
            pixelObj.setBlue((int)(blue * 0.80));
        }
        else
        {
            pixelObj.setBlue((int)(blue * 0.90));
        }
      }
    }
  }
  
  /** Method to set  */
  public void fixUnderwater()
  {
    // 0,160 145,460
    Pixel[][] pixels = this.getPixels2D();
    Pixel pixel = null;
    //for (int row = 0; row < 145; row++)
    for (int row = 0; row < pixels.length; row++)
    {
      //for (int col = 160; col < 460; col++)
      for (int col = 0; col < pixels[0].length; col++)
      {
        int blue;
        int green;
        pixel = pixels[row][col];
        if (pixel.getBlue() > 175)
        {
            blue = (int) (pixel.getBlue() * 1.25);
            pixel.setBlue(blue);
            green = (int) (pixel.getGreen() * 1.25);
            pixel.setGreen(green);
        }
        else
        {
            blue = (int) (pixel.getBlue() * 0.75);
            pixel.setBlue(blue);
            green = (int) (pixel.getGreen() * 0.75);
            pixel.setGreen(green);
        }
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from right to left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from top to bottom */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int col = 0; col < pixels[0].length; col++)
    {
      for (int row = 0; row < height / 2; row++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height-1-row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from top to bottom */
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int col = 0; col < pixels[0].length; col++)
    {
      for (int row = 0; row < height / 2; row++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height-1-row][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    } 
  }
  
  /**
   * 
   */
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel bottomLeftPixel = null;
    Pixel topRightPixel = null;
    int shortSideLength = 0;
    if (pixels.length < pixels[0].length)
    {
        shortSideLength = pixels.length;
    }
    else
    {
        shortSideLength = pixels[0].length;
    }
    for (int y = 0; y < shortSideLength; y++)
    {
        for (int x = 0; x < shortSideLength; x++)
        {
            bottomLeftPixel = pixels[y][x];
            topRightPixel = pixels[x][y];
            topRightPixel.setColor(bottomLeftPixel.getColor());
        }
    }
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        count++;
      }
    }
    System.out.println(count);
  }
  
  /** Mirror just the arms part of a picture of a snowman */
  public void mirrorArms()
  {
    int mirrorPoint = 195;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    // loop through the rows
    for (int col = 105; col < 295; col++)
    {
      // loop from 160 to just before the mirror point
      for (int row = 160; row < mirrorPoint; row++)
      {
        topPixel = pixels[row][col];      
        bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
  }
  
  /** Mirror just the seagull part of a picture of a beach */
  public void mirrorGull()
  {
    int mirrorPoint = 235;
    Pixel rightPixel = null;
    Pixel leftPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    // loop through the rows
    for (int row = 235; row < 320; row++)
    {
      // loop from 345 to just before the mirror point
      for (int col = 345; col > mirrorPoint; col--)
      {
        rightPixel = pixels[row][col];      
        leftPixel = pixels[row][mirrorPoint - col + mirrorPoint];
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  /**
   * 
   */
  public void cropAndCopy(
                          Picture sourcePicture, 
                          int startSourceRow, 
                          int endSourceRow, 
                          int startSourceCol, 
                          int endSourceCol, 
                          int startDestRow, 
                          int startDestCol
                         )
  {
      Pixel[][] fromPixels = sourcePicture.getPixels2D();
      Pixel[][] toPixels = this.getPixels2D();
      Pixel fromPixel = null;
      Pixel toPixel = null;
      int rowCounter = 0;
      int colCounter = 0;
      for (int r = startSourceRow; r < endSourceRow; r++)
      {
          for (int c = startSourceCol; c < endSourceCol; c++)
          {
              fromPixel = fromPixels[r][c];
              toPixel = toPixels[startDestRow + rowCounter][startDestCol + colCounter];
              toPixel.setColor(fromPixel.getColor());
              colCounter++;
          }
          rowCounter++;
          colCounter = 0;
      }
  }
  
  /** Main method for testing - each class in Java can have a main method
   * Collage Lab
   */
  public static void main(String[] args)
  {
    Picture collage = new Picture(810,1440);
    
    Picture hiking = new Picture("hiking.jpg");
    collage.cropAndCopy(hiking, 0, 540, 0, 960, 0, 0);
    
    //Half size hiking picture (not in collage)
    Picture halfHiking = hiking.scale(0.5,0.5);
    
    //Half size grayscale hiking picture
    Picture grayscaleHiking = new Picture(halfHiking);
    grayscaleHiking.grayscale();
    collage.cropAndCopy(grayscaleHiking, 0, 270, 0, 480, 0, 960);
    
    //Half size negated hiking picture
    Picture negatedHiking = new Picture(halfHiking);
    negatedHiking.negate();
    collage.cropAndCopy(negatedHiking, 0, 270, 0, 480, 270, 960);
    
    //Half size zeroBlue hiking picture
    Picture noBlueHiking = new Picture(halfHiking);
    noBlueHiking.zeroBlue();
    collage.cropAndCopy(noBlueHiking, 0, 270, 0, 480, 540, 0);
    
    //Half size zeroGreen hiking picture
    Picture noGreenHiking = new Picture(halfHiking);
    noGreenHiking.zeroGreen();
    collage.cropAndCopy(noGreenHiking, 0, 270, 0, 480, 540, 480);
    
    //Half size vertically reflected hiking picture
    Picture vertReflectHiking = new Picture(halfHiking);
    vertReflectHiking.mirrorVertical();
    collage.cropAndCopy(vertReflectHiking, 0, 270, 0, 480, 540, 960);
    
    collage.write("Collage.jpg");
    
    collage.explore();
  }
} // this } is the end of class Picture, put all new methods before this
