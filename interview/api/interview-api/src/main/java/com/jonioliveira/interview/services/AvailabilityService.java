package com.jonioliveira.interview.services;

import com.jonioliveira.interview.exceptions.AvailabilityNotFoundException;
import com.jonioliveira.interview.models.Availability;
import com.jonioliveira.interview.repositories.AvailabilityRepository;
import com.jonioliveira.interview.resources.models.AddAvailabilityModel;
import com.jonioliveira.interview.resources.models.GetByDateModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AvailabilityService {

    @Inject
    private AvailabilityRepository repository;

    @Transactional
    public Availability addAvailability(AddAvailabilityModel request) {
        Availability availability = new Availability(request.getStartDate(), request.getEndDate(), request.getInterviewerId());
        repository.persist(availability);
        return availability;
    }

    public List<Availability> getAllAvailability() throws AvailabilityNotFoundException {
        return Optional.ofNullable(repository.findAll().list()).orElseThrow(AvailabilityNotFoundException::new);
    }

    public List<Availability> getByInterviewerId(int id) throws AvailabilityNotFoundException{
        return Optional.ofNullable(repository.findByInterviewerId(id)).orElseThrow(AvailabilityNotFoundException::new);
    }

    public List<Availability> getByStartDate(GetByDateModel request) throws AvailabilityNotFoundException {
        return Optional.ofNullable(repository.findByDate(request.getStartDate())).orElseThrow(AvailabilityNotFoundException::new);
    }
}
