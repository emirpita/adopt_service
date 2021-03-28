package ba.unsa.etf.nwt.adopt_service.controllers;

import ba.unsa.etf.nwt.adopt_service.models.AdoptionRequest;
import ba.unsa.etf.nwt.adopt_service.response.ResponseMessage;
import ba.unsa.etf.nwt.adopt_service.services.AdoptionRequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
public class AdoptionRequestController {
    private final AdoptionRequestService adoptionRequestService;

    @GetMapping("/adoption-request")
    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequestService.getAdoptionRequest();
    }

    @PostMapping("/adoption-request")
    public ResponseMessage addAdoptionRequest(@Valid @RequestBody AdoptionRequest adoptionRequest) {
        return adoptionRequestService.addAdoptionRequest(adoptionRequest);
    }
}
