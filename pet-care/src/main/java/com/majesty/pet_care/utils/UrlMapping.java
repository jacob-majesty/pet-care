package com.majesty.pet_care.utils;

public class UrlMapping {
    
    public static final String API = "/api/v1";
    public static final String USERS = API+"/users";
    public static final String REGISTER_USER = "/register";
    public static final String UPDATE_USER = "/update/{userId}";
    public static final String FOUND = "Found";
    public static final String GET_USER_BY_ID = "user/{userId}";
    public static final String DELETE_USER_BY_ID = "/delete/{userId}";
    public static final String GET_ALL_USERS = "/all-users";

    /* Appointment API */  
    public static final String APPOINTMENTS = API+"/appointments";
    public static final String ALL_APPOINTMENTS = "/all";
    public static final String APPOINTMENTS_BY_NO = "/appointment/{appointmentNo}";
    public static final String APPOINTMENT_BY_ID = "/appointment/{id}/appointment";
    public static final String BOOK_APPOINTMENT = "/book-appointment";
    public static final String UPDATE_APPOINTMENT = "/appointment/{id}/update";
    public static final String DELETE_APPOINTMENT = "/appointment/{id}/delete";
}
