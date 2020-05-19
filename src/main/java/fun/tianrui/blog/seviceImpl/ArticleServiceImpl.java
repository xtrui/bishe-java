package fun.tianrui.blog.seviceImpl;

import fun.tianrui.blog.entity.Article;
import fun.tianrui.blog.entity.Comment;
import fun.tianrui.blog.repository.ArticleRepository;
import fun.tianrui.blog.service.ArticleService;
import fun.tianrui.blog.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
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
            article.setComments(article.getComments().stream().filter(Comment::isPublic).collect(Collectors.toSet()));
        }
        return article;
    }
}
