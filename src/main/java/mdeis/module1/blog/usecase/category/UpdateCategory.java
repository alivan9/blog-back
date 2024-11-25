package mdeis.module1.blog.usecase.category;

import mdeis.module1.blog.app.category.api.NewCategoryApi;
import mdeis.module1.blog.data.CategoryRepository;
import mdeis.module1.blog.domain.Category;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;


@Component
public class UpdateCategory {

    private final CategoryRepository categoryRepository;

    public UpdateCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category invoke(Category category, NewCategoryApi newCategoryApi) {
        try {
            category.setName(newCategoryApi.getName());
            category = categoryRepository.save(category);
        } catch (Exception e) {
            throw new SaveEntityException("Error updating category: " + e.getMessage());
        }
        return category;
    }

}
