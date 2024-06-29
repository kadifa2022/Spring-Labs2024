package com.cydeo.service.impl;

import com.cydeo.model.Recipe;
import com.cydeo.service.ShareService;
import org.springframework.stereotype.Component;

@Component
public class InstagramShareServiceImpl implements ShareService {
    @Override
    public boolean share(Recipe recipe) {
        System.out.println("Shared on Instagram");
        System.out.println("RecipeType: " + recipe.getRecipeType());
        System.out.println("Recipe Name " + recipe.getName());
        System.out.println("Ingredient List ");
        recipe.getIngredients().forEach(ingredient -> {
            System.out.println(" Ingredient Name " + ingredient.getName() + "Quantity : " +
                    ingredient.getQuantity() + " " + ingredient.getQuantityType());
        });
        System.out.println("Preparation:  \n" + "\t" + recipe.getPreparation());
        return true;

    }
}
