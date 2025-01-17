package shop.javaman.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import lombok.extern.log4j.Log4j2;
import shop.javaman.club.entity.Likes;
import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.Note;

@SpringBootTest
@Log4j2
public class LikesRepositoryTests {
  @Autowired
  private LikesRepository repository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private NoteRepository noteRepository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  public void testInsert() {
    Likes likes = Likes.builder()
      .member(Member.builder().mno(100L).build()) 
      .note(Note.builder().num(1L).build())
    .build();

    repository.save(likes);
  }

  @Test
  public void testDelete() {
    repository.delete(Likes.builder().member(Member.builder().mno(100L).build()).note(Note.builder().num(1L).build()).build());
  }

  @Test
  public void testCountByNum() {
    repository.count(Example.of(Likes.builder().note(Note.builder().num(1L).build()).build()));
  }

  @Test
  public void testInsertDummy() {
    long memberCount = memberRepository.count();
    long noteCount = noteRepository.count();
  }
}
