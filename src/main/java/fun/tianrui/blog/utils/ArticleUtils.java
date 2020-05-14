package fun.tianrui.blog.utils;

import fun.tianrui.blog.entity.Article;
import fun.tianrui.blog.entity.Category;
import fun.tianrui.blog.repository.CategoryRepository;
import fun.tianrui.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class ArticleUtils {
    private final CategoryRepository categoryRepository;

    @Autowired
    public ArticleUtils(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Article toArticle(ArticleVO articleVO) {
        Category category = new Category();
        Article article = new Article();
        articleVO.getCategories().forEach(s -> {
            String[] strs = s.split("\\|", 2);
//              如果id为空则添加在数据库保存
            System.out.println(strs[0]);
            if (strs.length == 1) {
                category.setCategoryName(strs[0]);
                categoryRepository.save(category);
            } else {
                category.setId(Long.parseLong(strs[0]));
                category.setCategoryName(strs[1]);
            }
            article.getCategories().add(category);
        });
        article.setTime(LocalDateTime.now());
        article.setTitle(articleVO.getTitle());
        article.setContent(articleVO.getContent());
        return article;
    }

    public static List<Article> toArticleByPage(Set<Article> articles, Integer page) {
        List<Article> newArticle = new ArrayList<>(articles);
        Collections.reverse(newArticle);
        int totalPage;
        totalPage = articles.size() % 9 == 0 ? articles.size() / 9 : articles.size() / 9 + 1;
        if (page < 1) {
            page = 1;
        }
        ;

        page = page > articles.size() / 9 + 1 ? articles.size() / 9 + 1 : page;
        int endIndex = (page == 1 || page == totalPage) ? articles.size() : 9 * page;

        return newArticle.subList(9 * page - 9, endIndex);
    }
}
