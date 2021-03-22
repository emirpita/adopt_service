package ba.unsa.etf.nwt.adopt_service.controllers;

import ba.unsa.etf.nwt.adopt_service.models.AddPetRequest;
import ba.unsa.etf.nwt.adopt_service.responses.ResponseMessage;
import ba.unsa.etf.nwt.adopt_service.services.AddPetRequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class AddPetRequestController {
    private final AddPetRequestService addPetRequestService;

    @GetMapping("/add-pet-request")
    public List<AddPetRequest> getAddPetRequests() {
        return addPetRequestService.getAddPetRequest();
    }

    @PostMapping("/add-pet-request")
    public ResponseMessage addAddPetRequest(@RequestBody AddPetRequest addPetRequest) {
        if (addPetRequest.getMessage().length() > 1000)
            return new ResponseMessage(false, "Request message can't have more than 1000 characters.", "BAD_REQUEST");
        if (addPetRequest.getUserID() == null)
            return new ResponseMessage(false, "User ID can't be null.", "BAD_REQUEST");
        if (addPetRequest.getNewPetID() == null)
            return new ResponseMessage(false, "New pet ID can't be null.", "BAD_REQUEST");
        try {
            addPetRequestService.addAddPetRequest(addPetRequest);
            return new ResponseMessage(true, "Request to add a new pet added successfully!", "SUCCESS");
        } catch (Exception e) {
            return new ResponseMessage(false, "Database Error: Error saving request to database.", "DB_ERROR");
        }
    }
}
