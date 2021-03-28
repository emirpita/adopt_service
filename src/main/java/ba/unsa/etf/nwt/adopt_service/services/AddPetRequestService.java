package ba.unsa.etf.nwt.adopt_service.services;

import ba.unsa.etf.nwt.adopt_service.models.AddPetRequest;
import ba.unsa.etf.nwt.adopt_service.repository.AddPetRequestRepository;
import ba.unsa.etf.nwt.adopt_service.response.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
            return new ResponseMessage(false, HttpStatus.BAD_REQUEST, "Request message can't have more than 1000 characters.");
        if (addPetRequest.getUserID() == null)
            return new ResponseMessage(false, HttpStatus.BAD_REQUEST, "User ID can't be null.");
        if (addPetRequest.getNewPetID() == null)
            return new ResponseMessage(false, HttpStatus.BAD_REQUEST, "New pet ID can't be null.");
        try {
            addPetRequestRepository.save(addPetRequest);
            return new ResponseMessage(true, HttpStatus.OK, "Request to add a new pet added successfully!");
        } catch (Exception e) {
            return new ResponseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, "Database Error: Error saving request to database.");
        }
    }
}
