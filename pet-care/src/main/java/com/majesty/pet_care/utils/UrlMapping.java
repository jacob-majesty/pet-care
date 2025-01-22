package com.majesty.pet_care.utils;

public class UrlMapping {

    public static final String API = "/api/v1";
    public static final String USERS = API + "/users";
    public static final String REGISTER_USER = "/register";
    public static final String UPDATE_USER = "/update/{userId}";
    public static final String FOUND = "Found";
    public static final String GET_USER_BY_ID = "user/{userId}";
    public static final String DELETE_USER_BY_ID = "/delete/{userId}";
    public static final String GET_ALL_USERS = "/all-users";

    public static final String Count_All_USERS = "/count/users";
    public static final String Count_All_VETS = "/count/veterinarians";
    public static final String Count_All_PATIENTS = "/count/patients";
    public static final String AGGREGATE_USERS = "/aggregated-users";

    /* Appointment API */
    public static final String APPOINTMENTS = API + "/appointments";
    public static final String ALL_APPOINTMENTS = "/all";
    public static final String GET_APPOINTMENT_BY_NO = "/appointment/{appointmentNo}";
    public static final String GET_APPOINTMENT_BY_ID = "/appointment/{id}/fetch/appointment";
    public static final String BOOK_APPOINTMENT = "/book-appointment";
    public static final String UPDATE_APPOINTMENT = "/appointment/{id}/update";
    public static final String DELETE_APPOINTMENT_BY_ID = "/appointment/{id}/delete";
    public static final String CANCEL_APPOINTMENT = "/appointment/{id}/cancel";
    public static final String APPROVE_APPOINTMENT = "/appointment/{id}/approve";
    public static final String DECLINE_APPOINTMENT = "/appointment/{id}/decline";
    public static final String COUNT_APPOINTMENT = "/count/appointments";

    /* Pet API */
    public static final String PETS = API + "/pets";
    public static final String UPDATE_PET = "/pet/{petId}/update";
    public static final String SAVE_PETS_FOR_APPOINTMENT = "/save-pets/";
    public static final String GET_PET_BY_ID = "/pet/{petId}/pet";
    public static final String DELETE_PET_BY_ID = "/pet/{petId}/delete";
    public static final String GET_PET_TYPES = "/get-types";
    public static final String GET_PET_COLORS = "/get-pet-colors";
    public static final String GET_PET_BREEDS = "/get-pet-breeds";

    /* Photo API */
    public static final String PHOTOS = API + "/photos";
    public static final String UPLOAD_PHOTO = "/photo/upload";
    public static final String UPDATE_PHOTO = "/photo/{photoId}/update";;
    public static final String DELETE_PHOTO = "/photo/{photoId}/user/{userId}/delete";
    public static final String GET_PHOTO_BY_ID = "/photo/{photoId}/photo";

    /* Review API */
    public static final String REVIEWS = API + "/reviews";
    public static final String SUBMIT_REVIEW = "/submit-review";
    public static final String GET_USER_REVIEWS = "/user/{userId}/reviews";
    public static final String UPDATE_REVIEW = "/review/{reviewId}/update";
    public static final String DELETE_REVIEW = "/review/{reviewId}/delete";
    public static final String GET_AVERAGE_RATING = "/vet/{vetId}/get-average-rating";

    /* Veterinarian API */
    public static final String VETERINARIANS = API + "/veterinarians";
    public static final String GET_ALL_VETERINARIANS = "/get-all-veterinarians";
    public static final String SEARCH_VETERINARIAN_FOR_APPOINTMENT = "/search-veterinarian";
    public static final String GET_ALL_SPECIALIZATIONS = "vet/get-all-specialization";
    public static final String VET_AGGREGATE_BY_SPECIALIZATION = "vet/get-by-specialization";

    /* Auth API */

    public static final String CHANGE_PASSWORD = "/user/{userId}/change-password";
    public static final String AUTH = "/api/v1/auth";
    public static final String LOGIN = "/login";
    public static final String REQUEST_PASSWORD_RESET = "/request-password-reset";
    public static final String RESET_PASSWORD = "/reset-password";

}
