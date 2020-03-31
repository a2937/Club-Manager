package org.nerdynerd.clubs;

import org.junit.Assert;
import org.junit.Test;


/**
 * Integration tests for the club management system.
 */
public class ClubManagementTests
{
    /**
     * Asserts that a newly added club should be found by the data structure.
     */
    @Test
    public void ShouldFindNewlyAddedClub()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Club club = new Club(1,"Sample");
        boolean addedClubSuccessfully = clubManagement.add(club);
        Assert.assertTrue(addedClubSuccessfully);
        Assert.assertTrue(clubManagement.clubExists(club.getClubName()));
    }

    /**
     * Asserts that the maximum size of the club management system is actually set
     */
    @Test
    public void ShouldGetMaxSizeAfterSetting()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Assert.assertEquals(5, clubManagement.getMaxSize());
    }


    /**
     * Asserts that the stored number of clubs begins at zero and increased
     * by one each time a club is added.
     */
    @Test
    public void ShouldIncreaseClubCountAfterAddingClub()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Assert.assertEquals(0, clubManagement.getNumberOfClubs());
        Club club = new Club(1,"Sample");
        clubManagement.add(club);
        Assert.assertEquals(1, clubManagement.getNumberOfClubs());
    }

    /**
     * Asserts that an un-named club should be able to added
     * to the club management system.
     */
    @Test
    public void ShouldFindUnnamedClub()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Club club = new Club();
        boolean addedClubSuccessfully = clubManagement.add(club);
        Assert.assertTrue(addedClubSuccessfully);
        Assert.assertTrue(clubManagement.clubExists(club.getClubName()));
    }

    /**
     * Asserts that two clubs with the exact same name
     * should not be added to the same club management system.
     */
    @Test
    public void ShouldNotAddTwoClubsWithSameName()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Club club1 = new Club(1,"Sample");
        Club club2 = new Club(1,"Sample");
        boolean addedClubOneSuccessfully = clubManagement.add(club1);
        Assert.assertTrue(addedClubOneSuccessfully);
        Assert.assertTrue(clubManagement.clubExists(club1.getClubName()));
        boolean addedClubTwoSuccessfully = clubManagement.add(club2);
        Assert.assertFalse(addedClubTwoSuccessfully);
    }

    /**
     * Asserts that more than the maximum capacity of clubs
     * will not be added to the club management data structure.
     */
    @Test
    public void ShouldNotAddMoreClubsThanAllowed()
    {
        ClubManagement clubManagement = new ClubManagement(1);
        Club club1 = new Club(1,"Sample");
        Club club2 = new Club(2,"Other");
        boolean addedClubOneSuccessfully = clubManagement.add(club1);
        Assert.assertTrue(addedClubOneSuccessfully);
        Assert.assertTrue(clubManagement.clubExists(club1.getClubName()));
        boolean addedClubTwoSuccessfully = clubManagement.add(club2);
        Assert.assertFalse(addedClubTwoSuccessfully);
    }

    /**
     * Asserts that even if two clubs have the exact same name
     * with different cases only one will be added to the system.
     */
    @Test
    public void ShouldNotAddTwoClubsWithSameNameDifferentCase()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Club club1 = new Club(1,"sAmPlE");
        Club club2 = new Club(1,"SaMpLe");
        boolean addedClubOneSuccessfully = clubManagement.add(club1);
        Assert.assertTrue(addedClubOneSuccessfully);
        Assert.assertTrue(clubManagement.clubExists(club1.getClubName()));
        boolean addedClubTwoSuccessfully = clubManagement.add(club2);
        Assert.assertFalse(addedClubTwoSuccessfully);
    }

    /**
     * Asserts that newly added items are able to be removed
     * successfully.
     */
    @Test
    public void ShouldRemoveNewlyAddedItemSuccessfully()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Club club1 = new Club(1,"sAmPlE");
        clubManagement.add(club1);
        boolean removedClubSuccessfully = clubManagement.remove(club1);
        Assert.assertTrue(removedClubSuccessfully);
    }

    /**
     * Asserts that the number of stored items decreases
     * each time an item is removed.
     */
    @Test
    public void ShouldDecreaseCountAfterRemovingItem()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Assert.assertEquals(0,clubManagement.getNumberOfClubs());
        Club club1 = new Club(1,"sAmPlE");
        clubManagement.add(club1);
        Assert.assertEquals(1,clubManagement.getNumberOfClubs());
        clubManagement.remove(club1);
        Assert.assertEquals(0,clubManagement.getNumberOfClubs());
    }


    /**
     * Asserts that clubs should be in descending order by club name
     * when sorted that way.
     */
    @Test
    public void ShouldBeInDescendingOrderByClubName()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Assert.assertEquals(0,clubManagement.getNumberOfClubs());
        Club club1 = new Club(1,"Doki Doki Literature Club");
        clubManagement.add(club1);
        Club club2 = new Club(2,"Videogame club");
        clubManagement.add(club2);
        Club club3 = new Club(3,"Cooking club");
        clubManagement.add(club3);
        Club club4 = new Club(4,"Martial Arts Club");
        clubManagement.add(club4);
        Club club5 = new Club(5,"Computer Club");
        clubManagement.add(club5);
        clubManagement.sortByClubNames();
        Assert.assertEquals(club5,clubManagement.get(0));
        Assert.assertEquals(club3,clubManagement.get(1));
        Assert.assertEquals(club1,clubManagement.get(2));
        Assert.assertEquals(club4,clubManagement.get(3));
        Assert.assertEquals(club2,clubManagement.get(4));
    }

    /**
     * Asserts that clubs should be in ascending order by member count
     * when sorted that way.
     */
    @Test
    public void ShouldBeInAscendingOrderByMemberCount()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Assert.assertEquals(0,clubManagement.getNumberOfClubs());
        ClubPresident president1 = new ClubPresident(1,"Just",'F',"Monika");
        Club club1 = new Club(1,"Doki Doki Literature Club",president1,4);
        clubManagement.add(club1);
        ClubPresident president2 = new ClubPresident(2,"Rebecca",'U',"Johnson");
        Club club2 = new Club(2,"Videogame club",president2,6);
        clubManagement.add(club2);
        ClubPresident president3 = new ClubPresident(3,"Isabella",'C',"Garland");
        Club club3 = new Club(3,"Cooking club",president3,12);
        clubManagement.add(club3);
        ClubPresident president4 = new ClubPresident(4,"Shirley",'U',"Jest");
        Club club4 = new Club(4,"Martial Arts Club",president4,10);
        clubManagement.add(club4);
        ClubPresident president5 = new ClubPresident(5,"Zeke",'N',"Yeshallfind");
        Club club5 = new Club(5,"Computer Club",president5,3);
        clubManagement.add(club5);
        clubManagement.sortByMemberNumbers();
        Assert.assertEquals(club5,clubManagement.get(0));
        Assert.assertEquals(club1,clubManagement.get(1));
        Assert.assertEquals(club2,clubManagement.get(2));
        Assert.assertEquals(club4,clubManagement.get(3));
        Assert.assertEquals(club3,clubManagement.get(4));
    }

    /**
     * Asserts that clubs should be in descending order by club president
     * when sorted that way.
     */
    @Test
    public void ShouldBeInDescendingOrderByClubPresident()
    {
        ClubManagement clubManagement = new ClubManagement(5);
        Assert.assertEquals(0,clubManagement.getNumberOfClubs());
        ClubPresident president1 = new ClubPresident(1,"Just",'F',"Monika");
        Club club1 = new Club(1,"Doki Doki Literature Club",president1,4);
        clubManagement.add(club1);
        ClubPresident president2 = new ClubPresident(2,"Rebecca",'U',"Johnson");
        Club club2 = new Club(2,"Videogame club",president2,6);
        clubManagement.add(club2);
        ClubPresident president3 = new ClubPresident(3,"Isabella",'C',"Garland");
        Club club3 = new Club(3,"Cooking club",president3,12);
        clubManagement.add(club3);
        ClubPresident president4 = new ClubPresident(4,"Shirley",'U',"Jest");
        Club club4 = new Club(4,"Martial Arts Club",president4,10);
        clubManagement.add(club4);
        ClubPresident president5 = new ClubPresident(5,"Zeke",'N',"Yeshallfind");
        Club club5 = new Club(5,"Computer Club",president5,3);
        clubManagement.add(club5);
        clubManagement.sortByClubPresidents();
        Assert.assertEquals(club3,clubManagement.get(0));
        Assert.assertEquals(club4,clubManagement.get(1));
        Assert.assertEquals(club2,clubManagement.get(2));
        Assert.assertEquals(club1,clubManagement.get(3));
        Assert.assertEquals(club5,clubManagement.get(4));
    }
}
