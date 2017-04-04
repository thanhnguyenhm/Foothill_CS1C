package subsetsum;

import java.io.*;
import java.util.*;

/**
 * Read the input file that contains the prices of the different items.
 * @author CS1C, Foothill College, Thanh Nguyen
 * @version 1.0
 */

public class GroceriesFileReader
{
   public ArrayList<Double> readFile(String filePath)
   {
      ArrayList<Double> priceOfGroceries = new ArrayList<Double>();
      
      Scanner s;
      try
      {
         s = new Scanner(new File(filePath));
         while (s.hasNext())
         {
            String food = s.next();
            String delims = "[,]";
            String[] tokens = food.split(delims);
            
            priceOfGroceries.add(Double.parseDouble(tokens[1]));          
         }
      } catch (FileNotFoundException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
           
      return priceOfGroceries;
   }
   
}
