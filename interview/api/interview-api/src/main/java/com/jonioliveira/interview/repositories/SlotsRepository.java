package com.jonioliveira.interview.repositories;

import com.jonioliveira.interview.models.Slot;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class SlotsRepository implements PanacheRepositoryBase<Slot, Integer> {

    public List<Slot> findByDate(Date date){
        return find("date(startDate)", date).list();
    }

    public long findByStartDateAndEndDate(String startDate, String endDate){
        return find("to_char(startDate, 'YYYY-MM-DD HH24:MI:SS') LIKE ?1 and to_char(endDate, 'YYYY-MM-DD HH24:MI:SS') = ?2", startDate, endDate).count();
    }

    public List<Slot> findByDateAndUser(Date date, int id){
        return find("date(startDate) = ?1 and interviewerId = ?2 ", date, id).list();
    }

    public long countByDate(Date date){
        return find("date(startDate)", date).count();
    }


}
