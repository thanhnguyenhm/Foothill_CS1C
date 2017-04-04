package sorters;

/**
 * Reads multiple plain text files, which contain unsorted numbers.
 * 
 * Sorts the input files by dividing them into multiple chunks.
 * Note: Each chunk size is determined by the size of memory.
 * 
 * Sorting is done in two phases:
 * 
 * Phase 1. Each individual chunk is sorted.
 * 
 * Phase 2. Use min heap sorting techniques to sort all chunks
 *          with respect to each other. 
 *          
 *          Suggestion: Take advantage of the logic from our heap sorting algorithm
 *          we studied in modules.
 *          
 * @author Foothill College, Thanh Nguyen
 */

import java.util.ArrayList;
import cs1c.TimeConverter;

public class SortFileData
{
	/**
	 * Restricting the size of available memory to simulate large
	 * input file(s) that do not fit in memory.
	 * The size of a chunk is determined by the size of the memory.
	 */
	private static int MEM_SIZE = 50;

	private static final boolean ENABLE_DEBUG = false;
	private static final int OUTPUT_WIDTH = 70;
	private static final String OUTPUT_SEPARATOR = "----------------------------------------------------------------------";


	/**
	 * Display the contents of a chunk.
	 * @param chunk    a subset of the data as a chunk 
	 */
	public static void displayChunkContent(Integer [] chunk)
	{
		System.out.println(OUTPUT_SEPARATOR);

		String outStr = "";
		for (int elem : chunk)
		{
			outStr += elem + ",";            
			if (outStr.length() > OUTPUT_WIDTH)
			{
				System.out.println(outStr);
				outStr = "";
			}
		}

		if (outStr != "")
			System.out.println(outStr);  // print out left left one

		System.out.println(OUTPUT_SEPARATOR);	    
	}


	/**
	 * Display the chunk number and contents.
	 * @param chunk    a subset of the data as a chunk 
	 * @param index    the position of the data with respect to original
	 */
	public static void displayFileChunk(Integer [] chunk, int index)
	{
		System.out.println("file chunk[" + index + "] with size " + chunk.length + " :");
		displayChunkContent(chunk);
	}


	/**
	 * For debugging and displaying results.
	 * Outputs the array of Integer objects.
	 * @param array1    a subset of the data sorted 
	 * @param index1    the position of the data with respect to original
	 * @param array2    a subset of the data sorted 
	 * @param index2    the position of the data with respect to original
	 */
	public static void displaySortedChunks(
			Integer [] array1, int index1, 
			Integer [] array2, int index2)	
	{
		System.out.println("sort file chunk[" + index1 + "] :");
		displayChunkContent(array1);


		System.out.println("\nsort file chunk[" + index2 + "] :");
		displayChunkContent(array2);


		System.out.println("");
	}


	/**
	 * For debugging and displaying results.
	 * Used to output a sample number of chunks. 
	 */
	private static void displaySampleChunks(ArrayList<Integer[]> fileChunksAsArrays, int numOfChunks) 
	{		
		int numOfFileChunks = fileChunksAsArrays.size();

		for (int i = 0; i < numOfChunks-1; i++)
		{
			// check if requested number of chunks to display is valid	  
			if (i < numOfFileChunks)
			{
				System.out.println();
				System.out.println("Phase 1 : Sorted file chunks " + i + " and " + (i+1) + ":");
				displaySortedChunks(fileChunksAsArrays.get(i), i, fileChunksAsArrays.get(i+1), (i+1));
				i += 2;
			}       
		} // for all the chunks up to the requested number
	}


	public static void main(String[] args) 
	{
		final String filePath = "resources/";	// Directory path for input files


		// Sample input files in Comma-Seperated-Value (CSV) format		
		final String [] fileNames = {"numbers01.txt", "numbers02.txt", "numbers03.txt", "numbers04.txt"};

		ArrayList<Integer[]> fileChunksAsArrays = new ArrayList<Integer[]>();

		for (String fname : fileNames)
		{
			// TODO: Reads text files and stores the data into arrays of integers chunk(s).
			//       Each chunk is represented by an array of Integers of length MEM_SIZE
			//       Adds the chunk(s) to the list of chunks called fileChunksAsArrays
			// Suggestion: Use Arrays.copyOfRange(int[] original, int from, int to)
			//       to copy a chunk found into fileChunksAsArrays
			//       for more details see: 
			//       http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#copyOfRange(int[],%20int,%20int)
			SimulateChunks.splitFileIntoArrayChunks(MEM_SIZE, filePath + fname, fileChunksAsArrays);
		}


		// Phase 1. Sort each individual chunk ---------------------------------------
		// 
		// Note: the total size of all chunks should be the same as the total number
		// of values in each file divided by the memory size.
		int numOfChunks = fileChunksAsArrays.size();
		System.out.println("Number of arrays holding file input = " + numOfChunks);

		int chunkIndex = 0;
		for (Integer[] chunk : fileChunksAsArrays)
		{
			if (ENABLE_DEBUG)
			{
				displayFileChunk(chunk, chunkIndex);
				chunkIndex++;
			}

			// TODO: Pick a sorting algorithm which sorts an individual chunk in place.
			//       The sorting algorithm you should should depend on the specific size of the chunk.
			//       The sorted result is stored in the argument "chunk".
			BasicSorter.sortChunk(chunk);
		}

		// Display the result of various chunks after sorting.
		displaySampleChunks(fileChunksAsArrays, numOfChunks);


		// Phase 2. Use the min heap sorting techniques to sort all chunks ---------- 
		// 
		long startTime, estimatedTime;

		// TODO: Use the minHeap technique we learned in modules to sort
		//       the various chunks with respect to each other and write the output to 
		//       a file called "result_using_min_heap.txt"
		// Note: In class MinHeapArrayMerger, we are *not* explicitly calling FHsort.heapSort.

		// Use the array of HeapTuple objects called "minHeap" to hold the current minimums.
		// In your RUN.txt file show a sample number of iterations.
		HeapTuple[] minHeap = new HeapTuple[fileChunksAsArrays.size()];

		// capture start time
		startTime = System.nanoTime();

		MinHeapArrayMerger.mergeSortedArrays(MEM_SIZE, fileChunksAsArrays, 
				minHeap, filePath + "result_using_min_heap.txt");

		// stop and calculate elapsed time
		estimatedTime = System.nanoTime() - startTime;

		// report algorithm time
		System.out.println("\nAlgorithm Elapsed Time: "
				+ TimeConverter.convertTimeToString(estimatedTime) + "\n");		
	}
}
