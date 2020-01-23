/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.serviceTest;

import com.mycompany.dentalclinic.dataTest.AppointmentsFileDaoTestStub;
import com.mycompany.dentalclinic.dataTest.CustomerFileDaoTestStub;
import com.mycompany.dentalclinic.dataTest.ProfessionalFileDaoTestStub;
import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.Customer;
import com.mycompany.dentalclinic.models.Professional;
import com.mycompany.dentalclinic.models.SpecialtyType;
import static com.mycompany.dentalclinic.models.SpecialtyType.DENTIST;
import com.mycompany.dentalclinic.service.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Eddy
 */
public class DentalClinicServiceTest {

    private DentalClinicService dentalClinicService = new DentalClinicService(new AppointmentsFileDaoTestStub(),
            new ProfessionalFileDaoTestStub(), new CustomerFileDaoTestStub());

     Appointment appointment1 = new Appointment(23, "Buzek", SpecialtyType.DENTIST, LocalTime.of(2, 30), LocalTime.of(3, 30), new BigDecimal("100.00"), "Test Data");
     Appointment appointment2 = new Appointment(24, "Stebbing", SpecialtyType.HYGIENIST, LocalTime.of(7, 30), LocalTime.of(8, 30), new BigDecimal("150.00"), "Test Data");
     Appointment appointment3 = new Appointment(25, "Robins", SpecialtyType.HYGIENIST, LocalTime.of(9, 30), LocalTime.of(10, 30), new BigDecimal("175.00"), "Test Data");
        
   
    
    public DentalClinicServiceTest() {
    }

    @Test
    public void testFindByProLastNameAndDate() throws Exception {
        
    }

    @Test
    public void testCalculateCost() {
        assertEquals(BigDecimal.valueOf(100.00), dentalClinicService.calculateCost(appointment1));
    }

    @Test
    public void testCreateNewAppointment() {
  
        try {
            assertNotNull(dentalClinicService.createNewAppointment(appointment3));
            fail();
        } catch (IllegalArgumentException ex) {
            // this is a good thing
        }
    }

    @Test
    public void testAppointmentAlreadyExists() {
        
    }

}
