package org.ditebogo.fullstackmvc.Controllers;

import org.ditebogo.fullstackmvc.*;
import org.ditebogo.fullstackmvc.DTO.PeopleRequest;
import org.ditebogo.fullstackmvc.DTO.PeopleResponse;
import org.ditebogo.fullstackmvc.Services.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService1)
    {
        this.peopleService = peopleService1;
    }

    // create a person
    @PostMapping
    public ResponseEntity<PeopleResponse> createPerson(
            @RequestBody PeopleRequest peopleRequest
            )
    {
        PeopleResponse peopleResponse = peopleService.createPerson(peopleRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(peopleResponse);
    }

    // get all records
    @GetMapping
    public ResponseEntity<List<PeopleResponse>> getAllPeople()
    {
        return ResponseEntity.ok(peopleService.getAllPeople());
    }

    // read one by Id
    @GetMapping("/{id}")
    public ResponseEntity<PeopleResponse> getPerson(@PathVariable Long id, @Valid @RequestBody PeopleRequest peopleRequest)
    {
        return  ResponseEntity.ok(peopleService.getPersonById(id));
    }

    // update person details
    @PutMapping("/{id}")
    public ResponseEntity<PeopleResponse> updatePerson(
            @PathVariable Long id,
            @Valid @RequestBody PeopleRequest peopleRequest
    )
    {
        return  ResponseEntity.ok(peopleService.updatePerson(id, peopleRequest));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletePerson(@PathVariable Long id)
    {
        peopleService.deletePerson(id);

        return  ResponseEntity.noContent().build();
    }
}
