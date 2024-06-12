package pl.pwr.isk.culturecode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.isk.culturecode.model.CommentEntity;
import pl.pwr.isk.culturecode.repository.CommentRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    protected CommentRepository commentRepository;

    public List<CommentEntity> getCommentsByPlaceId(String placeId) {
        return commentRepository.findByPlaceId(placeId);
    }

    public CommentEntity save(CommentEntity comment) {
        return commentRepository.save(comment);
    }


}
