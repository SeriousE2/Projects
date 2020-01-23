package com.mycompany.dentalclinic.ui;

import com.mycompany.dentalclinic.data.AppointmentsDao;
import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.service.Response;
import com.mycompany.dentalclinic.models.Customer;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.Duration;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validations {

//Scheduling
//NEVER allow an Appointment outside of business hours.
//DENTIST: min Appointment is 15 minutes, max 3 hours
//HYGIENIST: min 30 minutes, max 2 hours
//ORTHODONTIST: min 15 minutes, max 1 hour
//ORAL_SURGEON: min 30 minutes, max 8 hours (may schedule over lunch if Appointment > 5 hours)
//Only one Appointment per Customer, per Specialty, per day. 
//   (A Customer could see a HYGIENIST in the morning and a DENTIST in the afternoon, but never two DENTISTs in a single day.)
//It is absolutely essential to prevent Dental Professionals from being double-booked.
    
    
    public static boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static boolean isNullOrWhitespace(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static boolean appointmentInOfficeHours(Appointment appointment) {

        if (appointment.getDate().getDayOfWeek() == null) {
            LocalDateTime earliestAppointment = LocalDateTime.of(appointment.getDate(), LocalTime.of(7, 30));
            LocalDateTime latestAppointment = LocalDateTime.of(appointment.getDate(), LocalTime.of(18, 00));
            return appointment.getStart().isBefore(earliestAppointment.toLocalTime()) 
                    || appointment.getEnd().isAfter(latestAppointment.toLocalTime());
        } else {
            switch (appointment.getDate().getDayOfWeek()) {
                case SUNDAY:
                    return false;
                case SATURDAY: {
                    LocalDateTime earliestAppointment = LocalDateTime.of(appointment.getDate(), LocalTime.of(8, 30));
                    LocalDateTime latestAppointment = LocalDateTime.of(appointment.getDate(), LocalTime.of(12, 30));
                    return appointment.getStart().isBefore(earliestAppointment.toLocalTime()) 
                            || appointment.getEnd().isAfter(latestAppointment.toLocalTime());

                }
                default: {
                    LocalDateTime earliestAppointment = LocalDateTime.of(appointment.getDate(), LocalTime.of(7, 30));
                    LocalDateTime latestAppointment = LocalDateTime.of(appointment.getDate(), LocalTime.of(18, 00));
                    return appointment.getStart().isBefore(earliestAppointment.toLocalTime()) 
                            || appointment.getEnd().isAfter(latestAppointment.toLocalTime());
                }

            }
        }
    }

    public static boolean lengthOfAppointmentCheck(Appointment appointment) {
        SpecialtyType professionalType;

        professionalType = appointment.getType();
        Duration amountOfTime = Duration.between(appointment.getStart(), appointment.getEnd());
        long totalMinutes = amountOfTime.toMinutes();

        switch (professionalType) {
            case DENTIST:
                return totalMinutes >= 15 && totalMinutes <= 180;
            case HYGIENIST:
                return totalMinutes >= 30 && totalMinutes <= 120;
            case ORTHODONTIST:
                return totalMinutes >= 15 && totalMinutes <= 60;
            case ORAL_SURGEON:
                return totalMinutes >= 30 && totalMinutes <= 480;
            default:
                return false;
        }
    }

}
