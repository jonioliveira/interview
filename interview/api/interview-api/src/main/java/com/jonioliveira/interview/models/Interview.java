package com.jonioliveira.interview.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "interview")
public class Interview extends PanacheEntity {
    @Id
    @SequenceGenerator(name="interview_id_seq",sequenceName="interview_id_seq", initialValue=0, allocationSize=50)
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

    @Column(name = "candidateId")
    private int candidateId;

    public Interview() {
        //used for Panache
    }

    public Interview(Date startDate, Date endDate, int interviewerId, int candidateId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.interviewerId = interviewerId;
        this.candidateId = candidateId;
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
}
