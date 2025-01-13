package shop.javaman.club.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import shop.javaman.club.security.util.JWTUtil;

@SpringBootTest
@Log4j2
public class JWTTests {
  private JWTUtil jwtUtil;

  @BeforeEach
  public void init() {
    jwtUtil = new JWTUtil();
  }

  @Test

  public void testCreate() throws Exception{
    String email = "user100@javaman.shop";
    String str = jwtUtil.generateToken(email);
    log.info(str);
    
    // eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MzY3NTAwOTksImV4cCI6MTczOTQyODQ5OSwic3ViIjoidXNlcjEwMEBqYXZhbWFuLnNob3AifQ.y3w1UVac4sjQiy_fBSVfBXDWzTq6tKKWt0BltLxzoc9VeVoJVSC7JXg0ISrgrmWD
  }
  
  @Test
  public void testExtract() throws Exception{
    String email = "user100@javaman.shop";
    String token = jwtUtil.generateToken(email);

    Thread.sleep(5000);

    String resultEmail = jwtUtil.validateExtract(token);

    log.info(resultEmail);

    assertEquals(email, resultEmail);
  }
}
