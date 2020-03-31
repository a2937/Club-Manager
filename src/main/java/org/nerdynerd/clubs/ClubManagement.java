package org.nerdynerd.clubs;

import com.sun.istack.internal.Nullable;
import org.nerdynerd.clubs.comparator.ClubNameComparator;
import org.nerdynerd.clubs.comparator.CurrentPresidentComparator;
import org.nerdynerd.clubs.comparator.MemberNumberComparator;

import java.util.*;

/**
 * A component used to store, manage, and query a list of clubs.
 */
public class ClubManagement extends AbstractList<Club>
{
    private int numberOfClubs;

    private int maxSize;

    private Club[] clubList;

    private MemberNumberComparator memberNumberComparator;

    private CurrentPresidentComparator clubPresidentComparator;

    private ClubNameComparator clubNameComparator;

    /**
     * Instantiates a new Club management.
     *
     * @param maxSize the max size
     */
    public ClubManagement(int maxSize)
    {
        setMaxSize(maxSize);
        numberOfClubs = 0;
        clubList = new Club[maxSize];
        memberNumberComparator = new MemberNumberComparator();
        clubNameComparator = new ClubNameComparator();
        clubPresidentComparator = new CurrentPresidentComparator();
    }

    /**
     * Returns the maximum number of clubs allowed to be
     * stored in this instance.
     *
     * @return the max size
     */
    public int getMaxSize() {
        return maxSize;
    }

    private void setMaxSize(int maxSize) throws IllegalArgumentException
    {
        if(maxSize < 0)
        {
            throw new IllegalArgumentException("IllegalArgumentException on ClubManagement.setMaxSize; maxSize must be greater than zero.");
        }
        this.maxSize = maxSize;
    }

    /**
     * Returns if the given club is managed by this instance.
     *
     * @param clubName the club name
     * @return true if the club is found in the system, else false.
     */
    public boolean clubExists(String clubName)
    {
        return findClub(clubName) != null;
    }

    /**
     * Determines if the provided club president is
     * in charge of a stored club.
     *
     * @param clubName      the club name
     * @param clubPresident the club president
     * @return true if the club president is in charge of a stored club. False otherwise.
     */
    public boolean currentPresidentExists(String clubName, ClubPresident clubPresident)
    {
        Club club = findClub(clubName);
        if(club != null)
        {
            return club.getClubPresident().equals(clubPresident);
        }
        return false;
    }

    @Nullable
    private Club findClub(String clubName)
    {
        if(numberOfClubs == 0 || clubList.length == 0)
        {
            return null;
        }
        for (Club club: clubList)
        {
            if(club == null)
            {
                break;
            }
            if(club.getClubName().trim().equalsIgnoreCase(clubName.trim()))
            {
                return club;
            }
        }
       return null;
    }

    private int findIndex(Club club)
    {
        if(club == null || numberOfClubs <= 0)
        {
            return -1;
        }
       for(int i = 0; i< clubList.length; i++)
       {
           if(clubList[i].equals(club))
           {
               return i;
           }
       }
       return -1;
    }

    private boolean addClub(Club club)
    {
        if (findClub(club.getClubName()) == null)
        {
            if (numberOfClubs < maxSize)
            {
                clubList[numberOfClubs] = club;
                numberOfClubs++;
                return true;
            }
            else
            {
                throw new IllegalStateException("IllegalStateException on ClubManagement.add : " + " not enough room for new element." );
            }
        }
        return false;
    }

    private boolean removeClub(String clubName)
    {
        Club club = findClub(clubName);
        if(club != null)
        {
            int index = findIndex(club);
            clubList[index] = null;
            numberOfClubs--;
            return true;
        }
        return false;
    }

    /**
     * Returns the number of clubs that are currently
     * registered.
     *
     * @return the number of clubs
     */
    public int getNumberOfClubs()
    {
        return numberOfClubs;
    }

    /**
     * Sort  all club entries in ascending by the number of members.
     */
    public void sortByMemberNumbers()
    {
        Arrays.sort(clubList,memberNumberComparator);
    }

    /**
     * Sorts all club entries in descending by the names of the clubs.
     */
    public void sortByClubNames()
    {
        Arrays.sort(clubList,clubNameComparator);
    }

    /**
     * Sorts all club entries in descending order by the last names of the club presidents.
     */
    public void sortByClubPresidents()
    {
        Arrays.sort(clubList,clubPresidentComparator);
    }

