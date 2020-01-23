/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.ui;

import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.Customer;
import com.mycompany.dentalclinic.models.Professional;
import com.mycompany.dentalclinic.models.SpecialtyType;
import com.mycompany.dentalclinic.service.AppointmentService;
import com.mycompany.dentalclinic.service.CustomerService;
import com.mycompany.dentalclinic.service.DentalClinicService;
import com.mycompany.dentalclinic.service.ProfessionalService;
import com.mycompany.dentalclinic.service.Response;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eddy
 */
public class Controller {

    private View view;
    private CustomerService customerService;
    private AppointmentService appointmentService;
    private ProfessionalService professionalService;
    private DentalClinicService dentalClinicService;

    public Controller(View view,
            CustomerService customerService,
            AppointmentService appointmentService,
            ProfessionalService professionalService,
            DentalClinicService dentalClinicService) {
        this.view = view;
        this.customerService = customerService;
        this.appointmentService = appointmentService;
        this.professionalService = professionalService;
        this.dentalClinicService = dentalClinicService;
    }

    public void run() throws DataException {

        view.welcome();

        MainMenuOption selection;

        do {
            selection = view.selectFromMainMenu();
            switch (selection) {
                case APPOINTMENTS_OPTIONS:
                    appointmentOptions();
                    break;
                case CUSTOMER_OPTIONS:
                    customerOption();
                    break;
            }
        } while (selection != MainMenuOption.EXIT);

        view.goodbye();

    }

    private Customer findAndListCustomer() {
        Customer customer = findOrCreateCustomer("Enter a customer.");
        if (customer != null) {
            return customer;
        }
        return null;
    }

    private void customerOption() {
        Customer customer = findAndListCustomer();

        view.displayCustomer(customer);
    }

    private void appointmentOptions() throws DataException {
        Appointment appointment = manageAppointment("Appointment Options");
        if (appointment == null) {
//           return appointment;
        }

    }

