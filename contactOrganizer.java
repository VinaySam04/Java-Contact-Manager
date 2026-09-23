/**
 * Vinay Sam 
 * 04/05/2025
 * This class manages a list of contact objects, allowing operations like add, search, update, delete, and display
 */
import java.util.ArrayList;  
import java.util.Collections;
import java.util.Scanner;

public class contactOrganizer 
{
	private ArrayList<contact> contacts; // List to store contact objects
	
	public contactOrganizer()
	{
		contacts = new ArrayList<>(); // Initialize the contact list
	}
	
	/**
	 * Adds a new contact and keeps the list sorted.
	 * 
	 * @param firstName The first name of the contact
	 * @param lastName The last name of the contact
	 * @param number The phone number of the contact
	 * @param email The email address of the contact
	 * @param birthDay The birthday of the contact
	 */
	public void addContact(String firstName, String lastName, String number, String email, String birthDay)
	{
		contacts.add(new contact(firstName, lastName, number, email, birthDay));
		Collections.sort(contacts); // Sort the list for binary search compatibility
	}
	
	/**
	 * Searches for a contact by first and last name.
	 * 
	 * @param firstName The first name of the contact to search for
	 * @param lastName The last name of the contact to search for
	 */
	public void searchContact(String firstName, String lastName) 
	{
	    contact key = new contact(firstName, lastName, "", "", ""); // Create a temporary key contact for search
	    int index = Collections.binarySearch(contacts, key); // Binary search assumes list is sorted
	    if (index >= 0) 
	    {
	        System.out.println("Contact Found:\n" + contacts.get(index)); 
	    } 
	    else 
	    {
	        System.out.println("Contact not found.\n"); 
	    }
	}
	
	/**
	 * Updates an existing contact’s details using user input.
	 * 
	 * @param firstName The first name of the contact to update
	 * @param lastName The last name of the contact to update
	 */
	public void updateContact(String firstName, String lastName)
	{
		contact key = new contact(firstName, lastName, "", "", "");
	    int index = Collections.binarySearch(contacts, key); // Locate the contact in the list

	    if (index >= 0) 
	    {
	        contact found = contacts.get(index); // Retrieve the contact
	        @SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in); // Scanner to take new input from the user

	        System.out.println("Contact found:\n" + found);
				
			// Prompt user to update each field, or keep existing by pressing Enter
			System.out.println("Enter new first name (or press Enter to keep current):");
			String newFirstName = scanner.nextLine();
				
			System.out.println("Enter new last name (or press Enter to keep current):");
			String newLastName = scanner.nextLine();

			// Validate phone number format or keep current
			String newNumber = "";
			while(true)
			{
				System.out.println("Enter new phone number (or press Enter to keep current):");
				newNumber = scanner.nextLine();
				if(newNumber.isEmpty())
					break;
				else if (newNumber.matches("\\d{3}-\\d{3}-\\d{4}")) 
					break;
				else 
					System.out.println("Invalid phone number. Use format 123-456-7890.");
			}	
				
			// Validate email format or skip
			String newEmail = "";
			while(true)
			{
				System.out.println("Enter new email address (or press Enter to keep current):");
				newEmail = scanner.nextLine();	
				if(newEmail.isEmpty())
					break;
				else if (newEmail.contains("@")) 
					break;
				else 
					System.out.println("Invalid email address. Must contain '@'.");
			}
				
			// Validate birthday format or skip
			String newBirthday = "";
			while(true)
			{
				System.out.println("Enter new birthday (or press Enter to keep current):");
				newBirthday = scanner.nextLine();
				if(newBirthday.isEmpty())
				{
					break;
				}
				else if (newBirthday.contains("/"))
				{
					break;
				}
				else 
				{
					System.out.println("Invalid birthday. Must contain '/'.");
				}
			}
				
			// Update fields only if a new value is provided
			if (!newFirstName.isEmpty()) 
				found.setContactFirstName(newFirstName);
			if (!newLastName.isEmpty()) 
				found.setContactLastName(newLastName);
			if (!newNumber.isEmpty()) 
				found.setContactNumber(newNumber);
			if (!newEmail.isEmpty()) 
				found.setContactEmail(newEmail);
			if (!newBirthday.isEmpty())
				found.setContactBirthday(newBirthday);
			
			System.out.println("Contact updated:\n" + found);
	    } 
	    else 
	    {
	        System.out.println("Contact not found.\n");
	    }
	}
	
	/**
	 * Deletes a contact from the list.
	 * 
	 * @param firstName The first name of the contact to delete
	 * @param lastName The last name of the contact to delete
	 */
	public void deleteContact(String firstName, String lastName)
	{
		contact key = new contact(firstName, lastName, "", "", "");
        int index = Collections.binarySearch(contacts, key); // Find index with binary search
        if (index >= 0) 
        {
            contacts.remove(index); // Remove the contact
            System.out.println("Contact deleted.");
        } 
        else 
        {
            System.out.println("Contact not found. Please try again.");
        }
	}
	
	/**
	 * Checks if a contact exists by first and last name.
	 * 
	 * @param firstName The first name of the contact
	 * @param lastName The last name of the contact
	 * @return true if the contact exists, false otherwise
	 */
	public boolean contactExists(String firstName, String lastName) 
	{
	    for (contact c : contacts) 
	    { 
	        if (c.getContactFirstName().equalsIgnoreCase(firstName) && c.getContactLastName().equalsIgnoreCase(lastName)) 
	        {
	            return true;
	        }
	    }
	    return false;
	}

	/**
	 * Prints all contact entries in the list.
	 */
	public void printAllContacts()
	{
		if (contacts.isEmpty()) 
        {
            System.out.println("No listings available."); // No contacts in the list
        } 
        else 
        {
            for (contact listing : contacts) 
            {
                System.out.println(listing); // Display each contact
            }
        }
	}
}
