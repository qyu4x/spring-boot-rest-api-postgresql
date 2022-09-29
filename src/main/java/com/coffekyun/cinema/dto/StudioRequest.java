package com.coffekyun.cinema.dto;

import com.coffekyun.cinema.entity.Movie;
import com.coffekyun.cinema.entity.SeatDetail;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
public class StudioRequest {

    private String name;

    private Boolean status;

    private Date createdAt;

    private Date updatedAt;

}
