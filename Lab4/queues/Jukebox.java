package queues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs1c.*;

/**  
 * @author Foothill College, Thanh Nguyen
 */

public class Jukebox
{
   private Queue<SongEntry> favoritePL = new Queue<SongEntry>("favorites");
   private Queue<SongEntry> roadTripPL = new Queue<SongEntry>("road trip");
   private Queue<SongEntry> loungePL  = new Queue<SongEntry>("lounge");

   public void fillPlaylists(String requestFile, SongEntry[] allSongs)
   {
      File infile = new File(requestFile);
      try 
      {
         Scanner input = new Scanner(infile);

         String line = "";
         while (input.hasNextLine()) 
         {
            line = input.nextLine(); 
            String [] tokens = line.split(",");

            String songName = tokens[1];
            for(int i = 0; i < allSongs.length; i++) {
              
               if(allSongs[i].getTitle().equals(songName))
               {
                  if(tokens[0].equals("favorites")){
                     favoritePL.enqueue(allSongs[i]);
                     break;}
                  else if(tokens[0].equals("road trip")){
                     roadTripPL.enqueue(allSongs[i]);
                     break;}
                  else if(tokens[0].equals("lounge")) {
                     loungePL.enqueue(allSongs[i]);
                     break;}
               }   
            }
         }     
         
         // closing the input file resource
         input.close();
      } 
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      }
      
   }

   public Queue<SongEntry> getFavoritePL()
   {
      
      return favoritePL;
   }

   public Queue<SongEntry> getLoungePL()
   {
      
      return loungePL;
   }

   public Queue<SongEntry> getRoadTripPL()
   {
      
      return roadTripPL;
   }
}
