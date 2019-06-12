package com.jonioliveira.interview.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.usertype.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "slot")
public class Slot extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startDate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "interviewerId")
    private int interviewerId;

    @Column(name = "candidateId")
    private int candidateId;

    @Column(name = "status")
    private int status;


    public Slot(Date startDate, Date endDate, int interviewerId, int candidateId, SlotStatus status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.interviewerId = interviewerId;
        this.candidateId = candidateId;
        this.status = status.getValue();
    }

    public Slot(Date startDate, Date endDate, int interviewerId, int status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.interviewerId = interviewerId;
        this.status = status;
    }

    public Slot() {
        //used for panache
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

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public SlotStatus getStatus() {
        return SlotStatus.fromValue(status);
    }

    public void setStatus(SlotStatus status) {
        this.status = status.getValue();
    }
}
