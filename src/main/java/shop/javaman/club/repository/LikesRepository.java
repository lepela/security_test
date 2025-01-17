package shop.javaman.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.javaman.club.entity.Likes;
import shop.javaman.club.entity.composite.LikesId;

public interface LikesRepository extends JpaRepository<Likes, LikesId>{
  
}
