/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic;
import com.mycompany.dentalclinic.data.AppointmentsDao;
import com.mycompany.dentalclinic.data.AppointmentsFileDao;
import com.mycompany.dentalclinic.data.CustomersDao;
import com.mycompany.dentalclinic.data.ProfessionalDao;
import com.mycompany.dentalclinic.data.ProfessionalFileDao;
import com.mycompany.dentalclinic.ui.ConsoleIO;
import com.mycompany.dentalclinic.ui.View;
import com.mycompany.dentalclinic.service.DentalClinicService;
import com.mycompany.dentalclinic.ui.Controller;
import com.mycompany.dentalclinic.data.CustomerFileDao;
import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.service.AppointmentService;
import com.mycompany.dentalclinic.service.CustomerService;
import com.mycompany.dentalclinic.service.ProfessionalService;
/**
 *
 * @author Eddy
 */
public class App {
//    Professional / Customer
    public static final String PROFESSIONAL_FILE_PATH = "professionals.txt";
    public static final String CUSTOMER_FILE_PATH = "customers.txt";
    
    public static void main(String[] args) throws DataException {
        
        ConsoleIO myIo = new ConsoleIO();
        View myView = new View(myIo);
        
        AppointmentsDao appointmentsDao = new AppointmentsFileDao();
        ProfessionalDao professionalDao = new ProfessionalFileDao(PROFESSIONAL_FILE_PATH);
        CustomersDao customerDao = new CustomerFileDao(CUSTOMER_FILE_PATH);
        
        ProfessionalService professionalService = new ProfessionalService(professionalDao);
        CustomerService customerService = new CustomerService(customerDao);
        AppointmentService appointmentService = new AppointmentService(appointmentsDao);
        DentalClinicService dentalClinicService = new DentalClinicService(appointmentsDao, professionalDao, customerDao);
        Controller controller = new Controller(myView, customerService, appointmentService, professionalService, dentalClinicService);
        
        controller.run(); 
    }
}