    /**
     * Returns a string representing the clubs
     * stored in this object.
     *
     * @return a string representing the list of clubs.
     */
    public String listClubs()
    {
        return toString();
    }

    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clubList[0]);
        for (int i = 1; i < maxSize - 1; i++)
        {
            if(clubList[i] == null)
            {
                break;
            }
            stringBuilder.append("\n").append(clubList[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * Closes the link to the underlying data storage.
     */
    public void closeClubManagement()
    {
        throw new UnsupportedOperationException("Current function closeClubManagement is not available. Please try again in another version.");
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<Club> iterator()
    {
        return new ClubIterator(this);
    }

    /**
     * Returns the number of elements in this collection.  If this collection
     * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this collection
     */
    @Override
    public int size()
    {
        return numberOfClubs;
    }

    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
    @Override
    public boolean isEmpty()
    {
        return numberOfClubs == 0;
    }

    /**
     * Returns <tt>true</tt> if this collection contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this collection
     * contains at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this collection is to be tested
     * @return <tt>true</tt> if this collection contains the specified
     * element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this collection
     *                              (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              collection does not permit null elements
     *                              (<a href="#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object o)
    {
        if(o instanceof Club)
        {
            Club club = (Club)o;
            return clubExists(club.getClubName());
        }
        else
        {
            return false;
        }
    }



    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this collection
     */
    @Override
    public Object[] toArray()
    {
        return Arrays.copyOf(clubList,maxSize);
    }


    /**
     * Ensures that this collection contains the specified element
     * Returns <tt>true</tt> if this collection changed as a
     * result of the call.  (Returns <tt>false</tt> if this collection does
     * not permit duplicates and already contains the specified element.)<p>
     * <p>
     * Collections that support this operation may place limitations on what
     * elements may be added to this collection.  In particular, some
     * collections will refuse to add <tt>null</tt> elements, and others will
     * impose restrictions on the type of elements that may be added.
     * Collection classes should clearly specify in their documentation any
     * restrictions on what elements may be added.<p>
     * <p>
     * If a collection refuses to add a particular element for any reason
     * other than that it already contains the element, it <i>must</i> throw
     * an exception (rather than returning <tt>false</tt>).  This preserves
     * the invariant that a collection always contains the specified element
     * after this call returns.
     *
     * @param club element whose presence in this collection is to be ensured
     * @return <tt>true</tt> if this collection changed as a result of the
     * call
     * @throws UnsupportedOperationException if the <tt>add</tt> operation
     *                                       is not supported by this collection
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this collection
     * @throws NullPointerException          if the specified element is null and this
     *                                       collection does not permit null elements
     * @throws IllegalArgumentException      if some property of the element
     *                                       prevents it from being added to this collection
     * @throws IllegalStateException         if the element cannot be added at this
     *                                       time due to insertion restrictions
     */
    @Override
    public boolean add(Club club)
    {
        return addClub(club);
    }

    /**
     * {@inheritDoc}
     *
     * @param index
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Club get(int index)
    {
        if(index >= maxSize)
        {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException on ClubManagement.get: " + "index of " + index + " greater than array size.");
        }
        return clubList[index];
    }

    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present (optional operation).  More formally,
     * removes an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
     * this collection contains one or more such elements.  Returns
     * <tt>true</tt> if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     *
     * @param o element to be removed from this collection, if present
     * @return <tt>true</tt> if an element was removed as a result of this call
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this collection
     *                                       (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       collection does not permit null elements
     *                                       (<a href="#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this collection
     */
    @Override
    public boolean remove(Object o)
    {
        if(o instanceof Club)
        {
            Club club = (Club)o;
            return removeClub(club.getClubName());
        }
        else
        {
            throw new ClassCastException("ClassCastException on ClubManagement.remove " + "Cannot convert object o to a club");
        }
    }

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements
     * in the specified collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return <tt>true</tt> if this collection contains all of the elements
     * in the specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              collection
     *                              (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this collection does not permit null
     *                              elements
     *                              (<a href="#optional-restrictions">optional</a>),
     *                              or if the specified collection is null.
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c)
    {
        for (Object o: c)
        {
            if(o instanceof Club)
            {
                Club club = (Club)o;
                if(!clubExists(club.getClubName()))
                {
                    return false;
                }
            }
            else
            {
                throw new ClassCastException("ClassCastException on ClubManagement.containsAll: " + " one or more elements cannot be converted to type club");
            }
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.)
     *
     * @param c collection containing elements to be added to this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *                                       is not supported by this collection
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this collection
     * @throws NullPointerException          if the specified collection contains a
     *                                       null element and this collection does not permit null elements,
     *                                       or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this
     *                                       collection
     * @throws IllegalStateException         if not all the elements can be added at
     *                                       this time due to insertion restrictions
     */
    @Override
    public boolean addAll(Collection<? extends Club> c)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection (optional operation).  After this call returns,
     * this collection will contain no elements in common with the specified
     * collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return <tt>true</tt> if this collection changed as a result of the
     * call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> method
     *                                       is not supported by this collection
     * @throws ClassCastException            if the types of one or more elements
     *                                       in this collection are incompatible with the specified
     *                                       collection
     *                                       (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this collection contains one or more
     *                                       null elements and the specified collection does not support
     *                                       null elements
     *                                       (<a href="#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).  In other words, removes from
     * this collection all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *                                       is not supported by this collection
     * @throws ClassCastException            if the types of one or more elements
     *                                       in this collection are incompatible with the specified
     *                                       collection
     *                                       (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this collection contains one or more
     *                                       null elements and the specified collection does not permit null
     *                                       elements
     *                                       (<a href="#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this collection
     */
    @Override
    public void clear()
    {
        numberOfClubs = 0;
        clubList = new Club[maxSize];
    }
}
