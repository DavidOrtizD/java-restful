package com.david.rest.webservices.restfulwebservices.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.rest.webservices.restfulwebservices.post.PostJpa;

@Repository
public interface PostJpaRepository extends JpaRepository<PostJpa, Integer>{

}
