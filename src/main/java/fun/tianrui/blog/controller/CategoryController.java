package fun.tianrui.blog.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import fun.tianrui.blog.entity.Category;
import fun.tianrui.blog.repository.CategoryRepository;
import fun.tianrui.blog.service.CategoryService;
import fun.tianrui.blog.utils.ArticleUtils;
import fun.tianrui.blog.vo.ArticleIdAndTitle;
import fun.tianrui.blog.vo.CategoryNameAndArticlesSize;
import fun.tianrui.blog.vo.CategoryVO;
import fun.tianrui.blog.vo.CategoryVOListAndTotalPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/All")
    public List<CategoryVO> getAll() {
        return categoryRepository.findAllWithoutArticle();
    }

    @GetMapping("/All/page")
    public CategoryVOListAndTotalPage getAllByPage(Integer page) {
//         用list分页吧
        if (null == page || page < 1) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(--page, 9, Sort.Direction.DESC, "id");
        Page<CategoryVO> categories = categoryRepository.findAllWithoutArticleByPage(pageable);
        CategoryVOListAndTotalPage categoryVOListAndTotalPage = new CategoryVOListAndTotalPage();
        categoryVOListAndTotalPage.setCategories(categories.get().collect(Collectors.toList()));
        categoryVOListAndTotalPage.setTotalPage(categories.getTotalPages());
        return categoryVOListAndTotalPage;

    }

    @GetMapping("/new")
    public List<CategoryVO> getNew() {
        // 分页查询
        Pageable pageable = PageRequest.of(0, 10);
        return categoryRepository.findNewAllWithoutArticle(pageable);
    }

    @RequestMapping("/getCategoryArticleByPage")
    public List<ArticleIdAndTitle> getCategoryArticleByPage(Long categoryId, Integer page) {
        categoryId = categoryId == null ? 1 : categoryId;
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if (optional.isEmpty()) {
            return null;
        }
        //处理成分页
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        return ArticleUtils.toArticleByPage(optional.get().getArticles(), page)
                .stream().map(e -> mapper.map(e, ArticleIdAndTitle.class))
                .collect(Collectors.toList());
    }

    @RequestMapping("/getTotalPageById")
    public CategoryNameAndArticlesSize getTotalPageByID(Long id) {
        id = id == null ? 1 : id;
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        CategoryNameAndArticlesSize categoryNameAndArticlesSize = new CategoryNameAndArticlesSize();
        categoryNameAndArticlesSize.setCategoryName(optional.get().getCategoryName());
        categoryNameAndArticlesSize.setArticleNum(optional.get().getArticles().size());


        return categoryNameAndArticlesSize;
    }
}
