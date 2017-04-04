package sorters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * using min heap sorting techniques to sort all chunks with respect to each other
 * @author Thanh Nguyen
 *
 */
public class MinHeapArrayMerger
{
   /**
    * Uses the minHeap technique to sort the various chunks with respect to each other 
    * and writes the output to a file called result_using_min_heap.txt
    * @param memorySize maximum memory size
    * @param inputChunks sorted chunks as list of integers
    * @param minHeap to hold the current minimums
    * @param outfile: name of the output file
    */
   public static void mergeSortedArrays(final int memorySize, ArrayList<Integer[]> inputChunks, 
         HeapTuple[] minHeap, String outfile)
   {     
      FileOutputStream out=null;
      try 
      {
          out = new FileOutputStream(outfile);
      }
      catch( FileNotFoundException e )
      {
          System.err.println(e);
          return ;
      } //end catch   
      
      PrintWriter prtWriter = new PrintWriter(out, true);
      
      //create 2D array
      int[][] arr = new int[inputChunks.size()][memorySize];
      
      for(int i = 0; i < inputChunks.size(); i++)
      {
         for(int j = 0; j < inputChunks.get(i).length; j++)
         {
            arr[i][j] = inputChunks.get(i)[j];
         }
      }
   
      
      PriorityQueue<ArrayContainer> queue = new PriorityQueue<ArrayContainer>();
      int total=0;
 
      //add arrays to heap
      for (int i = 0; i < arr.length; i++) 
      {
         queue.add(new ArrayContainer(arr[i], 0, inputChunks.get(i).length));
         total = total + inputChunks.get(i).length;
      }
 
      int m=0;
      int result[] = new int[total];
 
      //while heap is not empty
      while(!queue.isEmpty())
      {
         ArrayContainer ac = queue.poll(); 
         if(ac.index < ac.size)
            result[m++]=ac.arr[ac.index];
 
         if(ac.index < ac.arr.length-1){
            queue.add(new ArrayContainer(ac.arr, ac.index+1, ac.size));
         }
      }
 
      for(int i = 0; i < result.length; i++)
      {
         System.out.print(result[i] + ",");
         prtWriter.print(result[i] + ",");
      }
      prtWriter.println("");
   }//end mergeSortedArrays
   
}

/**
 * helper class to contain the chunk in an array
 * @author Thanh
 *
 */
class ArrayContainer implements Comparable<ArrayContainer> 
{
   int[] arr;
   int index;
   int size;
 
   /**
    * Constructor with three parameters
    * @param arr  chunk as an array
    * @param index   array index
    * @param size    size of the chunk
    */
   public ArrayContainer(int[] arr, int index, int size) 
   {
      this.arr = arr;
      this.index = index;
      this.size = size;
   }
 
   @Override
   public int compareTo(ArrayContainer o) 
   {
      return this.arr[this.index] - o.arr[o.index];
   }
}
