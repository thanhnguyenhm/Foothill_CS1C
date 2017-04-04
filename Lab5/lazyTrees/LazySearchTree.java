package lazyTrees;

import java.util.*;

/** 
 * Implementation of a Binary Search Tree
 * Including FHsearch_tree as an inner class
 * Enabling Lazy Deletion
 * @author Foothill College, Thanh Nguyen
 *
 */
public class LazySearchTree<E extends Comparable< ? super E > >
implements Cloneable
{
   protected int mSize;
   protected LazySTNode mRoot;
   protected int mSizeHard;

   /**
    * Constructs a LazySearchTree object
    */
   public LazySearchTree() 
   { 
      clear(); 
   }

   /**
    * check for empty tree
    * @return true for empty tree
    */
   public boolean empty() 
   { 
      return (mSize == 0); 
   }

   /**
    * Accessor method returns soft size of tree
    * @return a int size
    */
   public int size() 
   { 
      return mSize; 
   }
   
   /**
    * Accessor method returns hard size of tree
    * @return a int size
    */
   public int sizeHard()
   {
      return mSizeHard;
   }

   /**
    * Clear the tree by setting default values to member data
    */
   public void clear() 
   { 
      mSize = 0;
      mSizeHard = 0;
      mRoot = null; 
   }

   /**
    * show height of the tree
    * @return height as an int
    */
   public int showHeight() 
   { 
      return findHeight(mRoot, -1); 
   }

   /**
    * call private method to find the smallest value of a tree (Soft)
    * @return data
    */
   public E findMin() 
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMin(mRoot).data;
   }
   
   /**
    * call private method to find the smallest value of a tree (Hard)
    * @return data
    */
   public E findMinHard() 
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMinHard(mRoot).data;
   }

   /**
    * call private method to find the largest value of a tree (Hard)
    * @return data
    */
   public E findMaxHard() 
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMaxHard(mRoot).data;
   }
   
   /**
    * call private method to find the largest value of a tree (Soft)
    * @return data
    */
   public E findMax() 
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMax(mRoot).data;
   }

   /**
    * call private method to search for a data in the tree (Soft)
    * @param x
    * @return data
    */
   public E find( E x )
   {
      LazySTNode resultNode;
      resultNode = find(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }
   
   /**
    * call private method to search for a data in the tree (Hard)
    * @param x
    * @return data
    */
   public E findHard( E x )
   {
      LazySTNode resultNode;
      resultNode = findHard(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }
   
   /**
    * call find() to search for a data in the tree (Soft)
    * @param x
    * @return true if the tree contains x
    */
   public boolean contains(E x)  
   { return find(mRoot, x) != null; }
   
   /**
    * call find() to search for a data in the tree (Hard)
    * @param x
    * @return true if the tree contains x
    */
   public boolean containsHard(E x)
   {
      return findHard(mRoot, x) != null;
   }

   /**
    * call the private insert() (Soft)
    * @param x
    * @return true if insert successfully
    */
   public boolean insert( E x )
   {
      int oldSize = mSize;
      mRoot = insert(mRoot, x);
      return (mSize != oldSize);
   }

   /**
    * call the private remove() (Soft)
    * @param x
    * @return true if remove successfully
    */
   boolean remove( E x )
   {
      int oldSize = mSize;
      remove(mRoot, x);
      return (mSize != oldSize);
   }
   
   /**
    * call the private remove() (Hard)
    * @param x
    * @return true if remove successfully
    */
   public boolean removeHard( E x )
   {
      int oldSize = mSize;
      mRoot = removeHard(mRoot, x);
      return (mSize != oldSize);
   }

   /**
    * traverser (Hard)
    * @param f
    */
   public < F extends Traverser<? super E > > 
   void traverseHard(F func)
   {
      traverseHard(func, mRoot);
   }
   
   /**
    * traverser (Soft)
    * @param f
    */
   public < F extends Traverser<? super E > > 
   void traverseSoft(F func)
   {
      traverseSoft(func, mRoot);
   }

   /**
    * clone
    */
   public Object clone() throws CloneNotSupportedException
   {
      LazySearchTree<E> newObject = (LazySearchTree<E>)super.clone();
      newObject.clear();  // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;

      return newObject;
   }

   // private helper methods ----------------------------------------
   
   /**
    * find the smallest value (soft)
    * @param a LazySTNode object
    * @return a LazySTNode object
    */
   protected LazySTNode findMin( LazySTNode root ) 
   {
      if (root == null)
         return null;
      LazySTNode temp = findMin(root.lftChild);
      if(temp != null)
         return temp;
      if(!root.deleted)
         return root;
      
      return findMin(root.rtChild);
   }

   /**
    * find the largest value (soft)
    * @param a LazySTNode object
    * @return a LazySTNode object
    */
   protected LazySTNode findMax( LazySTNode root ) 
   {
      if (root == null)
         return null;
      LazySTNode temp = findMax(root.rtChild);
      if(temp != null)
         return temp;
      if(!root.deleted)
         return root;
      
      return findMax(root.lftChild);
   }
   
   /**
    * find the smallest value (hard)
    * @param a LazySTNode object
    * @return a LazySTNode object
    */
   protected LazySTNode findMinHard( LazySTNode root ) 
   {
      if (root == null)
         return null;
      if (root.lftChild == null)
         return root;
      return findMinHard(root.lftChild);
   }

   /**
    * find the largest value (hard)
    * @param a LazySTNode object
    * @return a LazySTNode object
    */
   protected LazySTNode findMaxHard( LazySTNode root ) 
   {
      if (root == null)
         return null;
      if (root.rtChild == null)
         return root;
      return findMaxHard(root.rtChild);
   }

   /**
    * insert data to the tree
    * @param a LazySTNode object, data x
    * @return a LazySTNode object
    */
   protected LazySTNode insert( LazySTNode root, E x )
   {
      int compareResult;  // avoid multiple calls to compareTo()

      if (root == null)
      {
         mSize++;
         mSizeHard++;
         return new LazySTNode(x, null, null);
      }

      compareResult = x.compareTo(root.data); 
      if ( compareResult < 0 )
         root.lftChild = insert(root.lftChild, x);
      else if ( compareResult > 0 )
         root.rtChild = insert(root.rtChild, x);
      else if(root.deleted)
      {
         root.deleted = false;
         mSize++;
      }

      return root;
   }
   
   /**
    * remove data from the tree (Hard)
    * @param a LazySTNode object, data x
    * @return a LazySTNode object
    */
   protected LazySTNode removeHard( LazySTNode root, E x  )
   {
      int compareResult;  // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data); 
      if ( compareResult < 0 )
         root.lftChild = removeHard(root.lftChild, x);
      else if ( compareResult > 0 )
         root.rtChild = removeHard(root.rtChild, x);

      // found the node
      else if (root.lftChild != null && root.rtChild != null)
      {
         root.data = findMin(root.rtChild).data;
         root.rtChild = removeHard(root.rtChild, root.data);
      }
      else
      {
         root =
               (root.lftChild != null)? root.lftChild : root.rtChild;
         mSizeHard--;
      }
      return root;
   }

   /**
    * remove data from the tree (soft)
    * @param a LazySTNode object, data x
    */
   protected void remove( LazySTNode root, E x  )
   {
      if(root == null)
         return;
      
      LazySTNode temp = new LazySTNode();
      temp = find(root, x);
      
      if(temp != null)//if found
      {
         temp.deleted = true;
         mSize--;
      }
   }


   protected <F extends Traverser<? super E>> 
   void traverseHard(F func, LazySTNode treeNode)
   {
      if (treeNode == null)
         return;

      traverseHard(func, treeNode.lftChild);
      func.visit(treeNode.data);
      traverseHard(func, treeNode.rtChild);
   }

   protected <F extends Traverser<? super E>> 
   void traverseSoft(F func, LazySTNode treeNode)
   {
      if (treeNode == null)
         return;

      traverseSoft(func, treeNode.lftChild);
      if(!treeNode.deleted)
         func.visit(treeNode.data);
      traverseSoft(func, treeNode.rtChild);
   }
   
   /**
    * search for data in the tree (Soft)
    * @param a LazySTNode object, data x
    * @return a LazySTNode object
    */
   protected LazySTNode find( LazySTNode root, E x )
   {
      int compareResult;  // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data); 
      if (compareResult < 0)
         return find(root.lftChild, x);
      if (compareResult > 0)
         return find(root.rtChild, x);
      if (!root.deleted)
         return root; // found
      
      return null;   // found
   }
   
   /**
    * search for data in the tree (Hard)
    * @param a LazySTNode object, data x
    * @return a LazySTNode object
    */
   protected LazySTNode findHard( LazySTNode root, E x )
   {
      int compareResult;  // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data); 
      if (compareResult < 0)
         return find(root.lftChild, x);
      if (compareResult > 0)
         return find(root.rtChild, x);
      return root;   // found
   }

   protected LazySTNode cloneSubtree(LazySTNode root)
   {
      LazySTNode newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new LazySTNode
      (
            root.data, 
            cloneSubtree(root.lftChild), 
            cloneSubtree(root.rtChild)
            );
      return newNode;
   }

   /**
    * find the height of the tree
    * @param a LazySTNode object, data x
    * @return a int height
    */
   protected int findHeight( LazySTNode treeNode, int height ) 
   {
      int leftHeight, rightHeight;
      if (treeNode == null)
         return height;
      height++;
      leftHeight = findHeight(treeNode.lftChild, height);
      rightHeight = findHeight(treeNode.rtChild, height);
      return (leftHeight > rightHeight)? leftHeight : rightHeight;
   }
   
   //inner class LazySTNode------------------------------------
   private class LazySTNode
   {
      // use public access so the tree or other classes can access members 
      public LazySTNode lftChild, rtChild;
      public E data;
      public LazySTNode myRoot;  // needed to test for certain error
      public boolean deleted;

      public LazySTNode( E d, LazySTNode lft, LazySTNode rt )
      {
         lftChild = lft; 
         rtChild = rt;
         data = d;
         deleted = false;
      }
      
      public LazySTNode()
      {
         this(null, null, null);
      }
   }//end LazyStNode--------------------------------------------

}//end LazySearchTree

interface Traverser<E>
{
   public void visit(E x);
}

class PrintObject<E> implements Traverser<E>
{
   public void visit(E x)
   {
      System.out.print( x + " ");
   }
};