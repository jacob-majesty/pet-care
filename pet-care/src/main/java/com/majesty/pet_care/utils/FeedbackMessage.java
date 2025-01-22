package com.majesty.pet_care.utils;

public class FeedbackMessage {

    public static final String CREATE_SUCCESS = "Resource registered successfully";
    public static final String UPDATE_SUCCESS = "Resource updated successfully";
    public static final String FOUND = "Resource Found";
    public static final String DELETE_SUCCESS = "Resource Deleted successfully";
    public static final String NOT_FOUND = "Resource Not Found";
    public static final String ALREADY_APPROVED = "Sorry, this appointment is already approved";
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String SENDER_RECIPIENT_NOT_FOUND = "sender or recipient not found";
    public static final String ERROR = "error occurred";
    public static final String SERVER_ERROR = "internal server error";
    public static final String NOT_ALLOWED = "Sorry, only patients that have a completed appointments with this veterinarian can leave a review";
    public static final String ALREADY_REVIEWED = "You have already rated this veterinarian, you may edit your previous review";
    public static final String CANNOT_REVIEW = "Veterinarians can not review themselves";
    public static final String VET_OR_PATIENT_NOT_FOUND = "Veterinarian or Patient not found";
    public static final String NO_VETS_AVAILABLE = "No veterinarians available for the requested date and time";
    public static final String APPOINTMENT_CANNOT_BE_CANCELLED = "Error cancelling appointment";
    public static final String APPOINTMENT_ALREADY_APPROVED = "This appointment is already approved";
    public static final String ADDED_SUCCESS = "Pet added to appointment successfully";

    /* Password API */
    public static final String EMPTY_PASSWORD = "All fields are required";
    public static final String INCORRECT_PASSWORD = "Incorrect password";
    public static final String PASSWORD_MISMATCH = "Password confirmation mismatch";
    public static final String AUTHENTICATION_SUCCESS = "Authentication Successful";
    public static final String ACCOUNT_DISABLED = "Sorry, your account is disabled, please contact the service desk";
    public static final Object INVALID_PASSWORD = "Invalid username or password";
    public static final String UNAUTHORIZED = "Please, login to submit a review.";

    /* Token API */
    public static final String INVALID_TOKEN = "INVALID";
    public static final String TOKEN_ALREADY_VERIFIED = "VERIFIED";
    public static final String EXPIRED_TOKEN = "EXPIRED";
    public static final String VALID_VERIFICATION_TOKEN = "VALID";
    public static final String TOKEN_VALIDATION_ERROR = "Token validation error";
    public static final String TOKEN_SAVED_SUCCESS = "Verification token saved successfully";
    public static final String TOKEN_DELETE_SUCCESS = "User token deleted successfully";
    public static final String INVALID_VERIFICATION_TOKEN = "Invalid verification token";
    public static final String NEW_VERIFICATION_TOKEN_SENT = "A new verification link has been sent to your email. Please check to complete your registration.";

    /* Photo API */
    public static final String PHOTO_UPLOAD_SUCCESS = "Photo uploaded successfully";
    public static final String PHOTO_UPDATE_SUCCESS = "Photo updated successfully";;
    public static final String PHOTO_REMOVE_SUCCESS = "Photo updated successfully";

    /* Role */
    public static final String ROLE_NOT_FOUND = "Role not found";

}
