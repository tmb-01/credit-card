package uz.pdp.creditcard.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferDto {
    private Long toCardNumber;
    private Float amount;
}
