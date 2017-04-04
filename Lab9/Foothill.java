import org.jfree.ui.RefineryUtilities;

/**
 * An application that investigates the Quicksort's recursion limit of arrays of various sizes
 * then creates a chart to visualize the result
 * @author Foothill, Thanh Nguyen
 *
 */
public class Foothill
{
   /**
    * Declare class static variables:
    * constant integers for recursion limits min and max
    * 1st array: an array of ARRAY_SIZES from 20000 to 1000000 with step of 196000
    * 2nd array: an array of recursion limits
    * 3rd array: an array of results
    */
   final static int MIN_LIMIT = 2, MAX_LIMIT = 300;
   final static int [] ARRAY_SIZES = {20000, 216000, 412000, 608000, 804000, 1000000};
   final static int [] RECURS_LIMITS = new int[MAX_LIMIT / 2];
   static Double [] results = new Double[RECURS_LIMITS.length * ARRAY_SIZES.length];
   
   public static void main(String[] args)
   {     
      int randomInt, recursionLimit = MIN_LIMIT;
      int index = 0;
      long startTime, estimatedTime1; 
      double sec; //time taken to do quicksort as second
      Integer[] testArray;
      
      //filling data for array of recursion limits(2nd array)
      while(recursionLimit <= MAX_LIMIT && index < RECURS_LIMITS.length)
      {
         RECURS_LIMITS[index] = recursionLimit;
         recursionLimit += 2;
         index++;
      }
      
      //do quick sort and assign data to result array(3rd array)
      for(int i = 0; i < RECURS_LIMITS.length; i++)
      {       
         //do the quick sort
         FHsort.setRecursionLimit(RECURS_LIMITS[i]);         
         System.out.println("Recursion Limit: " + RECURS_LIMITS[i] + "\n");
         
         for (int test = 0; test < ARRAY_SIZES.length; test++)
         {
            int testSize = ARRAY_SIZES[test];
            Integer[] arrayOfInts1 = new Integer[testSize];           

            for (int k = 0; k < testSize; k++)
            {
                randomInt = (int) (Math.random() * testSize);
                arrayOfInts1[k] = randomInt;
            }
            
            // copy the test array
            testArray = arrayOfInts1.clone();
            
            startTime = System.nanoTime();  // ------------------ start 

            FHsort.quickSort(testArray);

            estimatedTime1 = System.nanoTime() - startTime;    // ---------------------- stop
            System.out.println("Quick sort Elapsed Time: "
                    + " size: " + testSize + ", "
                    + TimeConverter.convertTimeToString(estimatedTime1) 
                    + " = " + estimatedTime1 + "ns");
            
            //convert estimate time to second
            sec = (double)estimatedTime1 / 1e9;
            
            results[i * ARRAY_SIZES.length + test] = sec;
         }//end inner for loop
         
         System.out.println();
         
      }//end outer for loop
      
      //create summary table--------------------------------------------------------------
      //create table header
      System.out.printf("%nSummary Table%n********************************************************************************%n");
      // output column headers
      System.out.print(String.format("%-12s ", "Limit/Size |"));
      for (int i = 0; i < ARRAY_SIZES.length; i++)
        System.out.print(String.format("%-12s", ARRAY_SIZES[i]));
      System.out.println("\n--------------------------------------------------------------------------------");
      
      for(int i = 0; i < RECURS_LIMITS.length; i ++)
      {
         System.out.print(String.format("%-12s ", RECURS_LIMITS[i]));
         for(int j = 0; j < ARRAY_SIZES.length; j++)
         {
            String strDouble = String.format("%.4f", results[i * ARRAY_SIZES.length + j]);
            System.out.print(String.format("%-12s", strDouble));
         }
         System.out.println();
      }
        
      //create chart
      XYLineChart_AWT chart = new XYLineChart_AWT("Analyzing quickSort's Recursion Limits", "Analyzing quickSort's Recursion Limits");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }//end main
}//end class Foothill
