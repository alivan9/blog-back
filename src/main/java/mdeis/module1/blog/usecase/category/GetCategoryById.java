package mdeis.module1.blog.usecase.category;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.data.CategoryRepository;
import mdeis.module1.blog.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class GetCategoryById {

    private final CategoryRepository categoryRepository;

    public GetCategoryById(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category invoke(Integer postId) {
        return categoryRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + postId));
    }

}
