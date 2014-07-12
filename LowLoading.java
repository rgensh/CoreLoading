package coreloading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rem
 */
public class LowLoading extends Thread
{
    private int m_offset = 0;
    
    private void ReadFile() throws IOException, InterruptedException
    {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:/TextFile.txt", "r");
        char symbol = randomAccessFile.readChar();
        m_offset = (int)(Math.random() * 1000000) + (int)symbol;
        //System.out.println("Offset = " + m_offset);
        randomAccessFile.seek(m_offset);
                
        //System.out.println("symbol = " + symbol);
//        FileReader fileReader = new FileReader("D:/TextFile.txt");
//        char[] buffer = new char[1];
//        int offset = (int)(Math.random() * 1000);
//        System.out.println("Offset = " + offset);
//        fileReader.read(buffer, offset, 1);
//        System.out.println("buffer[0] = " + buffer[0]);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        String currentLine;
//        int counter = 0;
//        long lengthCounter = 0;
//        while((currentLine = bufferedReader.readLine()) != null)
//        {
//            //System.out.println(currentLine);
//            lengthCounter += currentLine.length();
//            counter++;
//        }
//        System.out.println("Counter value = " + counter);      
//        System.out.println("Length counter value = " + lengthCounter); 
    }
    
    public void run()
    {
        long counter = 0;
        while(true)
        {
            try
            {
                ReadFile();
                //sleep(250);
//                counter++;
//                if (counter > 10)
//                    break;
            }
            catch(Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }
}
