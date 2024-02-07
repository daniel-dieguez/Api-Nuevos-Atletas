package com.atletas.atletas.models.entities;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AtletasDTO  implements Serializable {
    @NotEmpty(message = "Este dato debe de estar lleno")
    private String atletas;
}
