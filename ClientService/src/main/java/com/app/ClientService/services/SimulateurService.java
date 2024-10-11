package com.app.ClientService.services;


import com.app.ClientService.models.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@Service
public class SimulateurService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public SimulateurService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<EpargneResponse> getSimulateurVersementPeriodique(EpargneRequest request) {
        String url = "http://192.168.10.79/API_VIE/api/Epargne/Epargne_versement_Periodique";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EpargneRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();
            System.out.println("API External Response: " + jsonResponse); // Vérifier le contenu de la réponse

            try {
                EpargneResponse[] epargneResponses = objectMapper.readValue(jsonResponse, EpargneResponse[].class);

                // Vérifier les valeurs après désérialisation
                for (EpargneResponse response : epargneResponses) {
                    System.out.println(response);
                }

                return Arrays.asList(epargneResponses);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to parse JSON response", e);
            }
        } else {
            throw new RuntimeException("Failed to get data from external API");
        }
    }


    public List<EpargneResponse> getSimulateurParObjectif(EpargneRequestObjectif request) {
        String url = "http://192.168.10.79/API_VIE/api/Epargne/Epargne_versement_Objectif";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EpargneRequestObjectif> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();
            System.out.println("API External Response: " + jsonResponse); // Vérifier le contenu de la réponse

            try {
                EpargneResponse[] epargneResponses = objectMapper.readValue(jsonResponse, EpargneResponse[].class);

                // Vérifier les valeurs après désérialisation
                for (EpargneResponse response : epargneResponses) {
                    System.out.println(response);
                }

                return Arrays.asList(epargneResponses);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to parse JSON response", e);
            }
        } else {
            throw new RuntimeException("Failed to get data from external API");
        }
    }


    public List<EpargneResponse> getSimulateurPrimeUnique(EpargnePrimeUniqueRequest request) {
        String url = "http://192.168.10.79/API_VIE/api/Epargne/Epargne_prime_unique";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EpargnePrimeUniqueRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();
            System.out.println("API External Response: " + jsonResponse); // Vérifier le contenu de la réponse

            try {
                EpargneResponse[] epargneResponses = objectMapper.readValue(jsonResponse, EpargneResponse[].class);

                // Vérifier les valeurs après désérialisation
                for (EpargneResponse response : epargneResponses) {
                    System.out.println(response);
                }

                return Arrays.asList(epargneResponses);

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to parse JSON response", e);
            }
        } else {
            throw new RuntimeException("Failed to get data from external API");
        }
    }

    public EpargneCapitalResponse getSimulateurSelonCapital(EpargneRequestCapital request) {
        String url = "http://192.168.10.79/API_VIE/api/Epargne/versemment_selon_Capital";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<EpargneRequestCapital> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonResponse = responseEntity.getBody();
            System.out.println("API External Response: " + jsonResponse); // Vérifier le contenu de la réponse

            try {
                // Désérialiser un seul objet
                EpargneCapitalResponse epargneResponse = objectMapper.readValue(jsonResponse, EpargneCapitalResponse.class);

                // Vérifier les valeurs après désérialisation
                System.out.println(epargneResponse);

                return epargneResponse;

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to parse JSON response", e);
            }
        } else {
            throw new RuntimeException("Failed to get data from external API");
        }
    }




}