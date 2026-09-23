/**
 * Vinay Sam
 * 04/05/2025
 * This class represents an individual contact with details such as first name, last name, phone number, email, and birthday
 */
public class contact implements Comparable<contact>
{
	private String contactFirstName;
	private String contactLastName;
	private String contactEmail;
	private String contactNumber;
	private String contactBirthday;
	
	/**
	 * Constructor to initialize all fields of a contact. 
	 * 
	 * @param firstName The first name of the contact
	 * @param lastName The last name of the contact
	 * @param number The phone number of the contact
	 * @param email The email address of the contact
	 * @param birthDay The birthday of the contact
	 */
	public contact (String firstName, String lastName, String number, String email, String birthDay)
	{
		this.contactFirstName = firstName;
		this.contactLastName = lastName;
		this.contactNumber = number;
		this.contactEmail = email;
		this.contactBirthday = birthDay;
	}

	// Getter for first name
	public String getContactFirstName()
	{
		return contactFirstName;
	}

	// Getter for last name
	public String getContactLastName()
	{
		return contactLastName;
	}

	// Getter for phone number
	public String getContactNumber()
	{
		return contactNumber;
	}

	// Getter for email address
	public String getContactEmail()
	{
		return contactEmail;
	}

	// Getter for birthday
	public String getContactBirthday()
	{
		return contactBirthday;
	}

	/**
	 * Setter for first name.
	 * 
	 * @param firstName The new first name of the contact
	 */
	public void setContactFirstName(String firstName) 
	{
	    this.contactFirstName = firstName;
	}

	/**
	 * Setter for last name.
	 * 
	 * @param lastName The new last name of the contact
	 */
	public void setContactLastName(String lastName) 
	{
	    this.contactLastName = lastName;
	}

	/**
	 * Setter for phone number.
	 * 
	 * @param number The new phone number of the contact
	 */
	public void setContactNumber(String number) 
	{
	    this.contactNumber = number;
	}

	/**
	 * Setter for email address.
	 * 
	 * @param email The new email address of the contact
	 */
	public void setContactEmail(String email) 
	{
	    this.contactEmail = email;
	}

	/**
	 * Setter for birthday.
	 * 
	 * @param birthDay The new birthday of the contact
	 */
	public void setContactBirthday(String birthDay) 
	{
	    this.contactBirthday = birthDay;
	}
	
	/**
	 *  Compare two contacts by full name (case-insensitive) for sorting
	 * 
	 * @param other The contact to compare to
	 * @return A negative integer, zero, or a positive integer if this contact is less than, equal to, 
	 *         or greater than the specified contact
	 */
	@Override
	public int compareTo(contact other) 
	{
	    String fullName = this.contactFirstName + this.contactLastName;
	    String otherFullName = other.contactFirstName + other.contactLastName;
	    return fullName.compareToIgnoreCase(otherFullName);
	}


	// String representation of the contact
	@Override
	public String toString()
	{
		return  "Contacts\n===========================\n" +
		        "First Name: " + contactFirstName.toUpperCase() + "\n" + 
				"Last Name: " + contactLastName.toUpperCase() + "\n" + 
				"Phone Number: " + contactNumber + "\n" + 
				"Email: " + contactEmail.toUpperCase() + "\n" + 
				"Birthday: " + contactBirthday + 
				"\n===========================\n";
	}
}