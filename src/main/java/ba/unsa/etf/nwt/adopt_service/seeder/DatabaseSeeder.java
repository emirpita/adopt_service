package ba.unsa.etf.nwt.adopt_service.seeder;

import ba.unsa.etf.nwt.adopt_service.services.AddPetRequestService;
import ba.unsa.etf.nwt.adopt_service.services.AdoptionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    @Autowired
    private AddPetRequestService addPetRequestService;

    @Autowired
    private AdoptionRequestService adoptionRequestService;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedDatabase();
    }

    private void seedDatabase() {

    }
}
