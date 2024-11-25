package mdeis.module1.blog.app.category.service.mapper;

import mdeis.module1.blog.app.category.api.CategoryApi;
import mdeis.module1.blog.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryApi map(Category category) {
        CategoryApi categoryApi = new CategoryApi();
        categoryApi.setId(category.getId());
        categoryApi.setName(category.getName());
        return categoryApi;
    }
}
