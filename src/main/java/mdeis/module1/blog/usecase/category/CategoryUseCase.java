package mdeis.module1.blog.usecase.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryUseCase {
    public final CreateCategory createCategory;
    public final GetCategoryById getCategoryById;
    public final UpdateCategory updateCategory;
    public final DeleteCategory deleteCategory;

    public CategoryUseCase(CreateCategory createCategory, GetCategoryById getCategoryById, UpdateCategory updateCategory, DeleteCategory deleteCategory) {
        this.createCategory = createCategory;
        this.getCategoryById = getCategoryById;
        this.updateCategory = updateCategory;
        this.deleteCategory = deleteCategory;
    }
}
