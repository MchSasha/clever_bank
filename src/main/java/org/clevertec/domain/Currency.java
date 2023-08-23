package org.clevertec.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {
    private Integer currencyId;

    private String name;
}
