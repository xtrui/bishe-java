package fun.tianrui.blog.seviceImpl;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import fun.tianrui.blog.entity.Article;
import fun.tianrui.blog.entity.Comment;
import fun.tianrui.blog.repository.ArticleRepository;
import fun.tianrui.blog.service.ArticleService;
import fun.tianrui.blog.vo.ArticleIdAndTitle;
import fun.tianrui.blog.vo.ArticleIdAndTitleListAndTotalPage;
import fun.tianrui.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * @author TIANRUI
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository repository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean saveArticle(Article article) {
        repository.save(article);
        return true;
    }

    @Override
    public Article handleVO(ArticleVO articleVO) {

        return null;
    }


    @Override
    public Article findById(Long id) {
        Article article = repository.findById(id).orElse(null);
        if (article != null) {
            article.setComments(article.getComments().stream().filter(Comment::isPublic).collect(Collectors.toList()));
        }
        return article;
    }

    @Override
    public ArticleIdAndTitleListAndTotalPage getArticleListByPage(Integer page) {
        if (null == page || page < 0) {
            page = 1;
        }
        int size = 20;
        PageRequest pageable = PageRequest.of(--page, size, Sort.Direction.DESC, "time");
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        ArticleIdAndTitleListAndTotalPage articleIdAndTitleListAndTotalPage = new ArticleIdAndTitleListAndTotalPage();
        articleIdAndTitleListAndTotalPage.setArticleIdAndTitleList(repository.findAll(pageable).get()
                .map(e -> mapper.map(e, ArticleIdAndTitle.class)).collect(Collectors.toList()));
        articleIdAndTitleListAndTotalPage.setTotalPage(repository.findAll(pageable).getTotalPages());
        return articleIdAndTitleListAndTotalPage;
    }

    ;
}
