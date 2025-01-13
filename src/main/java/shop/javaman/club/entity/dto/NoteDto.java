package shop.javaman.club.entity.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteDto {
  private Long num;
  private String title;
  private String content;
  private String writer;  
  private Long mno;
  private LocalDateTime regDate, modDate;
}
