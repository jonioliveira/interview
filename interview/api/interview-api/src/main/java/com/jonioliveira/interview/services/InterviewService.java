package com.jonioliveira.interview.services;

import com.jonioliveira.interview.exceptions.InterviewNotFoundException;
import com.jonioliveira.interview.models.Availability;
import com.jonioliveira.interview.models.Interview;
import com.jonioliveira.interview.repositories.AvailabilityRepository;
import com.jonioliveira.interview.repositories.InterviewRepository;
import com.jonioliveira.interview.resources.models.AddInterviewModel;
import com.jonioliveira.interview.resources.models.GetByDateModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InterviewService {

    @Inject
    AvailabilityRepository availabilityRepository;

    @Inject
    InterviewRepository interviewRepository;

    public Interview addInterview(AddInterviewModel request) throws Exception {
        Interview interview = new Interview(
                request.getStartDate(), request.getEndDate(), request.getInterviewerId(), request.getCandidateId());
        interviewRepository.persist(interview);

        if (interviewRepository.isPersistent(interview)) {
            Availability availability = availabilityRepository.findById((long) request.getAvailabilityId());
            availabilityRepository.delete(availability);
        } else {
            throw new Exception("Error adding interview");
        }
        return interview;
    }

    public List<Interview> getInterviewsByDate(GetByDateModel request) throws InterviewNotFoundException {
        return Optional.ofNullable(interviewRepository.findByDate(request.getStartDate())).orElseThrow(InterviewNotFoundException::new);

    }
}
