package io.turq.turq.contstants;

public class APIErrors {
    // User
    public static final String EMAIL_ALREADY_USED = "The supplied email address has already been registered";
    public static final String USER_BAD_REQUEST = "One or more of the following required fields were not supplied: name, email";
    public static final String USER_ALREADY_EXISTS = "A user with that email address has already been registered";
    // Contest
    public static final String CONTEST_NOT_FOUND = "The specified contest does not exist";
    public static final String CONTEST_UPDATE_PERMISSION = "You are not authorized to update this contest";
    public static final String CONTEST_STATUS_INVALID = "The specified contest status is invalid";
    // Legislation
    public static final String LEGISLATION_NOT_FOUND = "The specified legislation does not exist";
    public static final String LEGISLATION_UPDATE_PERMISSION = "You are not authorized to update this piece of legislation";
}
