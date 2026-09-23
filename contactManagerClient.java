/**
 * Vinay Sam 
 * 04/05/2025
 * This class is the main user interface for the Contacts Manager application, handling user input and interaction with the contactOrganizer
 */
import java.io.*; 
import java.util.Scanner;

public class contactManagerClient 
{
    static Scanner readIn = new Scanner(System.in); // Scanner for user input
    
	public static void main(String[] args) throws IOException 
	{
      Scanner readContacts = new Scanner(new File("CONTACTS")); // Read from CONTACTS file
		int choice = 0;
		contactOrganizer newContacts = new contactOrganizer(); // Create contact organizer object
		
		while (readContacts.hasNext())  // Load existing contacts from file
        {
            String Fn = readContacts.next();
            String Ln = readContacts.next();
            String Pn = readContacts.next();
            String Em = readContacts.next();
            String Br = readContacts.next();

            newContacts.addContact(Fn, Ln, Pn, Em, Br);
        }
        readContacts.close(); // Close the file scanner
        
		do {
			// Display menu
			System.out.println("Welcome to the Contacts Manager App. Please press any number options below to get started:");
			System.out.println("1: Enter Contact Details");
			System.out.println("2: Search for Contacts by Name");
			System.out.println("3: Update Contacts by Name");
			System.out.println("4: Delete Contacts by Name");
			System.out.println("5: Print all Contacts");
			System.out.println("6: Exit the Program");
			choice = readIn.nextInt();
			readIn.nextLine();
			
			if(choice > 6 || choice < 1)
			{
				System.out.println("You pressed the wrong number, please press the choices between 1-6.\n");
			}
			switch(choice)
			{
				case 1: 
					// Add a new contact
					System.out.println("Adding a new Contact");
					System.out.println("Enter the first name of the person you wish to add");
					String firstName = readIn.nextLine();
					System.out.println("Enter the last name of the person you wish to add");
					String lastName = readIn.nextLine();
					
					// Validate phone number format
					String phoneNumber = "";
					while(true)
					{
						System.out.println("Enter the phone number of the new Contact. Please use the '-' symbol after the 3rd and 6th number.");
						phoneNumber = readIn.nextLine();
						if (phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}")) 
				        {
				            break;
				        } 
						else 
						{
				            System.out.println("Invalid phone number. Please enter a 10-digit number and make sure to use the format 123-456-7890.");
				        }
				    }
					
					// Optional email input with validation
					String emailAddress = "";
					while(true)
					{
						System.out.println("Do you wish to enter the email address of the new Contact? Press Enter to skip.");
						emailAddress = readIn.nextLine();
						if (emailAddress.isEmpty()) 
						{
					        emailAddress = "N/A";
					        break;
					    } 
						else if (emailAddress.contains("@")) 
					    {
					        break;
					    } 
						else 
						{
					        System.out.println("Invalid email address. Must contain an '@' symbol.");
					    }
				    }
					
					// Optional birthday input with format check
					String birthDay = "";
					while (true)
					{
						System.out.println("Do you wish to enter their birthday details? If so, type in MM/DD/YY. Press Enter to skip.");
						birthDay = readIn.nextLine();
						
						if(birthDay.isEmpty())
						{
							birthDay = "N/A";
							break;
						}
						else if (birthDay.contains("/"))
						{
							break;
						}
						else 
						{
							System.out.println("Invaid birthday detail. Must contain an '/' symbol.");
						}
					}
					
					// Prevent duplicate contact entry
					if (newContacts.contactExists(firstName, lastName)) 
					{
					    System.out.println("A contact with that name already exists. Please use the update option (Press 3) if you want to change their details.\n");
					} 
					else 
					{
					    newContacts.addContact(firstName, lastName, phoneNumber, emailAddress, birthDay);
					    System.out.println("Successfully added contact\n");
					    contact newContactPrint = new contact(firstName, lastName, phoneNumber, emailAddress, birthDay);
					    System.out.println(newContactPrint);
					}
					break;
					
				case 2: 
					// Search for a contact
					System.out.println("Searching for a Contact");
					System.out.println("Enter the first name of the person you wish to look up");
					String firstNameLookUp = readIn.nextLine();
					System.out.println("Enter the last name of the person you wish to look up");
					String lastNameLookUp = readIn.nextLine();
					newContacts.searchContact(firstNameLookUp, lastNameLookUp);
					break;
				
				case 3: 
					// Update contact details
					System.out.println("Updating Contacts by Name");
					System.out.println("Enter the first name of the person you wish to update their details");
					String firstNameUpdate = readIn.nextLine();
					System.out.println("Enter the last name of the person you wish to look up");
					String lastNameUpdate = readIn.nextLine();
					newContacts.updateContact(firstNameUpdate, lastNameUpdate);
					break;
					
				case 4: 
					// Delete a contact with confirmation
					System.out.println("Deleting Contacts by Name");
					while(true)
					{
						System.out.println("Enter the first name of the contact you wish to delete");
						String firstContactDelete = readIn.nextLine();
						System.out.println("Enter the last name of the person you wish to look up");
						String lastContactDelete = readIn.nextLine();
						
						if(newContacts.contactExists(firstContactDelete, lastContactDelete)) 
						{
							System.out.println("Are you sure you want to delete contact details for: " + firstContactDelete + " " + lastContactDelete + "\n" + "Enter Yes or No");
							String confirmation = readIn.nextLine();
							if(confirmation.equalsIgnoreCase("yes"))
							{
								newContacts.deleteContact(firstContactDelete, lastContactDelete);
								break;
							}
							else
							{
								System.out.println("Not deleting contact\n");
								break;
							}
						}
						else
						{
							System.out.println("Contact does not exist.\n");
						}
					}
					break;
					
				case 5:
					// Print all stored contacts
					System.out.println("Printing all of the contact informations: ");
					newContacts.printAllContacts();
					break;
					
				case 6:
					// Exit the application
					System.out.println("Thank you for using the Contacts Manager App. Have a nice day!");
					break;
			}
		}while(choice != 6); // Repeat until user exits
	}
}

