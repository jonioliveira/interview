package com.jonioliveira.interview.repositories;

import com.jonioliveira.interview.models.Availability;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class AvailabilityRepository implements PanacheRepositoryBase<Availability, Long>  {

    public List<Availability> findByDate(Date date){
        return find("startDate", date).list();
    }

    public List<Availability> findByInterviewerId(int interviewerId){
        return find("interviewerId", interviewerId).list();
    }
}
