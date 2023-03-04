package com.manish.example.beans;

public class ErrorResponse extends JsonResponse{
    private static class Error {
        String error;
        public Error(String message) {
            this.error = message;
        }
    }
    private ErrorResponse(Error error) {
        this(404, error);
    }

    private ErrorResponse(int errorCode, Error error) {
        super(errorCode, error);
    }

    public static ErrorResponse message(String message) {
        return new ErrorResponse(new Error(message));
    }

    public static ErrorResponse message(int errorCode, String message) {
        return new ErrorResponse(errorCode, new Error(message));
    }

}
