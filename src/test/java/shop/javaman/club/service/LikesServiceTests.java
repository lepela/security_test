package shop.javaman.club.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import shop.javaman.club.entity.Likes;
import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.Note;
import shop.javaman.club.entity.dto.LikesDto;
import shop.javaman.club.repository.MemberRepository;
import shop.javaman.club.repository.NoteRepository;

@SpringBootTest
@Log4j2
public class LikesServiceTests {
  @Autowired
  private LikesService service; 
  LikesDto dto = LikesDto.builder().mno(100L).num(1L).build();

  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private NoteRepository noteRepository;

  @Test
  public void testGet() {
    log.info(service.get(dto));
  }

  @Test
  public void testToggle() {
    service.toggle(dto);

  }

  @Test
  public void testInsertDummy() {
    List<Long> mnos = new ArrayList<>(memberRepository.findAll().stream().map(Member::getMno).toList());
    List<Long> nums  = new ArrayList<>(noteRepository.findAll().stream().map(Note::getNum).toList());

    Collections.shuffle(mnos);
    Collections.shuffle(nums);

    // mnos.subList(0, (int)(mnos.size() * .2));

    List<LikesDto> likesDtos = new ArrayList<>();
    for(int i = 0 ; i < mnos.size() ; i++) {
      for(int j = 0 ; j < nums.size() ; j++) {
        likesDtos.add(LikesDto.builder().mno(mnos.get(i)).num(nums.get(j)).build());
      }
    }

    log.info(likesDtos.size());

    likesDtos = likesDtos.subList(0, likesDtos.size() / 5);

    log.info(likesDtos.size());

    likesDtos.forEach(dto -> service.toggle(dto));
    
  }
}
