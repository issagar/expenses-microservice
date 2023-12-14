package controlwalletservice.controller;

import controlwalletservice.model.WalletResponse;
import controlwalletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WallerController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/{expenseId}")
    @ResponseStatus(HttpStatus.OK)
    public WalletResponse isPositiveBalance(@RequestParam String expenseId){
        return walletService.isPositiveBalance(expenseId);
    }



}
