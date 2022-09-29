package com.coffekyun.cinema.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "schedules")
public class Schedule {

    @Id
    private String id;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "films_id",
            referencedColumnName = "id"
    )
    private Movie movie;

    @OneToMany(mappedBy = "schedule")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "schedule")
    private List<SeatDetail> seatDetails;

    private Date showDate;

    private Date startTime;

    private Date endTime;

    private BigDecimal price;

    @Temporal(TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Temporal(TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
