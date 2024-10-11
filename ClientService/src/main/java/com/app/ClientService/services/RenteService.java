package com.app.ClientService.services;


import com.app.ClientService.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class RenteService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public RenteService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public RenteResponse getRentePrimeUnique(RenteRequestUnique request) {
        String url = "http://192.168.10.79/API_VIE/api/Rente/Rente_prime_unique";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RenteRequestUnique> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();
            System.out.println("API External Response: " + jsonResponse);

            try {
                // Deserialize the JSON response into a single RenteResponse object
                RenteResponse renteResponse = objectMapper.readValue(
                        jsonResponse,
                        RenteResponse.class
                );
                return renteResponse;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to parse JSON response", e);
            }
        } else {
            throw new RuntimeException("Failed to get data from external API");
        }
    }


    public RenteResponse getRenteVersements(RenteRequest request) {
        String url = "http://192.168.10.79/API_VIE/api/Rente/Rente_selon_versement";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RenteRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();
            System.out.println("API External Response: " + jsonResponse);

            try {
                // Deserialize the JSON response into a single RenteResponse object
                RenteResponse renteResponse = objectMapper.readValue(
                        jsonResponse,
                        RenteResponse.class
                );
                return renteResponse;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to parse JSON response", e);
            }
        } else {
            throw new RuntimeException("Failed to get data from external API");
        }
    }


    public RenteResponse getRenteParObjectif(RenteRequestObjectif request) {
        String url = "http://192.168.10.79/API_VIE/api/Rente/Rente_selon_Capital";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RenteRequestObjectif> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();
            System.out.println("API External Response: " + jsonResponse);

            try {
                // Deserialize the JSON response into a single RenteResponse object
                RenteResponse renteResponse = objectMapper.readValue(
                        jsonResponse,
                        RenteResponse.class
                );
                return renteResponse;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to parse JSON response", e);
            }
        } else {
            throw new RuntimeException("Failed to get data from external API");
        }
    }




}
