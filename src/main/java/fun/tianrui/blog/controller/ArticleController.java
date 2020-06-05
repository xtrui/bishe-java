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
import fun.tianrui.blog.vo.ArticleWithIDVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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
        //        article.setComments(article.getComments().stream().sorted(Comparator.comparing(Comment::getTime)).collect(Collectors.toCollection(LinkedHashSet::new)));
        return articleService.findById(id);
    }

    //发表文章
    @PostMapping("/postArticle")
    public String postArticle(@RequestBody ArticleVO articleVO) {
        if (articleVO == null || articleVO.getCategories() == null) return "0";
        Article article = articleUtils.toArticle(articleVO);
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
            page = 1;
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

    //更新文章
    @PostMapping("/updateArticle")
    public String updateArticle(@RequestBody ArticleWithIDVO articleWithIDVO) {

        if (articleWithIDVO == null || articleWithIDVO.getCategories() == null) return "0";
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        ArticleVO articleVO = mapper.map(articleWithIDVO, ArticleVO.class);
        Article article = articleUtils.toArticle(articleVO);
        article.setId(articleWithIDVO.getId());
        article.setContent(article.getContent().concat("\n*\n \n \n \n最近更新" + LocalDateTime.now() + "*"));
        articleService.saveArticle(article);
        return article.getId().toString();
    }

    //删除文章
    @PostMapping("/deleteArticleByIds")
    public boolean deleteArticle(@RequestBody List<Long> ids) {
//        articleRepository.deleteById(1L);
        try {
            ids.forEach(articleRepository::deleteById);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @PostMapping("/deleteArticleById")
    public boolean deleteArticle(Long id) {
//        articleRepository.deleteById(1L);
        try {
            articleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
