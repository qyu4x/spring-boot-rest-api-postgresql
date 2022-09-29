package com.coffekyun.cinema.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
public class StudioResponse {

    private String id;

    private String movieId;

    private String name;

    private Boolean status;

    private Date createdAt;

    private Date updatedAt;
}
