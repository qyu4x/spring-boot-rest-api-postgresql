package com.coffekyun.cinema.service;

import com.coffekyun.cinema.dto.OrderRequest;
import com.coffekyun.cinema.dto.SeatDetailRequest;
import com.coffekyun.cinema.dto.SeatDetailResponse;
import com.coffekyun.cinema.entity.OrderDetail;
import com.coffekyun.cinema.entity.Seat;
import com.coffekyun.cinema.entity.SeatDetail;
import com.coffekyun.cinema.entity.Studio;
import com.coffekyun.cinema.exception.DataAlreadyExistsException;
import com.coffekyun.cinema.exception.DataNotFoundException;
import com.coffekyun.cinema.exception.ValidationException;
import com.coffekyun.cinema.repository.SeatDetailRepository;
import com.coffekyun.cinema.repository.SeatRepository;
import com.coffekyun.cinema.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatDetailServiceImpl implements SeatDetailService{
    @Autowired
    private SeatDetailRepository seatDetailRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private StudioRepository studioRepository;

    @Override
    public SeatDetailResponse addSeatAndStudio(SeatDetailRequest seatDetailRequest) {

        Optional<Seat> seat = seatRepository.findById(seatDetailRequest.getSeatCode());
        Optional<Studio> studio = studioRepository.findById(seatDetailRequest.getStudioName());
        if (seat.isEmpty() || studio.isEmpty()) {
            throw new DataNotFoundException("Oops studio data or seat not found");
        }

        SeatDetail studioAndSeat = seatDetailRepository.findByStudioAndSeat(
                Studio.builder()
                        .id(seatDetailRequest.getStudioName()).build(),
                Seat.builder()
                        .id(seatDetailRequest.getSeatCode())
                        .build()
        );
        if (studioAndSeat != null) {
            throw new DataAlreadyExistsException("Oops studio data and seats have been entered");
        } else {
            SeatDetail seatDetail = SeatDetail.builder()
                    .studio(studio.get())
                    .seat(seat.get())
                    .build();

            seatDetailRepository.addStudioAndSeat(seatDetail);

            SeatDetail responseStudioAndSeat = seatDetailRepository.findByStudioAndSeat(
                    Studio.builder()
                            .id(seatDetail.getStudio().getName()).build(),
                    Seat.builder()
                            .id(seatDetail.getSeat().getSeatCode())
                            .build()
            );

            return SeatDetailResponse.builder()
                    .studioName(responseStudioAndSeat.getStudio().getName())
                    .seatCode(responseStudioAndSeat.getSeat().getSeatCode())
                    .status(responseStudioAndSeat.getStatus())
                    .build();
        }

    }

    @Override
    public List<SeatDetailResponse> getSeatIfAvailable(Boolean status) {
        List<SeatDetail> seatDetailsList =  seatDetailRepository.findByStatus(status);
        List<SeatDetailResponse> seatDetailResponses = new ArrayList<>();
        seatDetailsList.stream()
                .forEach(seatDetail -> {
                    SeatDetailResponse seatDetailResponse = SeatDetailResponse.builder()
                            .studioName(seatDetail.getStudio().getName())
                            .seatCode(seatDetail.getSeat().getSeatCode())
                            .status(seatDetail.getStatus())
                            .build();
                    seatDetailResponses.add(seatDetailResponse);
                });

        return seatDetailResponses;
    }

    @Transactional
    @Override
    public Boolean checkSeatAvailability(OrderRequest orderRequest) {

        orderRequest.getOrderDetailRequests().forEach(seatDetail -> {
           seatDetail.getOrderSeatRequests().forEach(orderSeatRequest -> {
               if (seatDetailRepository.findBySeatCodeAndStudioName(orderSeatRequest.getSeatCode()
                       , orderSeatRequest.getStudioName()).isEmpty()) {
                   throw new DataNotFoundException("Oops seat or studio not found");
               }

               List<SeatDetail> seatCodeAndStudio = seatDetailRepository.findBySeatCodeAndStudioName(orderSeatRequest.getSeatCode(),
                       orderSeatRequest.getStudioName());

               seatCodeAndStudio.forEach(seatAndStudio -> {
                   if (seatAndStudio.getStatus().equals(false)) {
                       throw new DataAlreadyExistsException("the seat with the name " + seatAndStudio.getSeat().getId() + " and studio " + seatAndStudio.getStudio().getName() +" is already filled");
                   }
               });
           });

        });

        return true;
    }

    @Override
    public Boolean checkNumberOfSeat(List<OrderDetail> orderDetails, OrderRequest orderRequest) {

        for (int i = 0; i < orderDetails.size(); i++) {
            Integer quantity = orderDetails.get(i).getQuantity();
            Integer numberOfSeat = orderRequest.getOrderDetailRequests()
                    .get(i).getOrderSeatRequests().size();

            if (!quantity.equals(numberOfSeat)) {
                throw new ValidationException("Seat can not be more than quantity");
            }
        }

        return true;
    }


}
