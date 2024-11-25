package mdeis.module1.blog.app.category.service;

import mdeis.module1.blog.app.category.api.CategoryApi;
import mdeis.module1.blog.app.category.api.NewCategoryApi;
import mdeis.module1.blog.app.category.service.mapper.CategoryMapper;
import mdeis.module1.blog.domain.Category;
import mdeis.module1.blog.usecase.category.CategoryUseCase;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryUseCase categoryUseCase;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryUseCase categoryUseCase, CategoryMapper categoryMapper) {
        this.categoryUseCase = categoryUseCase;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryApi createCategory(NewCategoryApi newCategoryApi) {
        return categoryMapper.map(categoryUseCase.createCategory.invoke(newCategoryApi));
    }

    @Override
    public CategoryApi readCategory(Integer categoryId) {
        return categoryMapper.map(categoryUseCase.getCategoryById.invoke(categoryId));
    }

    @Override
    public CategoryApi updateCategory(Integer categoryId, NewCategoryApi updatePostApi) {
        Category category = categoryUseCase.getCategoryById.invoke(categoryId);
        return categoryMapper.map(categoryUseCase.updateCategory.invoke(category, updatePostApi));
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryUseCase.getCategoryById.invoke(categoryId);
        categoryUseCase.deleteCategory.invoke(category);
    }


}
