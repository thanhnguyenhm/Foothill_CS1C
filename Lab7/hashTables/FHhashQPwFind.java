package hashTables;

import java.util.NoSuchElementException;

/**
 * Implementation of derived class FHhashQPwFind extends FHhashQP
 * @author Foothill College, Thanh Nguyen
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType> >
extends FHhashQP<E>
{
/**
 * A method to find the position based on the key, not on the object
 * @param KeyType key for searching
 * @return the found object, or throws a java.util.NoSuchElementException
 */
   public E find( KeyType key )
   {
      int bucket = findPosKey(key);
      
      if(mArray[bucket].state != ACTIVE)
         throw new NoSuchElementException();
      
      return mArray[bucket].data;
   }
   
/**
 * a protected method,  which provides trivial modification to findPos() 
 * which uses the key rather than the object, to get a position
 * @param KeyType key for searching
 * @return an integer which is the position
 */
   protected int findPosKey( KeyType key)
   {
      int kthOddNum = 1;
      int index = myHashKey(key);

      while ( mArray[index].state != EMPTY
         && (mArray[index].data.compareTo(key) != 0) )
      {
         index += kthOddNum; // k squared = (k-1) squared + kth odd #
         kthOddNum += 2;     // compute next odd #
         if ( index >= mTableSize )
            index -= mTableSize;
      }
      return index;
   }
   
/**
 * a protected method, which provides a trivial modification to myHash() 
 * which uses the key rather than the object, to hash.
 * @param KeyType key to create hash key
 * @return an integer which is a hash key
 */
   protected int myHashKey( KeyType key)
   {
      int hashVal;

      hashVal = key.hashCode() % mTableSize;
      if(hashVal < 0)
         hashVal += mTableSize;

      return hashVal;
   }
}
