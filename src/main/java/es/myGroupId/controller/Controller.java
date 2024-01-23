package es.myGroupId.controller;



import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import es.myGroupId.service.OcpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
class OcpController {
    @Autowired
    private OcpService ocpService;



    @GetMapping("/deployments")
    JsonNode deployments( @RequestHeader String token) throws IOException {
        try {
            return ocpService.getAllDeployments(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/services")
    JsonNode services(@RequestHeader String token ) throws IOException {
        try {
            return ocpService.getAllDeployments(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
