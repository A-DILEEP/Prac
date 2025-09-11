package com.hackerrank.tradingplatform.controller;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.TraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;

@RestController
@RequestMapping(value = "/trading/traders")
public class TraderController {
    @Autowired
    private TraderService traderService;

    //register
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerTrader(@RequestBody @Valid Trader trader) {
            Trader trader2= traderService.getTraderByEmail(trader.getEmail());
            if(trader2.getEmail()==null){
                Trader trader3=new Trader();
                trader3=traderService.registerTrader(trader);
                return ResponseEntity.status(201).body(trader3);
            }

            return ResponseEntity.status(400).build();
    } 

    //get by email
    @RequestMapping(method = RequestMethod.GET)
    // @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getTraderByEmail(@RequestParam("email") String email) {
        TraderDTO traderDTO=new TraderDTO(traderService.getTraderByEmail(email));
        if(traderDTO.getEmail()!=null){
            return ResponseEntity.ok(traderDTO);
        }

        return ResponseEntity.notFound().build();
    }

    //get all
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TraderDTO> getAllTraders() {
        return traderService.getAllTraders()
                .stream()
                .map(TraderDTO::new)
                .sorted(Comparator.comparing(TraderDTO::getId))
                .collect(toList());
    }

    //update by email
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateTrader(@RequestBody @Valid UpdateTraderDTO trader) {

        Trader trader3=traderService.getTraderByEmail(trader.getEmail());
        if(trader3.getEmail()!=null){
              traderService.updateTrader(trader);
              return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    //add money
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addMoney(@RequestBody @Valid AddMoneyTraderDTO trader) {
        
        Trader trader3=traderService.getTraderByEmail(trader.getEmail());
        if(trader3.getEmail()!=null){
                 traderService.addMoney(trader);  
                 return ResponseEntity.status(200).build();
}

  return ResponseEntity.status(404).build();



    }
}
