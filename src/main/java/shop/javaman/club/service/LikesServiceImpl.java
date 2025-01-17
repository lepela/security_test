package shop.javaman.club.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import shop.javaman.club.entity.composite.LikesId;
import shop.javaman.club.entity.dto.LikesDto;
import shop.javaman.club.repository.LikesRepository;
import shop.javaman.club.repository.MemberRepository;

@Service
@Log4j2
public class LikesServiceImpl implements LikesService {
  @Autowired
  private LikesRepository repository;
  @Autowired
  private MemberRepository memberRepository;

  @Override
  public boolean get(LikesDto dto) {
    if(dto.getMno() == null) {
      Long mno = memberRepository.findByEmail(dto.getEmail()).getMno();
      dto.setMno(mno);
    }
    return repository.findById(new LikesId(dto)).isPresent();
  }

  @Override
  public boolean toggle(LikesDto dto) {
    if(dto.getMno() == null) {
      Long mno = memberRepository.findByEmail(dto.getEmail()).getMno();
      dto.setMno(mno);
    }
    boolean ret = get(dto);
    if(ret) {
      repository.delete(toEntity(dto));
    } 
    else {
      repository.save(toEntity(dto));
    }
    return ret;
  }
}
