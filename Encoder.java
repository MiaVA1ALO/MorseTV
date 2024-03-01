import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encoder
{
    private static String output = "";
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("Enter filename + extension of image:");
        String filename = sc.nextLine();

        readFile(filename);
    }

    private static void readFile(String filename)
    {
        Color c;
        try
        {
            BufferedImage input = ImageIO.read(new File(filename));
            for(int y = 0; y < input.getHeight(); y++)
            {
                for(int x = 0; x < input.getWidth(); x++)
                {
                    c = new Color(input.getRGB(x, y));
                    if(c.getBlue() >= 1)
                    {
                        output += "1";
                    }
                    else
                    {
                        output += "0";
                    }
                }
                output += "" + "\n";
            }
        }
        catch(IOException exception)
        {
            System.out.println("Error: " + exception);
        }
        encodeData();
    }

    private static void encodeData()
    {
        try
        {
            FileWriter writer = new FileWriter("output.txt");
            writer.write(output);
            writer.close();

        }
        catch(IOException exception)
        {
            System.out.println("Error: " + exception);
        }

    }
}