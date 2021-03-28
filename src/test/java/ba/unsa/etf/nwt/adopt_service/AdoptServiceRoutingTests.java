package ba.unsa.etf.nwt.adopt_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class AdoptServiceRoutingTests {
    @Autowired
    private MockMvc mockMvc;

    // Testovi za AdoptionRequest

    @Test
    void GetAllAdoptionRequestsInJSON() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/adoption-request")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void CreateNewValidAdoptionRequest() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": 1,\n" +
                "    \"petID\": 1,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adoption-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": true,\n" +
                        "    \"message\": \"Request to adopt a pet with ID=1 added successfully!\",\n" +
                        "    \"status\": \"OK\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAdoptionRequestMessageTooLong() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": 1,\n" +
                "    \"petID\": 1,\n" +
                "    \"message\": \"Što se tiče lokalnih stranica, ova stranica je kao DogsTrust (http://www.dogstrust.ba/en/), s tim da DogsTrust ima razne druge stvari i novosti koje objavljuje na svojoj stranici, dok smo mi mislili da se baziramo samo na udomljavanje. Također, na DogsTrust opcija udomljavanja ide preko prijave i e-maila, dok u ovom slučaju to nije tako. Također, ova stranica neće biti bazirana samo na pse.\\n\" +\n" +
                "                \"Samim tim, pronađena je jedna stranica koja vrši sličnu stvar kao naša, a zove se Rehome (https://rehome.adoptapet.com). Ova stranica udomljava životinje online, na sličan način kao što će to biti urađeno ovdje, s tim da ovdje neće biti opcije upoznavanja budućih vlasnika i vršenja procesa udomljavanja od strane korisnika koji je prijavio životinju na udomljavanje, nego će to biti posao administracije. Druga stranica koja je namijenja samo za udomljavanje životinja jeste The Shelter Pet Project (https://theshelterpetproject.org) i nešto slično će postojati i ona ovoj stranici, uz ostale funkcionalnosti. Dakle, može se reći da je ova naša stranica kombinacija Rehome i The Shelter Pet Project stranice.\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adoption-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"Request message can't have more than 1000 characters.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAdoptionRequestUserIDNull() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": null,\n" +
                "    \"petID\": 1,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adoption-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"User ID can't be null.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAdoptionRequestUserIDMissing() throws Exception {
        String newComment = "{\n" +
                "    \"petID\": 1,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adoption-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"User ID can't be null.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAdoptionRequestPetIDNull() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": 1,\n" +
                "    \"petID\": null,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adoption-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"Pet ID can't be null.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAdoptionRequestPetIDMissing() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": 1,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/adoption-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"Pet ID can't be null.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    // Testovi za AddPetRequest

    @Test
    void GetAllAddPetRequestsInJSON() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/add-pet-request")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void CreateNewValidAddPetRequest() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": 1,\n" +
                "    \"newPetID\": 1,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-pet-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": true,\n" +
                        "    \"message\": \"Request to add a new pet added successfully!\",\n" +
                        "    \"status\": \"OK\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAddPetRequestMessageTooLong() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": 1,\n" +
                "    \"newPetID\": 1,\n" +
                "    \"message\": \"Što se tiče lokalnih stranica, ova stranica je kao DogsTrust (http://www.dogstrust.ba/en/), s tim da DogsTrust ima razne druge stvari i novosti koje objavljuje na svojoj stranici, dok smo mi mislili da se baziramo samo na udomljavanje. Također, na DogsTrust opcija udomljavanja ide preko prijave i e-maila, dok u ovom slučaju to nije tako. Također, ova stranica neće biti bazirana samo na pse.\\n\" +\n" +
                "                \"Samim tim, pronađena je jedna stranica koja vrši sličnu stvar kao naša, a zove se Rehome (https://rehome.adoptapet.com). Ova stranica udomljava životinje online, na sličan način kao što će to biti urađeno ovdje, s tim da ovdje neće biti opcije upoznavanja budućih vlasnika i vršenja procesa udomljavanja od strane korisnika koji je prijavio životinju na udomljavanje, nego će to biti posao administracije. Druga stranica koja je namijenja samo za udomljavanje životinja jeste The Shelter Pet Project (https://theshelterpetproject.org) i nešto slično će postojati i ona ovoj stranici, uz ostale funkcionalnosti. Dakle, može se reći da je ova naša stranica kombinacija Rehome i The Shelter Pet Project stranice.\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-pet-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"Request message can't have more than 1000 characters.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAddPetRequestUserIDNull() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": null,\n" +
                "    \"petID\": 1,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-pet-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"User ID can't be null.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAddPetRequestUserIDMissing() throws Exception {
        String newComment = "{\n" +
                "    \"petID\": 1,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-pet-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"User ID can't be null.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAddPetRequestNewPetIDNull() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": 1,\n" +
                "    \"petID\": null,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-pet-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"New pet ID can't be null.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }

    @Test
    void CreateNewAddPetnRequestPetIDMissing() throws Exception {
        String newComment = "{\n" +
                "    \"userID\": 1,\n" +
                "    \"message\": \"Pet care\",\n" +
                "    \"approved\": false\n" +
                "}\n";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-pet-request")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newComment);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "    \"success\": false,\n" +
                        "    \"message\": \"New pet ID can't be null.\",\n" +
                        "    \"status\": \"BAD_REQUEST\"\n" +
                        "}\n"));
    }
}
