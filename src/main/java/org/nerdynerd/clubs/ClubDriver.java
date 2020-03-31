package org.nerdynerd.clubs;

import java.util.Scanner;

/**
 * The driver for this application.
 */
public class ClubDriver
{
    private static boolean finished = false;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome to the club management program. \n" +

                " Press 1 to add a club. \n Press 2 to remove a club. \n Press 3 to list all clubs. \n Press 4 to quit.");
        Scanner scanner = new Scanner(System.in);
        ClubManagement clubManagement = new ClubManagement(30);
        String clubName;
        Club club;
        while(!finished)
        {
            int choice = scanner.nextInt();

            try
            {
                switch (choice)
                {

                    case 1:
                        if(clubManagement.getMaxSize() == clubManagement.size())
                        {
                            System.out.println("I'm afraid I can't let you do that. The list is full. :(");
                            break;
                        }
                        System.out.println("What is the name of the club you want to add?");
                        scanner.nextLine();
                        clubName = scanner.nextLine();
                        club = new Club(clubManagement.size(), clubName);
                        boolean addedClub = clubManagement.add(club);
                        if (addedClub)
                        {
                            System.out.println("Successfully added the " + clubName + " club");
                            break;
                        }
                        else
                        {
                            System.out.println("Something went wrong adding the club. Please try again later.");
                        }
                    case 2:
                        System.out.println("What is the name of the club you want to remove?");
                        scanner.nextLine();
                        clubName = scanner.nextLine();
                        club = new Club(clubManagement.size(), clubName);
                        boolean removedClub = clubManagement.remove(club);
                        if (removedClub)
                        {
                            System.out.println("Successfully removed the " + clubName + " club");
                            break;
                        }
                        else
                        {
                            System.out.println("Something went wrong removing the club. Please try again later.");
                        }
                    case 3:
                        System.out.println("Printing all clubs");
                        System.out.println(clubManagement.toString());
                        break;
                    case 4:
                        finished = true;
                        System.out.println("Shutting down the program. Have a nice day.");
                        break;
                    default:
                        System.out.println("Please try again. Input not recognized.");


                }

            }
            catch(Exception ex)
            {
                System.err.println("Logging exception");
            }
            System.out.println("Welcome to the club management program. " +
                    " \n Press 1 to add a club. \n Press 2 to remove a club.  \nPress 3 to list all clubs. \nPress 4 to quit.");
        }
        System.exit(0);

    }

}
