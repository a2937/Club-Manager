package org.nerdynerd.clubs;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * An iterator for the type ClubManagement.
 * @see java.util.Iterator
 */
public class ClubIterator implements Iterator<Club>
{
    private ClubManagement clubs;

    private Club current;

    private int index;

    /**
     * Instantiates a new Club iterator.
     *
     * @param clubs the clubs
     */
    public ClubIterator(ClubManagement clubs)
    {
        this.clubs = clubs;
        index = 0;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext()
    {
        return index < clubs.size();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Club next()
    {
        if(index < clubs.getMaxSize() || clubs.get(index) == null)
        {
            throw new NoSuchElementException("NoSuchElementException on ClubIterator.next: " + " no more elements in this iteration.");
        }
        current = clubs.get(index);
        index++;
        return current;
    }

}
