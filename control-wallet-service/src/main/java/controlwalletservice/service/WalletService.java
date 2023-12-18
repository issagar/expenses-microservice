package controlwalletservice.service;

import controlwalletservice.dto.Wallet;
import controlwalletservice.event.UserEvent;
import controlwalletservice.model.ExpensesResponse;
import controlwalletservice.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional(readOnly = true)
    public boolean isPositiveBalance(String userId, double amount){
        Wallet wallet = walletRepository.findByUserId(userId);
        if(wallet == null){
            log.info("Wallet not found for userId: " + userId);
            return false;
        }
        boolean isPositiveBalance = wallet.getTotalAmout() > amount;
        if(isPositiveBalance){
            wallet.setTotalAmout(wallet.getTotalAmout() + amount);
            walletRepository.save(wallet);
        }
        return isPositiveBalance;
    }

    @KafkaListener(topics = "notificationTopic" , groupId = "notificationId")
    public void handleNotificationUser(UserEvent userEvent){
        log.info("New user created: " + userEvent.getUserId());
        log.info("Creating wallet for user: " + userEvent.getUserId());
        JSONObject jsonObject = new JSONObject(userEvent.getUserId());
        String userId = jsonObject.getString("userId");
        Wallet wallet = Wallet.builder()
                .userId(userId)
                .totalAmout(0)
                .build();
        walletRepository.save(wallet);
    }

    public ResponseEntity<List<Wallet>> getAllWallets() {
        return ResponseEntity.ok(walletRepository.findAll());
    }
}
