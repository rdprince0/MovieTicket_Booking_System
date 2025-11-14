package com.movie.bookng.validator;

import com.movie.bookng.data.PaymentDTO;
import com.movie.bookng.data.ShowDTo;

public class PaymentValidator {

    public void  pValidator(PaymentDTO pDTO) {
        showIdValidation(pDTO.getBookingId());
        validateMobileNumber(pDTO.getMobileNo());
        amountValidate(pDTO.getPaymentAmount());

    }

    public static void showIdValidation(Integer bookigId) {
        if(bookigId == null || bookigId <= 0)
        {
            System.err.println("Show ID should not be null or less than zero");
            throw new IllegalArgumentException("Show ID should not be null or less than zero");
        }
    }
    public static void validateMobileNumber(String mobileNo) {
        if (mobileNo == null || mobileNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be empty.");
        }
        if (!mobileNo.matches("\\d{10}")) {
            throw new IllegalArgumentException("Mobile number must be exactly 10 digits.");
        }
    }
    public static void amountValidate(Integer amount)  {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        if (amount > 400) {
            throw new IllegalArgumentException("Amount exceeds allowed limit");
        }
    }
}
