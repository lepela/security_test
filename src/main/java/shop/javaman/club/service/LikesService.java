package shop.javaman.club.service;

import shop.javaman.club.entity.Likes;
import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.Note;
import shop.javaman.club.entity.dto.LikesDto;

public interface LikesService {
  boolean toggle(LikesDto dto);
  boolean get(LikesDto dto);

  default Likes toEntity(LikesDto dto) {
    return Likes.builder().member(Member.builder().mno(dto.getMno()).build()).note(Note.builder().num(dto.getNum()).build()).build();
  }
  default LikesDto toDto(Likes likes) {
    return LikesDto.builder()
      .email(likes.getMember().getEmail())
      .mno(likes.getMember().getMno())
      .modDate(likes.getModDate())
      .regDate(likes.getRegDate())
      .num(likes.getNote().getNum())
    .build();
  }

}
