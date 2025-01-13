package shop.javaman.club.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.javaman.club.entity.dto.NoteDto;
import shop.javaman.club.repository.MemberRepository;
import shop.javaman.club.repository.NoteRepository;

@Service
@Transactional
public class NoteServiceImpl implements NoteService{
  @Autowired
  private NoteRepository repository;

  @Override
  public int modify(NoteDto dto) {
    repository.save(toEntity(dto));
    return 1;
  }

  @Override
  public Long write(NoteDto dto) {
    return repository.save(toEntity(dto)).getNum();
  }

  @Override
  public NoteDto get(Long num) {
    return toDto(repository.findByNum(num));
  }

  @Override
  public List<NoteDto> list(String email) {
    return repository.findByMemberEmail(email).stream().map(this::toDto).toList();
  }

  @Override
  public int remove(Long num) {
    repository.deleteById(num);
    return 1;
  }
}
