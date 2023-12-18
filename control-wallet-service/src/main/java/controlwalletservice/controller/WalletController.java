package controlwalletservice.controller;

import controlwalletservice.dto.Wallet;
import controlwalletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @GetMapping
    public ResponseEntity<List<Wallet>> listAllWallets(){
        return walletService.getAllWallets();
    }
    @GetMapping("/{userId}/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isPositiveBalance(@PathVariable String userId , @PathVariable double amount){
        System.out.println("userId: " + userId + " amount: " + amount);
        return walletService.isPositiveBalance(userId, amount);
    }
}
