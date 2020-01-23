/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.service;

import com.mycompany.dentalclinic.data.AppointmentsDao;
import com.mycompany.dentalclinic.data.CustomersDao;
import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.data.ProfessionalDao;
import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.Professional;
import com.mycompany.dentalclinic.ui.Validations;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Eddy
 */
public class DentalClinicService {

    private AppointmentsDao appointmentDao;
    private ProfessionalDao professionalDao;
    private CustomersDao customersDao;

    public DentalClinicService(AppointmentsDao appointmentDao, ProfessionalDao professionalDao, CustomersDao customersDao) {
        this.appointmentDao = appointmentDao;
        this.professionalDao = professionalDao;
        this.customersDao = customersDao;
    }

//    public Appointment searchOpenTimeSlots(LocalDate date, Professional professional, LocalTime startTime, LocalTime endTime) {
//        TimeSlot hours = getDayHours(date);
//        TimeSlot lunchTime = new TimeSlot(LocalTime.parse("12:30"), LocalTime.parse("13:00"));
//        try {
//            List<Appointment> allAppointments = findByProLastNameAndDate(date, professional.getProfessionalLastName()).getValue();
//
//            List<TimeSlot> filledTimeSlots = allAppointments.stream()
//                    .map(a -> new TimeSlot(a.getStart(), a.getEnd()))
//                    .collect(Collectors.toList());
//
//
//            List<TimeSlot> openTimeSlots = new ArrayList<>();
//
//            //fully open day
//            if (filledTimeSlots.isEmpty()) {
//                openTimeSlots.add(new TimeSlot(hours.getStartTime(), lunchTime.getStartTime()));
//                openTimeSlots.add(new TimeSlot(lunchTime.getEndTime(), hours.getEndTime()));
//            // day with scheduling already.
//            } else {
//                TimeSlot prevTimeSlot = new TimeSlot(null, null);
//                TimeSlot nextTimeSlot = new TimeSlot(null, null);
//                if (hours != null) {
//                    filledTimeSlots.add(lunchTime);
//                }
//
//                filledTimeSlots = filledTimeSlots.stream()
//                        .sorted((a, b) -> a.getStartTime().compareTo(b.getStartTime()))
//                        .collect(Collectors.toList());
//
//                for (TimeSlot filledTimeSlot : filledTimeSlots) {
//                    // get minutes between certain slots.
//                    long minutesBetween = Duration.between();
//                    if(minutesBetween >= professional.getType().getMinVisit()){
//                        openSlots.add()
//                    } else {
//                        
//                        // use for loop to go through filledTimeSlots
//                        i++;
//                        if(i < filledSlots.size()){
//                            
//                        }
//                    }
//                }
//
//            }
//
//        } catch (DataException ex) {
//            return null;
//        }
//        return null;
//    }
    public serviceResponse<List<Appointment>> findByProLastNameAndDate(LocalDate date, String lastName) throws DataException {
        serviceResponse<List<Appointment>> response = new serviceResponse<>();

        List<Appointment> readAppointments = appointmentDao.findByLocalDateAndProLast(date, lastName);
        if (readAppointments.isEmpty()) {
            response.addError("No Resulsts!");
        }
        return response;

    }

    private TimeSlot getDayHours(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        switch (day) {
            case SATURDAY:
                return new TimeSlot(LocalTime.parse("08:30"), LocalTime.parse("12:30"));
            case SUNDAY:
                return null;
            default:
                return new TimeSlot(LocalTime.parse("07:30"), LocalTime.parse("18:00"));

        }

    }

    public BigDecimal calculateCost(Appointment apptToAdd) {
        Response response = new Response();

        LocalTime start = apptToAdd.getStart();
        LocalTime end = apptToAdd.getEnd();
        String lastName = apptToAdd.getDentalProLastname();
        Professional professional = null;

        try {
            professional = professionalDao.findByProfessionalLastNameNumberTwo(lastName);
        } catch (DataException ex) {
            response.hasError();
        }
        if (start == null || end == null || professional == null) {
            return BigDecimal.ZERO;
        }
        long m = start.until(end, ChronoUnit.MINUTES);
        BigDecimal minutes = new BigDecimal(m);
        BigDecimal hours = minutes.divide(BigDecimal.valueOf(m), 2, RoundingMode.HALF_UP);
        return professional.getHourlyRate()
                .multiply(hours)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Response createNewAppointment(Appointment appointment) {
        Response response = new Response();
        if (Validations.appointmentInOfficeHours(appointment)) {
            response.addError("Error: Please Check Appointment Office Hours!");
        }
        if (!Validations.lengthOfAppointmentCheck(appointment)) {
            response.addError("Error: Please Check Length Of Appointment!");
        }
        if (!appointmentAlreadyExists(appointment)) {
            response.addError("Error: Appointment Already Exists!");
        }
        if (response.hasError()) {
            return response;
        }
        try {
            appointmentDao.addAppointment(appointment, appointment.getDate());
        } catch (DataException ex) {
            response.hasError();
        }
        return response;
    }

    // Validation for if Appointment Already exist.
    public boolean appointmentAlreadyExists(Appointment appointment) {
        Response response = new Response();
        boolean duplicate = true;
        List<Appointment> allAppointments;

        try {
            allAppointments = appointmentDao.findByDate(appointment.getDate());
        } catch (DataException ex) {
            return response.hasError();
        }
        for (Appointment a : allAppointments) {
            if (appointment.getCustomerId() == a.getCustomerId()
                    && a.getDentalProLastname().equals(appointment.getDentalProLastname())
                    && a.getDate().equals(appointment.getDate()))
//                    && a.getStart().compareTo(appointment.getStart()) != 0
//                    && a.getEnd().compareTo(appointment.getEnd()) != 0
//                    && a.getTotalCost().equals(appointment.getTotalCost())
//                    && a.getNotes().equals(appointment.getNotes())) 
            {
                duplicate = false;
            }
        }
        return duplicate;
    }

}
