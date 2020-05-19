package fun.tianrui.blog;

import fun.tianrui.blog.repository.ArticleRepository;
import fun.tianrui.blog.repository.CategoryRepository;
import fun.tianrui.blog.repository.CommentRepository;
import fun.tianrui.blog.repository.TestRepository;
import fun.tianrui.blog.service.ArticleService;
import fun.tianrui.blog.vo.CategoryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;

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

    @Autowired
    CommentRepository commentRepository;

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
//        PageRequest pageable = PageRequest.of(0, 9, Sort.Direction.DESC, "time");
//        String s = "测试";
//        System.out.println(articleRepository.findByTitleLike("%" + s + "%", pageable));
//        Optional<Comment> optional= commentRepository.findById(1L);
//        System.out.println(optional.get());
        PageRequest pageable = PageRequest.of(2, 9, Sort.Direction.DESC, "id");
        Page<CategoryVO> categories = categoryRepository.findAllWithoutArticleByPage(pageable);
        System.out.println(categories.get().collect(Collectors.toList()));


    }

}
