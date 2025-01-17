package shop.javaman.club.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import shop.javaman.club.entity.Likes;
import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.Note;
import shop.javaman.club.entity.composite.LikesId;
import shop.javaman.club.entity.dto.NoteDto;
import shop.javaman.club.repository.LikesRepository;
import shop.javaman.club.repository.MemberRepository;
import shop.javaman.club.repository.NoteRepository;

@Service
@Transactional
@Log4j2
public class NoteServiceImpl implements NoteService{
  @Autowired
  private NoteRepository repository;
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private LikesRepository likesRepository;

  @Override
  public int modify(NoteDto dto) {
    repository.save(toEntity(dto));
    return 1;
  }

  @Override
  public Long write(NoteDto dto) {
    Member member = memberRepository.findByEmail(dto.getWriter());
    dto.setMno(member.getMno());
    return repository.save(toEntity(dto)).getNum();
  }

  @Override
  public Optional<NoteDto> get(Long num) {
    long count = likesRepository.count(Example.of(Likes.builder().note(Note.builder().num(num).build()).build()));
    log.info(count);
    return repository.findById(num).map(this::toDto).map(d -> { d.setLikesCnt(count); return d; });
  }

  @Override
  public List<NoteDto> list(String email) {
    return repository.findNotesBy(email).stream().map(o -> {
      NoteDto dto = toDto((Note)o[0]); 
      dto.setLikesCnt((Long)o[1]);
      dto.setAttachCnt((Long)o[2]);
      return dto;
    }).toList();
  }

  @Override
  public int remove(Long num) {
    repository.deleteById(num);
    return 1;
  }

  @Override
  public List<NoteDto> listAll() {
    return repository.findNotes().stream().map(o -> {
      NoteDto dto = toDto((Note)o[0]); 
      dto.setLikesCnt((Long)o[1]);
      dto.setAttachCnt((Long)o[2]);
      return dto;
    }).toList();
  }
}
