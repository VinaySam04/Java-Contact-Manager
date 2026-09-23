# Java-Contact-Manager
# Overview 
This is a Java console application I built for managing personal contact entries. The program loads existing contacts from a file, lets you add, search, update, delete, and list all contacts, and uses binary search to look up names quickly.

# Features

File Input: Loads contacts from a text file named CONTACTS when the program starts.

Add Contact: Add a new contact with first name, last name, phone number, email, and birthday. Validates phone number format (XXX-XXX-XXXX), checks for @ in emails, and checks for / in birthdays. Prevents duplicate name entries.

Search Contact: Finds a contact by first and last name using binary search.

Update Contact: Update individual details for a contact without needing to re-type existing info.

Delete Contact: Prompts for confirmation before removing a contact entry from memory.

Print All: Displays all currently stored contacts sorted alphabetically by full name.

# Project Files

contact.java - Class defining the contact object structure, getters/setters, formatted string output, and Comparable interface implementation for sorting by full name.

contactOrganizer.java - Handles storing contacts in an ArrayList, sorting the list with Collections.sort(), and performing binary search, updates, deletes, and print operations.

contactManagerClient.java - Main driver program containing the menu loop and input processing.

# Requirements

JDK 8 or higher

A text file named CONTACTS placed in the root directory.

Input File Format (CONTACTS)

The file must contain space-separated values for each contact in this exact order:

FirstName LastName PhoneNumber Email Birthday

# Example CONTACTS file:

ADA LOVELACE 543-449-2349 ADALOVELACE@GMAIL.COM 12/10/15
AYESHA PATEL 917-322-4855 AYESHA.PATEL@GMAIL.COM 02/14/97

# Steps

Open a terminal/command prompt in the directory containing the files.
Compile the source files:
javac contact.java contactOrganizer.java contactManagerClient.java

Execute the program:

java contactManagerClient

Console Menu Options

1: Enter Contact Details
2: Search for Contacts by Name
3: Update Contacts by Name
4: Delete Contacts by Name
5: Print all Contacts
6: Exit the Program
