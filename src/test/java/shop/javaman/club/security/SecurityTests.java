package shop.javaman.club.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class SecurityTests {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void testEncoder () {
    log.info(passwordEncoder);
    log.info(passwordEncoder.getClass().getName());

    String pw = "12345";
    String encoded = passwordEncoder.encode(pw);

    log.info(pw);
    log.info(encoded);

    // $2a$10$i52iYaqR2VttXl/2SAPWa.6ZDbsosEGzurV/Qd3C8s6hq3LwOUui2
    // $2a$10$ZoY0NyBPXhblfzY210wrJePCBfGZH/baDYJNnA1MZN9GjwwGcbJki

    assertTrue(passwordEncoder.matches(pw, "$2a$10$ZoY0NyBPXhblfzY210wrJePCBfGZH/baDYJNnA1MZN9GjwwGcbJki")); 
    assertTrue(passwordEncoder.matches(pw, "$2a$10$i52iYaqR2VttXl/2SAPWa.6ZDbsosEGzurV/Qd3C8s6hq3LwOUui2")); 
    
  }
}
