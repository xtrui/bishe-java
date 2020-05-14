package fun.tianrui.blog.service;

import fun.tianrui.blog.entity.Article;
import fun.tianrui.blog.vo.ArticleVO;

/**
 * @author TIANRUI
 */

public interface ArticleService {
    /**
     * 保存单个文章
     *
     * @param article 文章
     * @return true or false
     */
    boolean saveArticle(Article article);

    Article findById(Long id);

    Article handleVO(ArticleVO articleVO);
}
