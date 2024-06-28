package com.cydeo.model;

import com.cydeo.enums.RecipeType;

import java.util.List;
import java.util.UUID;

public class Recipe {

    private UUID recipeId;
    private String name;
    private int duration;
    private String preparation;
    private List<Ingredient> ingredients;
    private RecipeType recipeType;

}
