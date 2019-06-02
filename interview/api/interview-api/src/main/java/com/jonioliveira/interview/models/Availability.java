package com.jonioliveira.interview.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "availability")
public class Availability extends PanacheEntity {

    @Id
    @SequenceGenerator(name="availability_id_seq",sequenceName="availability_id_seq", initialValue=0, allocationSize=50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "interviewerId")
    private int interviewerId;

    public Availability() {
        //used for Panache
    }

    public Availability(Date startDate, Date endDate, int interviewerId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.interviewerId = interviewerId;
    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(int interviewerId) {
        this.interviewerId = interviewerId;
    }
}
