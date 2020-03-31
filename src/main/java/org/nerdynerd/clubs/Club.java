package org.nerdynerd.clubs;

import java.util.Objects;

/**
 * A data structure used to represent the
 * concept of a club.
 */
public class Club
{
    private int id;

    private int memberNumber = 0;

    private String clubName;

    private ClubPresident clubPresident;

    /**
     * Instantiates a new Club.
     */
    public Club()
    {
        id = 0;
        memberNumber = 0;
        clubName = "";
    }

    /**
     * Instantiates a new Club.
     *
     * @param id       the id
     * @param clubName the club name
     */
    public Club(int id, String clubName)
    {
        setId(id);
        setClubName(clubName);
    }

    /**
     * Instantiates a new Club.
     *
     * @param id            the id
     * @param clubName      the club name
     * @param clubPresident the club ClubPresident
     * @param memberNumber  amount of members in the club
     */
    public Club(int id, String clubName, ClubPresident clubPresident, int memberNumber)
    {
        setId(id);
        setClubName(clubName);
        setClubPresident(clubPresident);
        setMemberNumber(memberNumber);
    }

    /**
     * Gets the club name.
     *
     * @return the club name
     */
    public String getClubName()
    {
        return clubName;
    }

    /**
     * Sets the club name.
     *
     * @param clubName the club name
     */
    public void setClubName(String clubName)
    {
        this.clubName = clubName;
    }

    /**
     * Gets the id of the club.
     *
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the id of the club.
     *
     * @param id the id
     */
    public void setId(int id)
    {
        this.id = id;
    }


    /**
     * Gets member number.
     *
     * @return the member number
     */
    public int getMemberNumber() {
        return memberNumber;
    }

    /**
     * Sets the member count.
     *
     * @param memberNumber the member count
     */
    public void setMemberNumber(int memberNumber) throws IllegalArgumentException
    {
        if(memberNumber <= 0)
        {
            throw new IllegalArgumentException("IllegalArgumentException on Club.setMemberNumber: " + " member count must be greater than or equal to zero.");
        }
        this.memberNumber = memberNumber;
    }

    /**
     * Gets the club president.
     *
     * @return the club president
     */
    public ClubPresident getClubPresident() {
        return clubPresident;
    }

    /**
     * Sets the club president.
     *
     * @param clubPresident the club president
     */
    public void setClubPresident(ClubPresident clubPresident) {
        this.clubPresident = clubPresident;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Club)) return false;
        Club club = (Club) o;
        return getId() == club.getId() &&
                getMemberNumber() == club.getMemberNumber() &&
                getClubName().equals(club.getClubName());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getMemberNumber(), getClubName());
    }

    public String toString()
    {
        String stringBuilder = "Club name: " +
                clubName + "\n" +
                "Club president: " +
                clubPresident + "\n" +
                "Member count: " +
                memberNumber;
        return stringBuilder;
    }
}
