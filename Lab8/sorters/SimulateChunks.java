package sorters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class to read the numbers from file and puts them in an array.
 * then splits the array according to the given memory size 
 * and adds the chunks to the chunks array
 * @author Thanh Nguyen
 *
 */
public class SimulateChunks
{

   /**
    * @param MEM_SIZE for the simulated memory size
    * @param string for the name of input file
    * @param fileChunksAsArrays: an ArrayList of Integer[] to hold chunks of arrays of integers
    */
   public static void splitFileIntoArrayChunks(final int MEM_SIZE, String string, ArrayList<Integer[]> fileChunksAsArrays)
   {
      int numChunks;
      String line;
      
      File infile = new File(string);

      try 
      {
         Scanner input = new Scanner(infile);
         line = input.nextLine(); 
         String [] tokens = line.split(",");

         //determine number of chunks needed
         if(tokens.length % MEM_SIZE == 0)
            numChunks = tokens.length / MEM_SIZE;
         else
            numChunks = tokens.length / MEM_SIZE + 1;        
         
         for(int i = 0; i < numChunks - 1; i++)
         {
            Integer[] chunk = new Integer[MEM_SIZE];
            for(int j = 0; j < MEM_SIZE; j++)
               chunk[j] = Integer.parseInt(tokens[i * MEM_SIZE + j]);
            fileChunksAsArrays.add(chunk);
         }
         
         //for the remaining elements in the last chunk 
         int size = tokens.length % MEM_SIZE;
         Integer[] chunk = new Integer[size];
         
         for(int i = 0; i < size; i++)
            chunk[i] = Integer.parseInt(tokens[i]);
         
         fileChunksAsArrays.add(chunk);
         
         input.close();
      } 
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      }   
   }
}
