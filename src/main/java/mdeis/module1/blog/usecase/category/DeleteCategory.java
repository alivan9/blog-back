package mdeis.module1.blog.usecase.category;

import mdeis.module1.blog.data.CategoryRepository;
import mdeis.module1.blog.domain.Category;
import mdeis.module1.blog.domain.exception.SaveEntityException;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategory {

    private final CategoryRepository categoryRepository;

    public DeleteCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void invoke(Category category) {
        try {
            categoryRepository.delete(category);
        } catch (Exception e) {
            throw new SaveEntityException("Error deleting category: " + e.getMessage());
        }
    }

}
