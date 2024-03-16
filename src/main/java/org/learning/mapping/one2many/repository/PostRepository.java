package org.learning.mapping.one2many.repository;

import org.learning.mapping.one2many.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
