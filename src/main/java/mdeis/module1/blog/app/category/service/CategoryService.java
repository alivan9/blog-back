package mdeis.module1.blog.app.category.service;

import mdeis.module1.blog.app.category.api.CategoryApi;
import mdeis.module1.blog.app.category.api.NewCategoryApi;

import java.util.List;

public interface CategoryService {

    CategoryApi createCategory(NewCategoryApi newCategoryApi);

    CategoryApi readCategory(Integer categoryId);

    CategoryApi updateCategory(Integer categoryId, NewCategoryApi updateCategoryApi);

    void deleteCategory(Integer categoryId);

    List<CategoryApi> getAllCategories();
}