    private Appointment scheduleAppointment() {
        view.printHeader("Schedule Appointment");
        // 1. Get existing customer name from view.
        // 2. Get matching customers from service.
        // 3. Select one customer using the view..
        Customer customer = findAndListCustomer();
        if (customer == null) {
            return null;
        } else{
             view.displayCustomer(customer);
        }
        // 4. Get date from view.
        LocalDate date = view.getDate("Please enter date:");
        if (date == null) {
            return null;
        }
        // 5. Get a Specialty from view.
        SpecialtyType specialtyType = view.getType("Please enter Specialty Type");
        if (specialtyType == null) {
            return null;
        }
        // 6. Get all Pros with that specialty from service.
        List<Professional> allProfessionalsBySpecialtyType = professionalService.searchAllProsBySpecialtyType(specialtyType);
        if (allProfessionalsBySpecialtyType.isEmpty()) {
            return null;
        }
        // 7. Select one pro using the view.
        Professional professional = view.chooseProfessional(allProfessionalsBySpecialtyType);
        if (professional == null) {
            return null;
        }
        // 8. Get start and end time from view.
        LocalTime startTime = view.getTime("Please enter Start Time: HH:mm");
        LocalTime endTime = view.getTime("Please enter End Time: HH:mm");

        // 9. Make an appointment from everything above. Save it (using service).
//        Appointment appointment = dentalClinicService.searchOpenTimeSlots(date, professional, startTime, endTime);
        Appointment appointment = new Appointment();
        appointment.setDate(date);
        appointment.setCustomerId(customer.getCustomerId());
        appointment.setStart(startTime);
        appointment.setEnd(endTime);
        appointment.setType(specialtyType);
        appointment.setDentalProLastname(professional.getProfessionalLastName());
        appointment.setTotalCost(dentalClinicService.calculateCost(appointment));

        view.displayUpdatedAppointment(appointment);

//         {}

            Response r = dentalClinicService.createNewAppointment(appointment);
            
            if(!r.hasError()) {
                view.displaySuccess();
            } else {
                view.displayErrors(r);
            }

//        try {
//            dentalClinicService.createNewAppointment(appointment);
//        } catch (DataException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

    private Appointment updateAppointment() throws DataException {
        view.printHeader("Update Appointment");
//        Response response = new Response();
//Enter a date.
        LocalDate date = view.getDate("Please enter date:");
        if (date == null) {
            return null;
        }
//Choose a Customer.
        Customer customer = findAndListCustomer();
        if (customer == null) {
            return null;
        }
        view.displayCustomer(customer);
        if (customer == null) {
            return null;
        }

//      Customer matchedsearched = customer;
//Choose an Appointment.
        List<Appointment> searchedByIdAndDate = appointmentService.findCustomerIdAndDate(customer.getCustomerId(), date);
        if (searchedByIdAndDate.isEmpty() == true) {
//            response.addError("No Appointment Found For User.");
            view.displayError("No Appointment Found For User.");

            return null;
        }

        view.displaySearchedAppointments(searchedByIdAndDate);

        Appointment updatedAppointment = view.updateAppointment(searchedByIdAndDate.get(0));

//Allow a Dental Professional or User to add notes to the Appointment or change its total cost.
        Response updateAppointment = appointmentService.upDateAppointment(updatedAppointment, date);
        if (updateAppointment == null) {
            view.displayError("Data Error");
        }
        view.displaySuccess();
        return null;
    }

    private void cancelAppointment() {
        view.printHeader("Cancel Appointment");

        Customer customer = findAndListCustomer();
        if (customer == null) {
            view.displayError("Data Error!");
        }

        LocalDate date = view.getDate("Please enter date:");
        if (date == null) {
            view.displayError("Date Error!");
        }

        view.displayCustomer(customer);
        if (customer == null) {
            view.displayError("Data Error!");
        }

        List<Appointment> searchedByIdAndDate = appointmentService.findCustomerIdAndDate(customer.getCustomerId(), date);
        if (searchedByIdAndDate.isEmpty() == true) {

            view.displayError("No Appointment Found For User.");
        }

        view.displaySearchedAppointments(searchedByIdAndDate);
        Appointment cancelAppointment = searchedByIdAndDate.get(0);
        boolean confirm = view.confirm("Would you like to cancel this Appointment?");

        if (confirm) {

            Response cancelAppointments = appointmentService.cancelAppointment(customer.getCustomerId(), date, cancelAppointment.getType());
            if (cancelAppointments == null) {
                view.displayError("Data Error");
            }
            view.displaySuccess();
        }

//        Appointment cancelAppointment = view.cancelAppointment();   
//                (searchedByIdAndDate.get(0));
    }

    private void searchAppointmentByDateAndPro() throws DataException {
        view.printHeader("Search Appointment");

//Enter a date.
        LocalDate date = view.getDate("Please enter date:");
//Select a Dental Professional.
        Professional professional = findProfessional();
//Application displays all Appointments for that date and Dental Professional, or indicates there are none.
        List<Appointment> appointments = appointmentService.findByProLastNameAndDate(date, professional.getProfessionalLastName());
        if (appointments == null) {
            view.displayError("No Professional Found For Search.");
            
        }
        view.displaySearchedAppointments(appointments);
        
       

// Test *
//        serviceResponse<List<Appointment>> response = appointmentService.findByProLastNameAndDate(date, userInputLastName);
//        if (!response.hasError()) {
//            view.displaySearchedAppointments(response.getValue());
//        } else {
//            view.displayErrors(response);
//        }
    }

    private void viewAppointment() {
//Enter a date.
        LocalDate date = view.getDate("Please enter date:");
//Choose a Customer.
        //get list of customers from customerService
        //pass list of customers to view then have the method return the users choice of customer store the choice in **customerChoice** 

//Application displays Appointments or indicates there are none.
        // Call to the customerService and get all appointments that customer has on date
//Choose an Appointment.
        // Pass list of appointments to view and have the method return an appointment
//Application displays full Appointment details.
        //Pass appointment Object to view and the method will display all the details 
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Customer createCustomer() {

        Customer c = view.makeCustomer();
        if (view.confirm("Please verify information for: " + c + " Submit? ")) {
            Response r = customerService.add(c);
            if (r.hasError()) {
                view.displayErrors(r);
                return null;
            } else {
                view.displaySuccess("" + c + " was added");
                return c;
            }
        }
        return null;
    }

//    public void findProfessionalbyLastName() {
//        String userInputLastName = view.getProfessionalbyLastName();
//        List<Professional> professionalLastName = professionalService.findByProLastName(userInputLastName);
//
//    }
    private Professional findProfessional() {

        String search = view.getProfessionalbyLastName();
        List<Professional> professional = professionalService.findByProLastName(search);
        if (professional.size() <= 0) {
            view.displayNoProfessionalWarning(search);
            return null;
        }
        return view.chooseProfessional(professional);
    }

    private Customer findCustomer() {

        String search = view.getCustomerLastNameSearch();
        List<Customer> customers = customerService.findByLastNameStartingWith(search);
        if (customers.size() <= 0) {
            view.displayNoCustomerWarning(search);
            return null;
        }

        return view.chooseCustomer(customers);
    }

    private Customer findOrCreateCustomer(String header) {

        boolean shouldCreate = view.shouldCreateCustomer(header);
        if (shouldCreate) {
            return createCustomer();
        }
        return findCustomer();
    }

    private Appointment manageAppointment(String header) throws DataException {
        int optionAppointment;
        do {
            optionAppointment = view.shouldCreateAppointment(header);
            switch (optionAppointment) {
                case 1:
                    scheduleAppointment();
                    break;
                case 2:
                    searchAppointmentByDateAndPro();
                    break;
                case 3:
                    updateAppointment();
                    break;
                case 4:
                    cancelAppointment();
//                    System.out.println("Cancel an existing appointment.");
                    break;

            }
        } while (optionAppointment > 0);

        view.goodbye();

        return null;
    }
}

