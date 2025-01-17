package shop.javaman.club.service;

import java.util.List;
import java.util.Optional;

import shop.javaman.club.entity.Attach;
import shop.javaman.club.entity.Member;
import shop.javaman.club.entity.Note;
import shop.javaman.club.entity.dto.AttachDto;
import shop.javaman.club.entity.dto.NoteDto;

public interface NoteService {

  Long write(NoteDto dto);
  Optional<NoteDto> get(Long num);
  List<NoteDto> list(String email);
  List<NoteDto> listAll();
  int modify(NoteDto dto);
  int remove(Long num);

  default Note toEntity(NoteDto dto) {
    Note note = Note.builder()
    .num(dto.getNum())
    .title(dto.getTitle())
    .content(dto.getContent())
    .member(Member.builder().email(dto.getWriter()).mno(dto.getMno()).build())
    .build();

    note.setAttachs(dto.getAttachDtos().stream().map(a -> Attach.builder()
      .uuid(a.getUuid())
      .origin(a.getOrigin())
      .ext(a.getExt())
      .fileName(a.getFileName())
      .image(a.isImage())
      .mime(a.getMime())
      .path(a.getPath())
      .size(a.getSize())
      .url(a.getUrl())
      .note(note)
      .build()).toList()
    );
    return note;
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
    .attachDtos(note.getAttachs().stream().map(a -> AttachDto.builder()
      .ext(a.getExt())
      .fileName(a.getFileName())
      .image(a.isImage())
      .mime(a.getMime())
      .num(a.getNote().getNum())
      .origin(a.getOrigin())
      .path(a.getPath())
      .size(a.getSize())
      .url(a.getUrl())
      .uuid(a.getUuid())
      .build()).toList()
    ).build();
  }
}
