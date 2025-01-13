package shop.javaman.club.service;

import java.util.List;

import org.modelmapper.ModelMapper;

import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.Note;
import shop.javaman.club.entity.dto.NoteDto;

public interface NoteService {

  Long write(NoteDto dto);
  NoteDto get(Long num);
  List<NoteDto> list(String email);
  int modify(NoteDto dto);
  int remove(Long num);

  default Note toEntity(NoteDto dto) {
    return Note.builder()
    .num(dto.getNum())
    .title(dto.getTitle())
    .content(dto.getContent())
    .member(Member.builder().email(dto.getWriter()).mno(dto.getMno()).build())
    .build();
  }

  default NoteDto toDto(Note note) {
    return NoteDto.builder()
    .num(note.getNum())
    .title(note.getTitle())
    .content(note.getContent())
    .writer(note.getMember().getEmail())
    .mno(note.getMember().getMno())
    .regDate(note.getRegDate())
    .modDate(note.getModDate())
    .build();
  }
}
