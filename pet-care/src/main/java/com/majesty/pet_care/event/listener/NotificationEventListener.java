package com.majesty.pet_care.event.listener;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.majesty.pet_care.email.EmailService;
import com.majesty.pet_care.event.AppointmentApprovedEvent;
import com.majesty.pet_care.event.AppointmentBookedEvent;
import com.majesty.pet_care.event.AppointmentDeclinedEvent;
import com.majesty.pet_care.event.PasswordResetEvent;
import com.majesty.pet_care.event.RegistrationCompleteEvent;
import com.majesty.pet_care.model.Appointment;
import com.majesty.pet_care.model.User;
import com.majesty.pet_care.service.token.IVerificationTokenService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationEventListener implements ApplicationListener<ApplicationEvent> {
    private final EmailService emailService;
    private final IVerificationTokenService tokenService;

    @Value("${frontend.base.url}")
    private String frontendBaseUrl;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        Object source = event.getSource();
        switch (event.getClass().getSimpleName()) {

            case "RegistrationCompleteEvent":
                if (source instanceof User) {
                    handleSendRegistrationVerificationEmail((RegistrationCompleteEvent) event);
                }
                break;

            case "AppointmentBookedEvent":
                if (source instanceof Appointment) {
                    try {
                        handleAppointmentBookedNotification((AppointmentBookedEvent) event);
                    } catch (MessagingException | UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;

            case "AppointmentApprovedEvent":
                if (source instanceof Appointment) {
                    try {
                        handleAppointmentApprovedNotification((AppointmentApprovedEvent) event);
                    } catch (MessagingException | UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;

            case "AppointmentDeclinedEvent":
                if (source instanceof Appointment) {
                    try {
                        handleAppointmentDeclinedNotification((AppointmentDeclinedEvent) event);
                    } catch (MessagingException | UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;

            case "PasswordResetEvent":
                PasswordResetEvent passwordResetEvent = (PasswordResetEvent) event;
                handlePasswordResetRequest(passwordResetEvent);
                break;

            default:
                break;
        }
    }

    private void handleSendRegistrationVerificationEmail(RegistrationCompleteEvent event) {
        User user = event.getUser();
        // Generate a token for the user
        String vToken = UUID.randomUUID().toString();
        // Save the token for the user
        tokenService.saveVerificationTokenForUser(vToken, user);
        // Build the verification url
        String verificationUrl = frontendBaseUrl + "/email-verification?token=" + vToken;
        try {
            sendRegistrationVerificationEmail(user, verificationUrl);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendRegistrationVerificationEmail(User user, String url)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Verify Your Email";
        String senderName = "Universal Pet Care";
        String mailContent = "<p> Hi, " + user.getFirstName() + ", </p>" +
                "<p>Thank you for registering with us," +
                "Please, follow the link below to complete your registration.</p>" +
                "<a href=\"" + url + "\">Verify your email</a>" +
                "<p> Thank you <br> Universal Pet Care Email Verification Service";
        emailService.sendEmail(user.getEmail(), subject, senderName, mailContent);
    }

    private void handleAppointmentBookedNotification(AppointmentBookedEvent event)
            throws MessagingException, UnsupportedEncodingException {
        Appointment appointment = event.getAppointment();
        User vet = appointment.getVeterinarian();
        sendAppointmentBookedNotification(vet, frontendBaseUrl);
    }

    private void sendAppointmentBookedNotification(User user, String url)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "New Appointment Notification";
        String senderName = "Universal Pet Care";
        String mailContent = "<p> Hi, " + user.getFirstName() + ", </p>" +
                "<p>You have a new appointment schedule:</p>" +
                "<a href=\"" + url + "\">Please, check the clinic portal to view appointment details.</a> <br/>" +
                "<p> Best Regards.<br> Universal Pet Care Service";
        emailService.sendEmail(user.getEmail(), subject, senderName, mailContent);
    }

    private void handleAppointmentApprovedNotification(AppointmentApprovedEvent event)
            throws MessagingException, UnsupportedEncodingException {
        Appointment appointment = event.getAppointment();
        User patient = appointment.getPatient();
        sendAppointmentApprovedNotification(patient, frontendBaseUrl);
    }

    private void sendAppointmentApprovedNotification(User user, String url)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Appointment Approved";
        String senderName = "Universal Pet Care Notification Service";
        String mailContent = "<p> Hi, " + user.getFirstName() + ", </p>" +
                "<p>Your appointment has been approved:</p>" +
                "<a href=\"" + url + "\">Please, check the clinic portal to view appointment details " +
                "and veterinarian information.</a> <br/>" +
                "<p> Best Regards.<br> Universal Pet Care";
        emailService.sendEmail(user.getEmail(), subject, senderName, mailContent);
    }

    private void handleAppointmentDeclinedNotification(AppointmentDeclinedEvent event)
            throws MessagingException, UnsupportedEncodingException {
        Appointment appointment = event.getAppointment();
        User patient = appointment.getPatient();
        sendAppointmentDeclinedNotification(patient, frontendBaseUrl);
    }

    private void sendAppointmentDeclinedNotification(User user, String url)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Appointment Not Approved";
        String senderName = "Universal Pet Care Notification Service";
        String mailContent = "<p> Hi, " + user.getFirstName() + ", </p>" +
                "<p>We are sorry, your appointment was not approved at this time,<br/> " +
                "Please, kindly make a reschedule for another date. Thanks</p>" +
                "<a href=\"" + url + "\">Please, check the clinic portal to view appointment details.</a> <br/>" +
                "<p> Best Regards.<br> Universal Pet Care";
        emailService.sendEmail(user.getEmail(), subject, senderName, mailContent);
    }

    private void handlePasswordResetRequest(PasswordResetEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        tokenService.saveVerificationTokenForUser(token, user);
        String resetUrl = frontendBaseUrl + "/reset-password?token=" + token;
        try {
            sendPasswordResetEmail(user, resetUrl);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to send password reset email", e);
        }
    }

    private void sendPasswordResetEmail(User user, String resetUrl)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request";
        String senderName = "Universal Pet Care";
        String mailContent = "<p>Hi, " + user.getFirstName() + ",</p>" +
                "<p>You have requested to reset your password. Please click the link below to proceed:</p>" +
                "<a href=\"" + resetUrl + "\">Reset Password</a><br/>" +
                "<p>If you did not request this, please ignore this email.</p>" +
                "<p>Best Regards.<br> Universal Pet Care</p>";
        emailService.sendEmail(user.getEmail(), subject, senderName, mailContent);
    }

}
