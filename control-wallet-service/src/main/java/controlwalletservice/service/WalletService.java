package controlwalletservice.service;

import controlwalletservice.dto.Wallet;
import controlwalletservice.event.UserEvent;
import controlwalletservice.model.ExpensesResponse;
import controlwalletservice.model.WalletResponse;
import controlwalletservice.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional(readOnly = true)
    public WalletResponse isPositiveBalance(String userId){
        Wallet wallet = walletRepository.findByUserId(userId);
        return WalletResponse.builder()
                .userId(wallet.getUserId())
                .isPositiveBalance(wallet.getTotalAmout() > 0)
                .build();
    }

    public Wallet update(Wallet wallet) {
        ExpensesResponse expensesResponse = restTemplate.getForObject("http://localhost:8081/expenses/wallet/"+wallet.getUserId(), ExpensesResponse.class);
        wallet = walletRepository.findByUserId(expensesResponse.getUserId());
        if (wallet != null){
            wallet.setTotalAmout(expensesResponse.getCurrentBalance());
        }
        return walletRepository.save(wallet);
    }

    @KafkaListener(topics = "notificationTopic" , groupId = "notificationId")
    public void handleNotificationUser(UserEvent userEvent){
        log.info("New user created: " + userEvent.getUserId());
        log.info("Creating wallet for user: " + userEvent.getUserId());
        Wallet wallet = Wallet.builder()
                .userId(userEvent.getUserId())
                .totalAmout(0)
                .build();
        walletRepository.save(wallet);
    }
}
