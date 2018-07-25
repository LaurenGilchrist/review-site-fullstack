package org.wecancodeit.reviewsitefullstack;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Comment findByAuthor(String author);

	

}
