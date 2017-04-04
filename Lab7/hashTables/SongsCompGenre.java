package hashTables;

import java.util.ArrayList;

import cs1c.SongEntry;

/**
 * Wrapper class for a SongEntry object
 * to compares objects based on the songs String genre field
 * to determine the number of songs in each genre
 * @author Thanh Nguyen
 */
public class SongsCompGenre implements Comparable<String>
{
   /**
    * Contains an attribute of type String for the name of the genre.
    */
   private String genre;
   
   /**
    * Contains an attribute of type ArrayList<SongEntry> for all the songs in that genre
    * which is our data.
    */
   
   private ArrayList<SongEntry> songEntryObj;
   
   /*
    * constructors
    */
   public SongsCompGenre()
   {
      songEntryObj = new ArrayList<SongEntry>();
      genre = "undefined";
   }

   /**
    * second constructor
    * @param genre
    * @param currentSong
    */
   public SongsCompGenre(String genre, SongEntry currentSong)
   {
      this();
      this.genre = genre;
      addSong(currentSong);
   }
   
   /**
    * override toString()
    */
   public String toString() 
   { 
      return songEntryObj.toString();
   }  
   
   /**
    * override compareTo
    */
   public int compareTo(String myStr)
   {
      return genre.compareTo(myStr);
   }
   
   /**
    * override equals
    * @param rhs
    * @return
    */
   public boolean equals(SongsCompGenre rhs ) 
   {
      return songEntryObj.equals(rhs.songEntryObj);
   }
   
   /**
    * override hashCode()
    */
   public int hashCode()
   { 
      return genre.hashCode();
   }
   
   /**
    * A method to add a song to the list of songs.
    * @param a SongEntry object
    */
   public void addSong(SongEntry song)
   {
      songEntryObj.add(song);
   }
   
   /**
    * accessor
    * @return String genre
    */
   public String getName() { return genre; }
   
   /**
    * accessor
    * @return an ArrayList object
    */
   public ArrayList<SongEntry> getData() { return songEntryObj; }
}
