package com.movie.bookng.data;

public class PaymentDTO {
    private Integer BookingId;
    private String mobileNo;
    private Integer paymentAmount;

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getBookingId() {
        return BookingId;
    }

    public void setBookingId(Integer bookingId) {
        BookingId = bookingId;
    }



}
