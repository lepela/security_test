package shop.javaman.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.javaman.club.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
  Member findByEmail(String email);  
  Member findByEmailAndFromSocial(String email, Boolean fromSocial);  
}
