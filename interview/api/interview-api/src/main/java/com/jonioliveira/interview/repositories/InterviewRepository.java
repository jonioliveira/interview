package com.jonioliveira.interview.repositories;

import com.jonioliveira.interview.models.Interview;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class InterviewRepository implements PanacheRepositoryBase<Interview, Long> {

    public List<Interview> findByDate(Date date){
        return find("startDate", date).list();
    }

    public List<Interview> findByCandidateId(int candidateId){
        return find("candidateId", candidateId).list();
    }

    public List<Interview> findByInterviewerId(int interviewerId){
        return find("interviewerId", interviewerId).list();
    }
}
