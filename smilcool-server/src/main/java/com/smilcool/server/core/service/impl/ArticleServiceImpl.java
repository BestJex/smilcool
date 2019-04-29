package com.smilcool.server.core.service.impl;

import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.ArticleMapper;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.ArticleInfo;
import com.smilcool.server.core.pojo.vo.ArticleVO;
import com.smilcool.server.core.pojo.vo.CommentVO;
import com.smilcool.server.core.service.ArticleService;
import com.smilcool.server.core.service.CommentService;
import com.smilcool.server.core.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Angus
 * @date 2019/4/12
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private CommentService commentService;


    @Override
    public Article getArticle(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new SmilcoolException("文章不存在");
        }
        return article;
    }

    @Override
    public List<ArticleVO> getArticleList() {
        List<Article> articleList = articleMapper.selectAll();
        return BeanUtil.copyProp(articleList, ArticleVO.class);
    }

    @Transactional
    @Override
    public Article addArticle(ArticleAddForm form) {
        // 添加资源，获取资源ID
        Integer resourceId = resourceService.addResource(form.getUserId(), form.getResourceTypeId());
        // 添加文章
        Article article = BeanUtil.copyProp(form, Article.class);
        article.setResourceId(resourceId);
        articleMapper.insertSelective(article);
        return getArticle(article.getId());
    }

    @Override
    public ArticleInfo getArticleInfo(Integer id) {
        // 获取文章
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new SmilcoolException("文章不存在");
        }
        // 获取评论
        List<CommentVO> commentList = commentService.getCommentList(article.getResourceId());
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticle(BeanUtil.copyProp(article, ArticleVO.class));
        articleInfo.setCommentList(commentList);
        return articleInfo;
    }
}