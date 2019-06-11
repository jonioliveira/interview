package com.jonioliveira.interview.repositories;

import com.jonioliveira.interview.models.Slot;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SlotsRepository implements PanacheRepositoryBase<Slot, Long> {
}
