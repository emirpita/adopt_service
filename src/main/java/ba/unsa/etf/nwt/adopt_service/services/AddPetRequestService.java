package ba.unsa.etf.nwt.adopt_service.services;

import ba.unsa.etf.nwt.adopt_service.models.AddPetRequest;
import ba.unsa.etf.nwt.adopt_service.repository.AddPetRequestRepository;
import ba.unsa.etf.nwt.adopt_service.responses.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AddPetRequestService {
    private final AddPetRequestRepository addPetRequestRepository;

    public List<AddPetRequest> getAddPetRequest() {
        return addPetRequestRepository.findAll();
    }

    public ResponseMessage addAddPetRequest(AddPetRequest addPetRequest) {
        if (addPetRequest.getMessage().length() > 1000)
            return new ResponseMessage(false, "Request message can't have more than 1000 characters.", "BAD_REQUEST");
        if (addPetRequest.getUserID() == null)
            return new ResponseMessage(false, "User ID can't be null.", "BAD_REQUEST");
        if (addPetRequest.getNewPetID() == null)
            return new ResponseMessage(false, "New pet ID can't be null.", "BAD_REQUEST");
        try {
            addPetRequestRepository.save(addPetRequest);
            return new ResponseMessage(true, "Request to add a new pet added successfully!", "OK");
        } catch (Exception e) {
            return new ResponseMessage(false, "Database Error: Error saving request to database.", "DB_ERROR");
        }
    }
}
