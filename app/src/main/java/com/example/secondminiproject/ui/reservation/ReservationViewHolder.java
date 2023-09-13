package com.example.secondminiproject.ui.reservation;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentReservationListDateBinding;
import com.example.secondminiproject.dto.Reservation;
import com.example.secondminiproject.ui.home.HomeFragment;

import java.util.Date;

public class ReservationViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ReservationViewHolder";
    private NavController navController;

    private TextView reservationListDay;
    private TextView reservationListTitle;
    private TextView reservationListNumber;
    private TextView reservationListReservationDay;
    private TextView reservationListStartDay;
    private Button reservationListReservationState;

    private Button reservationListWriteReview;


    public ReservationViewHolder(@NonNull View itemView, ReservationAdapter.OnItemClickListener onItemClickListener, NavController reservationNavController) {
        super(itemView);
        this.reservationListDay = itemView.findViewById(R.id.reservation_list_day);
        this.reservationListTitle = itemView.findViewById(R.id.reservation_list_title);
        this.reservationListNumber = itemView.findViewById(R.id.reservation_list_number);
        this.reservationListReservationDay = itemView.findViewById(R.id.reservation_list_reservation_day);
        this.reservationListStartDay = itemView.findViewById(R.id.reservation_list_start_day);
        this.reservationListReservationState = itemView.findViewById(R.id.reservation_list_reservation_state);

        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v,getAdapterPosition());
        });

        navController = reservationNavController;

        this.reservationListWriteReview = itemView.findViewById(R.id.btn_reservation_list_write_review);
        this.reservationListWriteReview.setOnClickListener(v -> {
            Log.i(TAG, "리뷰 작성 버튼 클릭 로그");

            navController.navigate(R.id.action_dest_reservation_list_to_dest_review_write);
        });
    }


    public void setData(Reservation reservation){
        this.reservationListDay.setText(reservation.getImsiReservationDate());
        this.reservationListTitle.setText(reservation.getProductName());
        this.reservationListNumber.setText(String.valueOf(reservation.getReservationNo()));
        this.reservationListReservationDay.setText(reservation.getImsiReservationDate());
        this.reservationListStartDay.setText(reservation.getStartDate());
        String reservationState=null;
        if(reservation.getReservationState()==1){
            reservationState ="발권완료";
        }else if(reservation.getReservationState()==2){
            reservationState ="예약완료";
        }else if(reservation.getReservationState()==3){
            reservationState ="취소중";
        }else if(reservation.getReservationState()==3){
            reservationState ="취소완료";
        }
        this.reservationListReservationState.setText(reservationState);
    }
}
