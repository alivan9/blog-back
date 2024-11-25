package mdeis.module1.blog.app.category.controller;

import jakarta.validation.Valid;
import mdeis.module1.blog.app.category.api.CategoryApi;
import mdeis.module1.blog.app.category.api.NewCategoryApi;
import mdeis.module1.blog.app.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryApi> createCategory(@RequestBody @Valid NewCategoryApi newCategoryApi) {
        return new ResponseEntity<>(categoryService.createCategory(newCategoryApi), HttpStatus.OK);
    }

    @GetMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryApi> readCategory(@PathVariable Integer categoryId) {
        return new ResponseEntity<>(categoryService.readCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryApi> updateCategory(@PathVariable Integer categoryId, @RequestBody @Valid NewCategoryApi newCategoryApi) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, newCategoryApi), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted.", HttpStatus.OK);
    }
}
