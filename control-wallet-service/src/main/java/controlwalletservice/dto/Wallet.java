package controlwalletservice.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("wallet")
public class Wallet {
    @Id
    private String id;
    private String userId;
    private double totalAmout;
}
