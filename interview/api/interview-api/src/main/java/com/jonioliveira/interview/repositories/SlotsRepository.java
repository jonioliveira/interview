package com.jonioliveira.interview.repositories;

import com.jonioliveira.interview.models.Slot;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class SlotsRepository implements PanacheRepositoryBase<Slot, Long> {

    public List<Slot> findByDate(Date date){
        return find("startDate", date).list();
    }

    public List<Slot> findByDateAndUser(Date date, int id){
        return find("startDate = ?1 and interviewerId = ?2 ", date, id).list();
    }


}
