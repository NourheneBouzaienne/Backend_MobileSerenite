package com.app.ClientService.proxies;


import com.app.ClientService.beans.Contrat;
import com.app.ClientService.beans.Quittance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "PortefeuilleService", url = "localhost:8000")

public interface MiddlewarePortefeuille {


    @GetMapping(value = "contrat/listContratsClients")
    List<Contrat> getContratsClient(@RequestParam String CIN, @RequestParam String numCNT) ;

    @GetMapping(value = "contrat/listContratsClientsF")
    List<Contrat> getContratsClientF(@RequestParam String CIN, @RequestParam String numCNT) ;

    @GetMapping(value = "contrat/ContratsEnabled")
    List<Contrat> listContratsClientById(@RequestParam String CIN) ;

    @GetMapping(value = "contrat/Quittances")
    List<Quittance> listQuittancesByContrat(@RequestParam String numCNT) ;


}


