package mdeis.module1.blog.usecase.category;

import mdeis.module1.blog.app.category.api.NewCategoryApi;
import mdeis.module1.blog.data.CategoryRepository;
import mdeis.module1.blog.domain.*;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;


@Component
public class CreateCategory {

    private final CategoryRepository categoryRepository;

    public CreateCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category invoke(NewCategoryApi newCategoryApi) {
        Category category = new Category();
        try {
            category.setName(newCategoryApi.getName());
            category = categoryRepository.save(category);
        } catch (Exception e) {
            throw new SaveEntityException("Error creating Category: " + e.getMessage());
        }
        return category;
    }

}
