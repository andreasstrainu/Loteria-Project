package com.softtek.loteria.controllers.bets;

import com.softtek.loteria.services.bet.BetService;
import com.softtek.loteria.services.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loteria/bets")
public class BetsControllerImpl implements BetsController {

    private final BetService betService;

    public BetsControllerImpl(BetService betService) {
        this.betService = betService;
    }

    @Override
    @PutMapping("/{dni}")
    public void addBet(@PathVariable String userDni, @RequestParam Integer[] numbers) {
        betService.addBet(userDni, numbers);
    }
}
