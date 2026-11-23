package org.ditebogo.fullstackmvc.Services;

import org.ditebogo.fullstackmvc.DTO.PeopleRequest;
import org.ditebogo.fullstackmvc.DTO.PeopleResponse;
import org.ditebogo.fullstackmvc.Entities.People;
import org.ditebogo.fullstackmvc.Repository.*;
import org.ditebogo.fullstackmvc.Exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository)
    {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public PeopleResponse createPerson(PeopleRequest peopleRequest)
    {
        People people = new People();
        people.setName((peopleRequest.name()));
        people.setEmail(peopleRequest.email());

        People savedPeople = peopleRepository.save(people);

        return mapToResponse(savedPeople);
    }

    //get all people
    @Transactional(readOnly = true)
    public List<PeopleResponse> getAllPeople()
    {
        return peopleRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    //get one person by id
    @Transactional(readOnly = true)
    public PeopleResponse getPersonById(Long id)
    {
        People people = findPersonOrThrow(id);

        return  mapToResponse(people);
    }

    //Update
    @Transactional
    public PeopleResponse updatePerson(Long id, PeopleRequest peopleRequest)
    {
        People people = findPersonOrThrow(id);

        people.setName(peopleRequest.name());
        people.setEmail(peopleRequest.email());

        People updatedPerson = peopleRepository.save(people);

        return mapToResponse(updatedPerson);
    }

    @Transactional
    public void deletePerson(Long id) {

        People people = findPersonOrThrow(id);

        peopleRepository.delete(people);
    }

    private PeopleResponse mapToResponse (People people)
    {
        return new PeopleResponse(people.getId(),people.getName(),people.getEmail());
    }

    private People findPersonOrThrow(Long id)
    {
        return peopleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person with ID: " + id + " not found."));
    }
}
