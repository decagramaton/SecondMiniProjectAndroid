package com.example.secondminiproject.service;

import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.dto.Review;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReservationService {
        @GET("reservation/setNewReservationInfo")
        Call<Void> setNewReservationInfo(@Query("userNo") int userNo, @Query("productNo") int productNo,
                                         @Query("adultNumber") int adultNumber, @Query("childNumber") int childrenNumber);

}