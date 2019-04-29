package com.smilcool.server.core.controller;

import com.smilcool.server.common.dto.Result;
import com.smilcool.server.core.pojo.form.CommentAddForm;
import com.smilcool.server.core.pojo.po.Comment;
import com.smilcool.server.core.pojo.vo.CommentVO;
import com.smilcool.server.core.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/1
 */
@Api(tags = "3.1", description = "评论接口")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation("评论添加")
    @PostMapping("/comment")
    public Result<Comment> addComment(@RequestBody @Valid CommentAddForm form) {
        Comment comment = commentService.addComment(form);
        return Result.success(comment);
    }

    @ApiOperation("评论列表")
    @GetMapping("/comment")
    public Result<List<CommentVO>> getCommentList() {
        List<CommentVO> commentList = commentService.getCommentList();
        return Result.success(commentList);
    }

    @ApiOperation("评论列表")
    @GetMapping("/{resourceId}/comment")
    public Result<List<CommentVO>> getCommentList(@PathVariable Integer resourceId) {
        List<CommentVO> commentList = commentService.getCommentList(resourceId);
        return Result.success(commentList);
    }
}
