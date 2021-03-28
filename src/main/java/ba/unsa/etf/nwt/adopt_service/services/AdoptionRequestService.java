package ba.unsa.etf.nwt.adopt_service.services;

import ba.unsa.etf.nwt.adopt_service.models.AdoptionRequest;
import ba.unsa.etf.nwt.adopt_service.repository.AdoptionRequestRepository;
import ba.unsa.etf.nwt.adopt_service.responses.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AdoptionRequestService {
    private final AdoptionRequestRepository adoptionRequestRepository;

    public List<AdoptionRequest> getAdoptionRequest() {
        return adoptionRequestRepository.findAll();
    }

    public ResponseMessage addAdoptionRequest(AdoptionRequest adoptionRequest) {
        if (adoptionRequest.getMessage().length() > 1000)
            return new ResponseMessage(false, "Request message can't have more than 1000 characters.", "BAD_REQUEST");
        if (adoptionRequest.getUserID() == null)
            return new ResponseMessage(false, "User ID can't be null.", "BAD_REQUEST");
        if (adoptionRequest.getPetID() == null)
            return new ResponseMessage(false, "Pet ID can't be null.", "BAD_REQUEST");
        try {
            adoptionRequestRepository.save(adoptionRequest);
            return new ResponseMessage(true, "Request to adopt a pet with ID= " + adoptionRequest.getPetID() + " added successfully!", "SUCCESS");
        } catch (Exception e) {
            return new ResponseMessage(false, "Database Error: Error saving request to database.", "DB_ERROR");
        }
    }
}
