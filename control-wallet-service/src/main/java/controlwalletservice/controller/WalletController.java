package controlwalletservice.controller;

import controlwalletservice.dto.Wallet;
import controlwalletservice.model.ExpensesResponse;
import controlwalletservice.model.WalletResponse;
import controlwalletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public WalletResponse isPositiveBalance(@RequestParam String userId){
        return walletService.isPositiveBalance(userId);
    }
}
