package com.coffekyun.cinema.dto;

import com.coffekyun.cinema.entity.Schedule;
import com.coffekyun.cinema.entity.Studio;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;


@Data
@Builder
public class MovieResponse {

    private String id;

    private String title;

    private Boolean showStatus;

    private Integer duration;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    private String description;

    private String genre;

    private String country;

    private String language;

    private Boolean active;

    private List<ScheduleResponse> schedules;

    private List<StudioResponse> studios;

}
