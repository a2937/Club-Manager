package org.nerdynerd.clubs;

import com.sun.istack.internal.Nullable;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * A data structure used to represent the
 * concept of a club president.
 */
public class ClubPresident
{

    private int id;

    private String firstName;

    private char middleInitial;

    private String lastName;

    private Date startDate;

    private Date endDate;

    /**
     * Instantiates a new Club president
     * with the start date being today.
     *
     * @param id            the id
     * @param firstName     the first name
     * @param middleInitial the middle initial
     * @param lastName      the last name
     */
    public ClubPresident(int id, String firstName, char middleInitial, String lastName)
    {
        setId(id);
        setFirstName(firstName);
        setMiddleInitial(middleInitial);
        setLastName(lastName);
        setStartDate(Calendar.getInstance().getTime());
    }

    /**
     * Instantiates a new Club president
     * with a start date and nullable end date.
     *
     * @param id            the id
     * @param firstName     the first name
     * @param middleInitial the middle initial
     * @param lastName      the last name
     * @param startDate     the start date
     * @param endDate       the end date
     */
    public ClubPresident(int id, String firstName, char middleInitial, String lastName, Date startDate, @Nullable  Date endDate)
    {
        setId(id);
        setFirstName(firstName);
        setMiddleInitial(middleInitial);
        setLastName(lastName);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    /**
     * Gets the id of the club president.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the club president id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the start date of when they were the club president.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of their term as club president.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the date when their term as club president ended.
     *
     * @return the end date
     */
    @Nullable
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * Sets the end date when their term as club president ended.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * Gets the club president's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the club president's last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the club president's middle initial.
     *
     * @return the middle initial
     */
    public char getMiddleInitial() {
        return middleInitial;
    }

    /**
     * Sets the club president's middle initial.
     *
     * @param middleInitial the middle initial
     */
    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    /**
     * Gets the club president's first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the club president's first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString()
    {
        return lastName + "," + firstName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ClubPresident)) return false;
        ClubPresident that = (ClubPresident) o;
        return getId() == that.getId() &&
                getMiddleInitial() == that.getMiddleInitial() &&
                getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getMiddleInitial(), getLastName(), getStartDate(), getEndDate());
    }
}
