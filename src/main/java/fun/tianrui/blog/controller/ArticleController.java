package fun.tianrui.blog.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import fun.tianrui.blog.entity.Article;
import fun.tianrui.blog.repository.ArticleRepository;
import fun.tianrui.blog.service.ArticleService;
import fun.tianrui.blog.utils.ArticleUtils;
import fun.tianrui.blog.vo.ArticleIdAndTitle;
import fun.tianrui.blog.vo.ArticleIdAndTitleListAndTotalPage;
import fun.tianrui.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleUtils articleUtils;
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleService articleService, ArticleUtils articleUtils, ArticleRepository articleRepository) {
        this.articleService = articleService;
        this.articleUtils = articleUtils;
        this.articleRepository = articleRepository;
    }


    @GetMapping("/detail")
    public Article getArticle(Long id) {

        return articleService.findById(id);
    }

    @PostMapping("/postArticle")
    public String postArticle(@RequestBody ArticleVO articleVO) {
        if (articleVO == null || articleVO.getCategories() == null) return "0";
        Article article = articleUtils.toArticle(articleVO);
        System.out.println(article);
        articleService.saveArticle(article);
        return article.getId().toString();
    }

    //查询并分页返回
    @GetMapping("/searchArticle")
    public ArticleIdAndTitleListAndTotalPage searchArticle(String key, Integer page) {
        if (null == page || page < 1) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(--page, 9, Sort.Direction.DESC, "time");
        Page<Article> articles = articleRepository.findByTitleLike("%" + key + "%", pageable);
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        ArticleIdAndTitleListAndTotalPage articleIdAndTitleListAndTotalPage = new ArticleIdAndTitleListAndTotalPage();
        articleIdAndTitleListAndTotalPage.setArticleIdAndTitleList(articles.get()
                .map(e -> mapper.map(e, ArticleIdAndTitle.class)).collect(Collectors.toList()));
        articleIdAndTitleListAndTotalPage.setTotalPage(articles.getTotalPages());
        return articleIdAndTitleListAndTotalPage;
    }

    //获取一页文章
    @GetMapping("/getArticleListByPage")
    public ArticleIdAndTitleListAndTotalPage getArticleListByPage(Integer page) {
        if (null == page || page < 0) {
            page = 0;
        }
        int size = 20;
        PageRequest pageable = PageRequest.of(--page, size, Sort.Direction.DESC, "time");
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        ArticleIdAndTitleListAndTotalPage articleIdAndTitleListAndTotalPage = new ArticleIdAndTitleListAndTotalPage();
        articleIdAndTitleListAndTotalPage.setArticleIdAndTitleList(articleRepository.findAll(pageable).get()
                .map(e -> mapper.map(e, ArticleIdAndTitle.class)).collect(Collectors.toList()));
        articleIdAndTitleListAndTotalPage.setTotalPage(articleRepository.findAll(pageable).getTotalPages());
        return articleIdAndTitleListAndTotalPage;
    }


}
