package com.jonioliveira.interview.repositories;

import com.jonioliveira.interview.models.Slot;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class SlotsRepository implements PanacheRepositoryBase<Slot, Integer> {

    public List<Slot> findByDate(Date date){
        return find("date(startDate)", date).stream().sorted((o1, o2) -> {
            if (o1.getStartDate().before(o2.getStartDate()) ){
                return -1;
            } else if (o1.getStartDate().after(o2.getStartDate())){
                return 1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
    }

    public long findByStartDateAndEndDateByUser(String startDate, String endDate, int interviewerId){
        return find("" +
                "to_char(startDate, 'YYYY-MM-DD HH24:MI:SS') LIKE ?1 " +
                "and to_char(endDate, 'YYYY-MM-DD HH24:MI:SS') LIKE ?2 " +
                "and interviewerId = ?3", startDate, endDate, interviewerId).count();
    }

    public List<Slot> findByDateAndUser(Date date, int id){
        return find("date(startDate) = ?1 and interviewerId = ?2 ", date, id).list();
    }

    public long countByDate(Date date){
        return find("date(startDate)", date).count();
    }

}