/**
 * Output: 
 * Welcome to the Contacts Manager App. Please press any number options below to get started:
1: Enter Contact Details
2: Search for Contacts by Name
3: Update Contacts by Name
4: Delete Contacts by Name
5: Print all Contacts
6: Exit the Program
5
Printing all of the contact informations: 
Contacts
===========================
First Name: ADA
Last Name: LOVELACE
Phone Number: 543-449-2349
Email: ADALOVELACE@GMAIL.COM
Birthday: 12/10/15
===========================

Contacts
===========================
First Name: AYESHA
Last Name: PATEL
Phone Number: 917-322-4855
Email: AYESHA.PATEL@GMAIL.COM
Birthday: 02/14/97
===========================

Contacts
===========================
First Name: DANIEL
Last Name: THOMPSON
Phone Number: 703-884-5532
Email: DANIEL.T@GMAIL.COM
Birthday: 10/03/87
===========================

Contacts
===========================
First Name: EMILY
Last Name: NGUYEN
Phone Number: 832-456-2290
Email: EMILYNGUYEN@GMAIL.COM
Birthday: 12/01/98
===========================

Contacts
===========================
First Name: ETHAN
Last Name: KIM
Phone Number: 408-733-2299
Email: ETHAN.KIM@OUTLOOK.COM
Birthday: 01/09/00
===========================

Contacts
===========================
First Name: GRACE
Last Name: JOHNSON
Phone Number: 205-911-3378
Email: GRACE.JOHNSON@AOL.COM
Birthday: 07/17/91
===========================

Contacts
===========================
First Name: JAMES
Last Name: MILLER
Phone Number: 312-678-9901
Email: JAMES.MILLER@GMAIL.COM
Birthday: 09/25/89
===========================

Contacts
===========================
First Name: MICHAEL
Last Name: RODRIGUEZ
Phone Number: 714-556-7789
Email: M.RODRIGUEZ@HOTMAIL.COM
Birthday: 11/20/85
===========================

Contacts
===========================
First Name: PETER
Last Name: PARKER
Phone Number: 999-333-2221
Email: SPIDERMAN@GMAIL.COM
Birthday: 08/10/99
===========================

Contacts
===========================
First Name: PRIYA
Last Name: MEHTA
Phone Number: 720-443-9087
Email: PRIYA.MEHTA@ICLOUD.COM
Birthday: 05/05/96
===========================

Contacts
===========================
First Name: QUINTESSA
Last Name: BARK
Phone Number: 222-222-222
Email: QUINTESSABARK@GMAIL.COM
Birthday: 03/03/88
===========================

Contacts
===========================
First Name: ROBERT
Last Name: LEE
Phone Number: 503-321-7445
Email: ROBERTLEE@LIVE.COM
Birthday: 08/30/93
===========================

Contacts
===========================
First Name: SOPHIA
Last Name: CHEN
Phone Number: 646-221-8734
Email: SOPHIA.CHEN@YAHOO.COM
Birthday: 06/11/92
===========================

Contacts
===========================
First Name: TONY
Last Name: STARK
Phone Number: 999-999-999
Email: IRONMANROX@GMAIL.COM
Birthday: 05/29/70
===========================

Contacts
===========================
First Name: VINAY
Last Name: SAM
Phone Number: 862-299-9450
Email: VINAYTSAM@GMAIL.COM
Birthday: 10/09/04
===========================

Welcome to the Contacts Manager App. Please press any number options below to get started:
1: Enter Contact Details
2: Search for Contacts by Name
3: Update Contacts by Name
4: Delete Contacts by Name
5: Print all Contacts
6: Exit the Program
1
Adding a new Contact
Enter the first name of the person you wish to add
Colleen
Enter the last name of the person you wish to add
Bamford
Enter the phone number of the new Contact. Please use the '-' symbol after the 3rd and 6th number.
973-328-5669
Do you wish to enter the email address of the new Contact? Press Enter to skip.
cbamford@ccm.edu
Do you wish to enter their birthday details? If so, type in MM/DD/YY. Press Enter to skip.

Successfully added contact

Contacts
===========================
First Name: COLLEEN
Last Name: BAMFORD
Phone Number: 973-328-5669
Email: CBAMFORD@CCM.EDU
Birthday: N/A
===========================

Welcome to the Contacts Manager App. Please press any number options below to get started:
1: Enter Contact Details
2: Search for Contacts by Name
3: Update Contacts by Name
4: Delete Contacts by Name
5: Print all Contacts
6: Exit the Program
4
Deleting Contacts by Name
Enter the first name of the contact you wish to delete
Colleen
Enter the last name of the person you wish to look up
Bamford
Are you sure you want to delete contact details for: Colleen Bamford
Enter Yes or No
yes
Contact deleted.
Welcome to the Contacts Manager App. Please press any number options below to get started:
1: Enter Contact Details
2: Search for Contacts by Name
3: Update Contacts by Name
4: Delete Contacts by Name
5: Print all Contacts
6: Exit the Program
2
Searching for a Contact
Enter the first name of the person you wish to look up
Peter
Enter the last name of the person you wish to look up
Parker
Contact Found:
Contacts
===========================
First Name: PETER
Last Name: PARKER
Phone Number: 999-333-2221
Email: SPIDERMAN@GMAIL.COM
Birthday: 08/10/99
===========================

Welcome to the Contacts Manager App. Please press any number options below to get started:
1: Enter Contact Details
2: Search for Contacts by Name
3: Update Contacts by Name
4: Delete Contacts by Name
5: Print all Contacts
6: Exit the Program
3
Updating Contacts by Name
Enter the first name of the person you wish to update their details
Peter
Enter the last name of the person you wish to look up
Parker
Contact found:
Contacts
===========================
First Name: PETER
Last Name: PARKER
Phone Number: 999-333-2221
Email: SPIDERMAN@GMAIL.COM
Birthday: 08/10/99
===========================

Enter new first name (or press Enter to keep current):
Pete
Enter new last name (or press Enter to keep current):

Enter new phone number (or press Enter to keep current):

Enter new email address (or press Enter to keep current):

Enter new birthday (or press Enter to keep current):

Contact updated:
Contacts
===========================
First Name: PETE
Last Name: PARKER
Phone Number: 999-333-2221
Email: SPIDERMAN@GMAIL.COM
Birthday: 08/10/99
===========================

Welcome to the Contacts Manager App. Please press any number options below to get started:
1: Enter Contact Details
2: Search for Contacts by Name
3: Update Contacts by Name
4: Delete Contacts by Name
5: Print all Contacts
6: Exit the Program
5
Printing all of the contact informations: 
Contacts
===========================
First Name: ADA
Last Name: LOVELACE
Phone Number: 543-449-2349
Email: ADALOVELACE@GMAIL.COM
Birthday: 12/10/15
===========================

Contacts
===========================
First Name: AYESHA
Last Name: PATEL
Phone Number: 917-322-4855
Email: AYESHA.PATEL@GMAIL.COM
Birthday: 02/14/97
===========================

Contacts
===========================
First Name: DANIEL
Last Name: THOMPSON
Phone Number: 703-884-5532
Email: DANIEL.T@GMAIL.COM
Birthday: 10/03/87
===========================

Contacts
===========================
First Name: EMILY
Last Name: NGUYEN
Phone Number: 832-456-2290
Email: EMILYNGUYEN@GMAIL.COM
Birthday: 12/01/98
===========================

Contacts
===========================
First Name: ETHAN
Last Name: KIM
Phone Number: 408-733-2299
Email: ETHAN.KIM@OUTLOOK.COM
Birthday: 01/09/00
===========================

Contacts
===========================
First Name: GRACE
Last Name: JOHNSON
Phone Number: 205-911-3378
Email: GRACE.JOHNSON@AOL.COM
Birthday: 07/17/91
===========================

Contacts
===========================
First Name: JAMES
Last Name: MILLER
Phone Number: 312-678-9901
Email: JAMES.MILLER@GMAIL.COM
Birthday: 09/25/89
===========================

Contacts
===========================
First Name: MICHAEL
Last Name: RODRIGUEZ
Phone Number: 714-556-7789
Email: M.RODRIGUEZ@HOTMAIL.COM
Birthday: 11/20/85
===========================

Contacts
===========================
First Name: PETE
Last Name: PARKER
Phone Number: 999-333-2221
Email: SPIDERMAN@GMAIL.COM
Birthday: 08/10/99
===========================

Contacts
===========================
First Name: PRIYA
Last Name: MEHTA
Phone Number: 720-443-9087
Email: PRIYA.MEHTA@ICLOUD.COM
Birthday: 05/05/96
===========================

Contacts
===========================
First Name: QUINTESSA
Last Name: BARK
Phone Number: 222-222-222
Email: QUINTESSABARK@GMAIL.COM
Birthday: 03/03/88
===========================

Contacts
===========================
First Name: ROBERT
Last Name: LEE
Phone Number: 503-321-7445
Email: ROBERTLEE@LIVE.COM
Birthday: 08/30/93
===========================

Contacts
===========================
First Name: SOPHIA
Last Name: CHEN
Phone Number: 646-221-8734
Email: SOPHIA.CHEN@YAHOO.COM
Birthday: 06/11/92
===========================

Contacts
===========================
First Name: TONY
Last Name: STARK
Phone Number: 999-999-999
Email: IRONMANROX@GMAIL.COM
Birthday: 05/29/70
===========================

Contacts
===========================
First Name: VINAY
Last Name: SAM
Phone Number: 862-299-9450
Email: VINAYTSAM@GMAIL.COM
Birthday: 10/09/04
===========================

Welcome to the Contacts Manager App. Please press any number options below to get started:
1: Enter Contact Details
2: Search for Contacts by Name
3: Update Contacts by Name
4: Delete Contacts by Name
5: Print all Contacts
6: Exit the Program
6
Thank you for using the Contacts Manager App. Have a nice day!

 */
