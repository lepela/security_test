package shop.javaman.club.entity.composite;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.javaman.club.entity.dto.LikesDto;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class LikesId implements Serializable{
  private Long member;
  private Long note;

  public LikesId(LikesDto dto) {
    member = dto.getMno();
    note = dto.getNum();
  }
}
