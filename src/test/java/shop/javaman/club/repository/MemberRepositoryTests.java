package shop.javaman.club.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.MemberRole;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
  @Autowired
  private MemberRepository repository;

  @Autowired
  private PasswordEncoder encoder;

  @Test
  public void testInsert() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member m = Member
      .builder()
      .email("user" + i + "@javaman.shop")
      .name("사용자"+ i)
      .password(encoder.encode("1234"))
      .build();
      m.addMemberRole(MemberRole.USER);

      if(i > 80) {
        m.addMemberRole(MemberRole.MANAGER);
      }
      if(i > 90) {
        m.addMemberRole(MemberRole.ADMIN);
      }

      repository.save(m);
    });
  }

  @Test
  @Transactional
  public void testFindByEmail() {
    Member m = repository.findByEmail("user100@javaman.shop");
    log.info(m);

    Member m2 = repository.findByEmailAndFromSocial("user100@javaman.shop", false);
    log.info(m2);

    UserDetailsService service;
    User user;
  }

  @Test
  @Transactional
  public void testMember() {
    
  }

}
