package com.coffekyun.cinema.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "studios")
public class Studio {

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


    @OneToMany(mappedBy = "studio")
    private List<SeatDetail> seatDetails;

    private String name;

    @Column(name = "status", columnDefinition = "boolean default false")
    private Boolean status;

    @Temporal(TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Temporal(TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

}
