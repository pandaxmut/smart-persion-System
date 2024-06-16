package com.edu.smartpersionsys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishScore {
    private int id;
    private int olderId;
    private int dishId;
    private int score;
}
