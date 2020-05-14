package fun.tianrui.blog;

import fun.tianrui.blog.repository.ArticleRepository;
import fun.tianrui.blog.repository.CategoryRepository;
import fun.tianrui.blog.repository.TestRepository;
import fun.tianrui.blog.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    TestRepository repository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
//    @Qualifier("articleServiceImpl")
            ArticleService articleService;

    @Test
    void contextLoads() {
//        Article article = new Article();
//        article.setTitle("000");
//        article.setContent("000");
//        Category category = new Category();
//        category.setCategoryName("生活");
//        article.setCategories(new HashSet<Category>());
//        category.setId(4L);
//        article.getCategories().add(category);
//        categoryRepository.save(category);
//        System.out.println(articleService.findByTitle("000"));
//        System.out.println(articleService.findById(7L));

//        System.out.println(categoryRepository.findAllWithoutArticle());
        PageRequest pageable = PageRequest.of(0, 9, Sort.Direction.DESC, "time");
        String s = "测试";
        System.out.println(articleRepository.findByTitleLike("%" + s + "%", pageable));

    }

}
