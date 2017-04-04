package hashTables;

import java.util.ArrayList;

import cs1c.SongEntry;

/**
 * This class will create and 
 * populate two hash tables of type FHhashQPwFind class, one for each wrapper class
 * @author Thanh Nguyen
 */
public class TableGenerator
{
   private FHhashQPwFind<Integer, SongCompInt>  tableOfSongIDs = 
         new FHhashQPwFind<Integer, SongCompInt>();
   private FHhashQPwFind<String, SongsCompGenre> tableOfGenres = 
         new FHhashQPwFind<String, SongsCompGenre>();
   private ArrayList<String> genreNames = new ArrayList<String>();
   
   /**
    * this method is to populate a hash table based on SongCompInt
    * @return a FHhashQPwFind object
    */
   public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] allSongs)
   {
    for(int i = 0; i < allSongs.length; i++)
      {
         SongCompInt song = new SongCompInt(allSongs[i]);
         tableOfSongIDs.insert(song);
      }
            
      return tableOfSongIDs;
   }

   /**
    * this method is to populate a hash table based on SongsCompGenre
    * @return a FHhashQPwFind object
    */
   public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs)
   {
      for(int i = 0; i < allSongs.length; i++)
      {        
         try
         {
            SongsCompGenre currentGenre = tableOfGenres.find(allSongs[i].getGenre());
            currentGenre.addSong(allSongs[i]);
         }
         catch (Exception e)
         {
            SongsCompGenre song = new SongsCompGenre(allSongs[i].getGenre(), allSongs[i]);
            tableOfGenres.insert(song);
            genreNames.add(allSongs[i].getGenre());
         }            
      }   
      return tableOfGenres;
   }

   /**
    * accessor of the list of genre names
    * @return an ArrayList<String> object
    */
   public ArrayList<String> getGenreNames()
   {
      return genreNames;
   }
}
