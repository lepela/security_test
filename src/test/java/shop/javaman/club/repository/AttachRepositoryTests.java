package shop.javaman.club.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import shop.javaman.club.entity.Attach;
import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.MemberRole;
import shop.javaman.club.entity.Note;

@SpringBootTest
@Log4j2
public class AttachRepositoryTests {
  @Autowired
  private AttachRepository repository;

  @Test
  @Transactional
  @Rollback(false)
  public void testInsert() {
    for(int i = 0 ; i < 5 ; i++) {
      Attach attach = Attach.builder()
      .uuid(""+i)
      .origin("1.png")
      .note(Note.builder().num(1L).build())
      .build();
  
      repository.save(attach);
    }
  }

  @Test
  @Transactional
  public void testFindByEmail() {
   
  }

  @Test
  @Transactional
  public void testMember() {
    
  }

}
