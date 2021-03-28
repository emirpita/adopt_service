package ba.unsa.etf.nwt.adopt_service.services;

import ba.unsa.etf.nwt.adopt_service.models.AdoptionRequest;
import ba.unsa.etf.nwt.adopt_service.repository.AdoptionRequestRepository;
import ba.unsa.etf.nwt.adopt_service.response.ResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
            return new ResponseMessage(false, HttpStatus.BAD_REQUEST, "Request message can't have more than 1000 characters.");
        if (adoptionRequest.getUserID() == null)
            return new ResponseMessage(false, HttpStatus.BAD_REQUEST, "User ID can't be null.");
        if (adoptionRequest.getPetID() == null)
            return new ResponseMessage(false, HttpStatus.BAD_REQUEST, "Pet ID can't be null.");
        try {
            adoptionRequestRepository.save(adoptionRequest);
            return new ResponseMessage(true, HttpStatus.OK, "Request to adopt a pet with ID=" + adoptionRequest.getPetID() + " added successfully!");
        } catch (Exception e) {
            return new ResponseMessage(false, HttpStatus.INTERNAL_SERVER_ERROR, "Database Error: Error saving request to database.");
        }
    }
}
