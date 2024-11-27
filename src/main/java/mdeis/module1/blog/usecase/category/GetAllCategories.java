package mdeis.module1.blog.usecase.category;

import jakarta.persistence.EntityNotFoundException;
import mdeis.module1.blog.data.CategoryRepository;
import mdeis.module1.blog.domain.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllCategories {

    private final CategoryRepository categoryRepository;

    public GetAllCategories(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> invoke() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories;
        } else {
            throw new EntityNotFoundException("Categories not found");
        }
    }

}
