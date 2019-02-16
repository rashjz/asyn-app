package rashjz.info.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class Order {
    private String id;
    private BigDecimal amount;
}
