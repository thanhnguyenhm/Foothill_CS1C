package hashTables;

import cs1c.SongEntry;

/**
 * Wrapper class for a SongEntry object
 * to compares objects based on the songs int id field.
 * @author Thanh Nguyen
 */
public class SongCompInt implements Comparable<Integer>
{
   //Contains a single SongEntry object as the attribute.
   private SongEntry songEntryObj;

 /**
  * constructor
  * @param song
  */
   public SongCompInt(SongEntry song)
   {
      songEntryObj = song;
   }

/**
 * override toString()
 */
   public String toString() 
   { 
      return songEntryObj.toString();
   }  
   
/**
 *override compareTo
 */
   public int compareTo(Integer key)
   {
      return songEntryObj.getID() - key;
   }
   
/**
 * override equals()
 */
   public boolean equals(SongCompInt rhs ) 
   {
      return songEntryObj.equals(rhs.songEntryObj);
   }
   
/**
 * override hashCode()
 */
   public int hashCode()
   { 
      return songEntryObj.getID();
   } 
}
