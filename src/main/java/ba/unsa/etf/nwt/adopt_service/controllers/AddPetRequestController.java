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

        return new ResponseMessage(true, "Request to add a new pet added successfully!", "SUCCESS");
        //return addPetRequestService.addAddPetRequest(addPetRequest);
    }
}
