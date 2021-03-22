package ba.unsa.etf.nwt.adopt_service.controllers;

import ba.unsa.etf.nwt.adopt_service.models.AdoptionRequest;
import ba.unsa.etf.nwt.adopt_service.responses.ResponseMessage;
import ba.unsa.etf.nwt.adopt_service.services.AdoptionRequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class AdoptionRequestController {
    private final AdoptionRequestService adoptionRequestService;

    @GetMapping("/adoption-requests")
    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequestService.getAdoptionRequest();
    }

    @PostMapping("/adoption-requests")
    public ResponseMessage addAdoptionRequest(@RequestBody AdoptionRequest adoptionRequest) {
        if (adoptionRequest.getMessage().length() > 1000)
            return new ResponseMessage(false, "Request message can't have more than 1000 characters.", "BAD_REQUEST");
        if (adoptionRequest.getUserID() == null)
            return new ResponseMessage(false, "User ID can't be null.", "BAD_REQUEST");
        if (adoptionRequest.getPetID() == null)
            return new ResponseMessage(false, "New pet ID can't be null.", "BAD_REQUEST");
        try {
            adoptionRequestService.addAdoptionRequest(adoptionRequest);
            return new ResponseMessage(true, "Request to adopt a pet with ID= " + adoptionRequest.getPetID() + " added successfully!", "SUCCESS");
        } catch (Exception e) {
            return new ResponseMessage(false, "Database Error: Error saving request to database.", "DB_ERROR");
        }
    }
}
