package com.majesty.pet_care.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.majesty.pet_care.service.appointment.IAppointmentService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentStatusUpdater {
    private final IAppointmentService appointmentService;

    /*
     * In the cron expression "0 0/5 * 1/1 * ?", each field represents
     * a different unit of time.
     * Here's the breakdown:
     * 
     * Seconds: "0" - The task will run at 0 seconds of the minute.
     * Minutes: "0/5" - The task will run every 5 minutes, starting from the 0th
     * minute.
     * Hours: Any * - The task can run at any hour.
     * Day of month: "1/1" - The task can run on any day of the month.
     * Month: Any * - The task can run in any month.
     * Day of week: Any - The task can run on any day of the week.
     * As a result, the task will run every 5 minutes, starting from the 0th minute,
     * every
     * hour, every day of the month, every month, and every day of the week.
     */

    @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void automateAppointmentStatusUpdate() {
        List<Long> appointmentIds = appointmentService.getAppointmentIds();
        for (Long appointmentId : appointmentIds) {
            appointmentService.setAppointmentStatus(appointmentId);
        }
    }
}
