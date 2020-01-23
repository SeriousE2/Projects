package com.mycompany.dentalclinic.ui;

import java.util.List;
import java.util.stream.Collectors;
import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.Customer;
import com.mycompany.dentalclinic.models.Professional;
import com.mycompany.dentalclinic.models.SpecialtyType;
import com.mycompany.dentalclinic.service.Response;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class View {
    
    private final ConsoleIO io;
    
    public View(ConsoleIO io) {
        this.io = io;
    }
    
    public MainMenuOption selectFromMainMenu() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MainMenuOption mmo : MainMenuOption.values()) {
            io.print(String.format("%s. %s", mmo.getValue(), mmo.getName()));
            min = Math.min(mmo.getValue(), min);
            max = Math.max(mmo.getValue(), max);
        }
        int value = io.readInt(String.format("Select [%s-%s]:", min, max), min, max);
        return MainMenuOption.fromValue(value);
    }
    
    public boolean confirm(String message) {
        return io.readBoolean(String.format("%s [y/n]:", message));
    }
    
    public void welcome() {
        printHeader("Dental Clinic!");
        io.print("====================\n");
    }
    
    public void goodbye() {
        printHeader("Good Bye!");
    }
    
    boolean createNewAppointment(String header) {
        printHeader(header);
        io.print("1. Create a new customer.");
        io.print("2. Search for an existing customer.");
        return io.readInt("Select [1-2]: ", 1, 2) == 1;
    }

    // create =============
    Appointment makeAppointment() {
        printHeader("New Appointment");
        Appointment appointment = new Appointment();

//        Customer result = new Customer();
//        result.setEmailAddress(io.readRequiredString("Email Address:"));
//        result.setFirstName(io.readRequiredString("First Name:"));
//        result.setLastName(io.readRequiredString("Last Name:"));
        return null;
    }

    // displays =======
    void displayErrors(Response r) {
        io.print("");
        printHeader("ERROR");
        for (String message : r.getErrors()) {
            io.print(message);
        }
        io.print("");
    }
    
    public void printHeader(String message) {
        io.print(String.format("=== %s ===", message));
    }
    
    String getAppointmentByLastNameSearch() {
        printHeader("Search appointments by customer last name.");
        return io.readRequiredString("Last Name: ");
    }
    
    public LocalDate getDate(String prompt) {
        return io.readLocalDate(prompt);
    }
    
    public LocalTime getTime(String prompt) {
        return io.readTime(prompt);
    }
    
    boolean shouldCreateCustomer(String header) {
        printHeader(header);
        io.print("1. Create a new customer.");
        io.print("2. Search for an existing customer.");
        return io.readInt("Select [1-2]: ", 1, 2) == 1;
    }
    
    int shouldCreateAppointment(String header) {
        printHeader(header);
        io.print("0. Exit");
        io.print("1. Create a new appointment.");
        io.print("2. Search for an existing appointment.");
        io.print("3. Update an existing appointment.");
        io.print("4. Cancel an existing appointment.");
        return io.readInt(header, 0, 4);
    }

    // create =============
    Customer makeCustomer() {
        printHeader("New customer");
        Customer result = new Customer();
//        result.getCustomerId();
        result.setFirstName(io.readRequiredString("First Name:"));
        result.setLastName(io.readRequiredString("Last Name:"));
        result.setDateOfBirth(io.readLocalDate("Date of Birth:"));
        return result;
    }
    
    String getCustomerLastNameSearch() {
        printHeader("Search for a Customer.");
        return io.readRequiredString("Last Name starts with:");
    }
    
    String getProfessionalbyLastName() {
        printHeader("Search for a Professional.");
        return io.readRequiredString("Last Name starts with:");
    }
    
    void displayNoProfessionalWarning(String prefix) {
        io.print("No Professional with last name starting with: " + prefix + "\n");
    }
    
    void displayNoCustomerWarning(String prefix) {
        io.print("No customers with last name starting with: " + prefix + "\n");
    }

    // Helps sort customer search
    Customer chooseCustomer(List<Customer> customers) {
        
        if (customers.size() > 25) {
            io.print("Too many customers. Showing the top 25.\n");
        }
        
        customers = customers.stream()
                .sorted((a, b) -> a.getLastName().compareTo(b.getLastName()))
                .limit(25)
                .collect(Collectors.toList());
        
        int index = 1;
        for (Customer c : customers) {
            io.print(String.format("%s: %s %s %s",
                    index++,
                    //                    c.getCustomerId(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getDateOfBirth()));
            
        }
        
        io.print(""); // empty newline

        int record = io.readInt("Enter a row number or 0 to exit.", 0, index - 1);
        if (record > 0) {
            return customers.get(record - 1);
        }
        
        return null;
    }

    // Helps sort Professional search
    Professional chooseProfessional(List<Professional> professional) {
        
        if (professional.size() > 25) {
            io.print("Too many professional. Showing the top 25.\n");
        }
        
        professional = professional.stream()
                .sorted((a, b) -> a.getProfessionalLastName().compareTo(b.getProfessionalLastName()))
                .limit(25)
                .collect(Collectors.toList());
        
        int index = 1;
        for (Professional p : professional) {
            io.print(String.format("%s: %s",
                    index++,
                    p.toString()));
            
        }
        
        io.print(""); // empty newline

        int record = io.readInt("Enter a row number or 0 to exit.", 0, index - 1);
        if (record > 0) {
            return professional.get(record - 1);
        }
        
        return null;
    }
    
    void displaySuccess(String string) {
        printHeader(string);
    }
    
    void displayError(String string) {
        printHeader(string);
    }
    
    public SpecialtyType getType(String prompt) {
        return SpecialtyType.valueOf(io.readRequiredString(prompt).toUpperCase());
    }
    
    int getUserInt() {
        return io.readInt("Select [1-4]:", 1, 4);
    }
    
    void displaySearchedAppointments(List<Appointment> searchedAppointments) {
        if (searchedAppointments == null) {
            io.print("The Error");
        }
        for (Appointment a : searchedAppointments) {
            io.print(a.getDentalProLastname()
                    + ": " + a.getType()
                    + ": "
                    + a.getStart()
                    + ": "
                    + a.getEnd()
                    + ": "
                    + a.getTotalCost()
                    + ": "
                    + a.getNotes()
                    + "\n");
        } 
    }
    
    public void displayCustomer(Customer customer) {
        io.print("" + customer);
    }
    
    void displaySuccess() {
        printHeader("Success.");
    }
    
    public Appointment updateAppointment(Appointment appointmentToUpdate) {
        boolean update = confirm("Would you like to update notes.");
        
        if (update) {
            String newNotes = io.readRequiredString("Please Enter new notes.");
            appointmentToUpdate.setNotes(newNotes);
        }
        boolean updateCost = confirm("Would you like to update Cost.");
        if (updateCost) {
            BigDecimal newPrice = io.readBigDecimal("Please Enter new Cost.");
            appointmentToUpdate.setTotalCost(newPrice);
        }
        return appointmentToUpdate;
    }   
    
     Appointment cancelAppointment(Appointment appointmentToCancel) {
        boolean update = confirm("Would you like to cancel this Appointment?");
        if (update) {
            return appointmentToCancel;
        } else {
        io.print("Option Cancelled");
        }
      return null;
    }
    
    public boolean displayUpdatedAppointment(Appointment appointment) {
        io.print("\n Please verify your appointment information is correct: ");
        io.print("=========================================================");
        io.print("Dental Professional: " + appointment.getDentalProLastname());
        io.print("Specialty: " + appointment.getType());
        io.print("Date: " + appointment.getDate());
        io.print("Start Time: " + appointment.getStart());
        io.print("End Time: " + appointment.getEnd());
        io.print("Total Cost $: " + appointment.getTotalCost());
        io.print("Notes: " + appointment.getNotes());
        boolean infoToVerify = confirm("Is the information above accurate?"); 
        
        return infoToVerify;
    }

}
