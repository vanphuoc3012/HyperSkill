package account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class POSTPaymentDto {

    @NotNull()
    private String employee;

    private String period;

    private Long salary;

}
