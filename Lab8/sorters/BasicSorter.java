package sorters;

import cs1c.FHsort;

/**
 * Helper class to sorts an individual chunk in place
 * @author Thanh Nguyen
 *
 */
public class BasicSorter
{
   /**
    * sort an individual chunk using shell sort algorithm
    * @param chunk to be sorted in place
    */
   public static void sortChunk(Integer[] chunk)
   {
      FHsort.shellSort1(chunk);    
   }
}
