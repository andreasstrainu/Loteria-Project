package com.softtek.loteria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String dni;
    private String name;
    private List<List<Integer>> bets = new ArrayList<>();
}
