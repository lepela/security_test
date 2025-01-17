package shop.javaman.club.repository;

import java.util.Arrays;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.Note;

@SpringBootTest
@Log4j2
@Transactional
public class NoteRepositoryTests {
  @Autowired
  private NoteRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  @Rollback(false)
  public void testSave() {
    repository.save(Note.builder()
    .title("제목" + 1)
    .content("내용" + 1)
    .member(Member.builder().email("lepelaka@gmail.com").build())
    .build());
    
    
    
    // LongStream.rangeClosed(1, 5).boxed().map(l->
    // Note.builder()
    // .title("제목" + l)
    // .content("내용" + l)
    // .member(Member.builder().mno(100L).build())
    // .build()
    // ).forEach(repository::save);
  }

  @Test
  public void testOne() {
    log.info(repository.findByNum(1L));
  }

  @Test
  public void testList() {
    repository.findByMemberMno(100L).forEach(log::info);
  }
  
  @Test
  public void testList2() {
    repository.findByMemberEmail("user100@javaman.shop").forEach(log::info);
  }

  @Test
  public void testListJPQL() {
    repository.findNotesBy("user100@javaman.shop").forEach(o -> { log.info(Arrays.toString(o)); });
  }
}
