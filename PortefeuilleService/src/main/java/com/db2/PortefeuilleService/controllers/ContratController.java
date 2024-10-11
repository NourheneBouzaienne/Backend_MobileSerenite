package com.db2.PortefeuilleService.controllers;



import com.db2.PortefeuilleService.dao.ContratDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


    @RestController
    @RequestMapping("/contrat")
    public class ContratController {
        private final ContratDAO contratDAO;

        public ContratController(ContratDAO contratDAO) {
            this.contratDAO = contratDAO;
        }

        @GetMapping("/test")
        public String test() {
            return "Test endpoint working!";
        }
        @GetMapping("/listContrats")
        public List<Map<String, Object>> findAll() {
            return contratDAO.findAll();
        }

        @GetMapping("/listContratsClients")
        public List<Map<String, Object>> ContratsClient(@RequestParam String CIN, @RequestParam String numCNT) {
            return contratDAO.findByIDAndNUMCNT(CIN, numCNT);
        }
        @GetMapping("/listContratsClientsF")
        public List<Map<String, Object>> ContratsClientF(@RequestParam String CIN, @RequestParam String numCNT) {
            return contratDAO.findContratByIDAndNUMCNT(CIN, numCNT);
        }

        @GetMapping("/ContratsEnabled")
        public List<Map<String, Object>> ContartEnabled(@RequestParam String CIN) {
            return contratDAO.findContratsByID(CIN);
        }

        @GetMapping("/Quittances")
        public List<Map<String, Object>> getQuittances(@RequestParam String numCNT) {
            return contratDAO.QuittanceByNumcnt(numCNT);
        }
}
