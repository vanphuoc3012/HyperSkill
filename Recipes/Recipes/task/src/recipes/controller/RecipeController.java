package recipes.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.model.Recipe;
import recipes.service.RecipeService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    private final Recipe firstRecipe = new Recipe();
    //stage 1
    @PostMapping("/api/recipe")
    public ResponseEntity addRecipe(@RequestBody Recipe recipe) {
        firstRecipe.setName(recipe.getName());
        firstRecipe.setDescription(recipe.getDescription());
        firstRecipe.setIngredients(recipe.getIngredients());
        firstRecipe.setDirections(recipe.getDirections());
        System.out.println("Adding recipe: " + firstRecipe);
        return new ResponseEntity(firstRecipe, HttpStatus.OK);
    }

    @GetMapping("/api/recipe")
    public ResponseEntity getRecipe() {
        System.out.println("Getting recipe: " + firstRecipe);
        return new ResponseEntity(firstRecipe, HttpStatus.OK);
    }


    //Stage 2
    @PostMapping("/api/recipe/new")
    public ResponseEntity addNewRecipe(@Validated @RequestBody Recipe recipeToAdd) throws JsonProcessingException {
        System.out.println("~~~~Adding new recipe~~~~");
        recipeToAdd.setDate(LocalDateTime.now());
        Recipe r = recipeService.save(recipeToAdd);
        System.out.println("~~~~New Recipe's id = "+r.getId()+"~~~~");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id");
        String idNewRecipe = recipeApplyFilter(filter, recipeToAdd);

        return new ResponseEntity<>(idNewRecipe, HttpStatus.OK);
    }

    @GetMapping("/api/recipe/{id}")
    public ResponseEntity getRecipeById(@PathVariable Integer id) throws JsonProcessingException {
        System.out.println("~~~~Getting Recipe By Id = "+id+"~~~~");
        Recipe r = recipeService.findById(id);
        if(r == null) {
            System.out.println("~~~~Not Found~~~~");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            System.out.println("~~~~Found~~~~");
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("id");
            return new ResponseEntity(recipeApplyFilter(filter, r), HttpStatus.OK);
        }
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity deleteRecipe(@PathVariable Integer id) {
        if(recipeService.checkExistsById(id)) {
            System.out.println("~~~~Found recipe with id = "+id+" deleting");
            recipeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            System.out.println("~~~~Not found~~~~");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity updateRecipe(@PathVariable Integer id,
                                       @Validated @RequestBody Recipe recipeToUpdate) {
        Recipe recipe = recipeService.findById(id);
        if(recipe == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            System.out.println("~~~~Updating recipe id = "+id+"~~~~");
            recipe.setName(recipeToUpdate.getName());
            recipe.setCategory(recipeToUpdate.getCategory());
            recipe.setDescription(recipeToUpdate.getDescription());
            recipe.setIngredients(recipeToUpdate.getIngredients());
            recipe.setDirections(recipeToUpdate.getDirections());
            recipe.setDate(LocalDateTime.now());
            recipeService.save(recipe);

            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/api/recipe/search")
    public ResponseEntity search (@RequestParam(required = false) Map<String, String> request) throws JsonProcessingException {
        if(request.isEmpty() || request.size() > 1) {
            System.out.println("~~~~No request param~~~~");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            ObjectMapper objectMapper = new ObjectMapper()
                                        .registerModule(new JavaTimeModule())
                                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("id");
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("recipeFilter", filter);
            if(request.containsKey("category")) {
                String category = request.get("category");
                System.out.println("~~~~List for category: "+category+"~~~~");
                List<Recipe> recipeList = recipeService.findAllByCategoryAllIgnoreCaseOrderByDateDesc(category);
                String list = objectMapper.writer(filterProvider).withDefaultPrettyPrinter().writeValueAsString(recipeList);

                return new ResponseEntity<>(list, HttpStatus.OK);
            } else if(request.containsKey("name")) {
                String name = request.get("name");
                System.out.println("Name parameter: "+name);
                List<Recipe> recipeList = recipeService.findByNameContainingIgnoreCaseOrderByDateDesc(name);
                String list = objectMapper.writer(filterProvider).withDefaultPrettyPrinter().writeValueAsString(recipeList);

                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    private static String recipeApplyFilter(SimpleBeanPropertyFilter filter, Recipe recipe) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("recipeFilter", filter);
        String recipeAsJson = objectMapper.writer(filterProvider).withDefaultPrettyPrinter().writeValueAsString(recipe);
        return  recipeAsJson;
    }
}
