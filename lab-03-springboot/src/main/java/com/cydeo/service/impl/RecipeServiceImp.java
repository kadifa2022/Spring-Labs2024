package com.cydeo.service.impl;

import com.cydeo.enums.QuantityType;
import com.cydeo.enums.RecipeType;
import com.cydeo.model.Ingredient;
import com.cydeo.model.Recipe;
import com.cydeo.repository.RecipeRepository;
import com.cydeo.service.RecipeService;
import com.cydeo.service.ShareService;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.*;

@Component

public class RecipeServiceImp implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ShareService shareService;
    private final Faker faker;

    public RecipeServiceImp(RecipeRepository recipeRepository, ShareService shareService, Faker faker) {
        this.recipeRepository = recipeRepository;
        this.shareService = shareService;
        this.faker = faker;
    }


    @Override
    public boolean prepareRecipe() {
        // create one Recipe object
       Recipe recipe = new Recipe();
       // set the fields
       recipe.setRecipeId(UUID.randomUUID());
       recipe.setName(faker.food().dish());
       recipe.setDuration(generateRandomValue());
       recipe.setPreparation(faker.lorem().paragraph(5));// lorem() feel the text
       recipe.setIngredients(prepareIngredient());
       recipe.setRecipeType(RecipeType.BREAKFAST);
       recipeRepository.save(recipe);
       shareService.share(recipe);
       // share the recipe
        //save recipe
        //return true
        return true;
    }

    private List<Ingredient> prepareIngredient() {
        List<Ingredient> ingredientList = new ArrayList<>();

        for (int i = 0; i < generateRandomValue(); i++) {

            Ingredient ingredient = new Ingredient();
            ingredient.setName(faker.food().ingredient());
            ingredient.setQuantity(generateRandomValue());
            ingredient.setQuantityType(QuantityType.TBSP);

            ingredientList.add(ingredient);
        }
        return ingredientList;
    }

    private int generateRandomValue() {
        return new Random().nextInt(20);

    }
}
