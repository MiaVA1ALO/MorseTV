import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
Decoder.java

Made by Mia Boudreau

Decodes .txt files containing data which allows images to be sent using morse code.
t = pixel, e = space, i = next column
 */
public class Decoder
{
    private static String data = "";
    private static final Scanner sc = new Scanner(System.in);
    //Asks for the filename and initiates the readFile function.
    public static void main(String[] args)
    {
        System.out.println("Enter filename of .txt to read:");
        String filename = sc.nextLine();

        readFile(filename);
    }
    //Takes in the data of the txt file supplied by the user.
    private static void readFile(String filename)
    {
        try
        {
            Scanner fileReader = new Scanner(new File(filename + ".txt"));
            while(fileReader.hasNext())
            {
                data += fileReader.next();
            }
            data = data.toLowerCase();
            System.out.println("Data: " + data);
            decodeData();
        }
        //Defines what happens if there is an error.
        catch(IOException exception)
        {
            System.out.println("Error: " + exception);
        }
    }
    //Decodes input data.
    private static void decodeData()
    {
        System.out.println("Enter filename to create/overwrite image:");
        String filename = sc.nextLine();
        System.out.println("Enter file extension:");
        String extension = sc.nextLine();

        int width = 0;
        //Find image width by detecting "I"s.
        for(int i = 0; i < data.length(); i++)
        {
            if(data.charAt(i) == ('i'))
            {
                width = i;
                break;
            }
        }
        System.out.println("Width: " + width + " Height: " + data.length() / width);

        // Generates the image
        BufferedImage output = new BufferedImage(width, data.length() / width, BufferedImage.TYPE_INT_BGR);

        // Removes all the "I"s because they are not important now.
        String temp = "";
        for(int i = 0; i < data.length(); i++)
        {
            if(data.charAt(i) == 'e' || data.charAt(i) == 't')
            {
                temp += data.charAt(i);
            }
        }
        data = temp;
        System.out.println("De-id data: " + data);

        /*
        A double for loop, which cycles through every pixel and detects
        whether there should be a pixel change.
         */
        System.out.print("Decoded data: ");
        for(int y = 0; y < data.length() / width; y++)
        {
            for(int x = 0; x < width; x++)
            {
                System.out.print(data.charAt(y * width + x));
                if(data.charAt(y * width + x) == 't')
                {
                    output.setRGB(x, y, 237);
                }
            }
        }
        //Write the image output file.
        try
        {
            ImageIO.write(output, extension, new File(filename + "." + extension));
        }
        //Defines what happens if there is an error.
        catch(IOException exception)
        {
            System.out.println("Error: " + exception);
        }
    }
}
