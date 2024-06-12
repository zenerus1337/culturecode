package pl.pwr.isk.culturecode.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.isk.culturecode.DTO.CommentDTO;
import pl.pwr.isk.culturecode.model.CommentEntity;
import pl.pwr.isk.culturecode.repository.CommentRepository;
import pl.pwr.isk.culturecode.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/comments")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{placeId}")
    public List<CommentEntity> getCommentsByPlaceId(@PathVariable("placeId") String placeId) {
        return commentService.getCommentsByPlaceId(placeId);
    }

    @PostMapping("/addComment")
    public ResponseEntity<Void> addComment(@RequestBody CommentDTO commentDTO) {
        CommentEntity newComment = new CommentEntity();
        newComment.setComment(commentDTO.getComment());
        newComment.setPlaceId(commentDTO.getPlaceId());
        newComment.setUserId(commentDTO.getUserId());
        newComment.setTimeStamp(commentDTO.getTimeStamp());
        commentService.save(newComment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
