package shop.javaman.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.javaman.club.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{
  Note findByNum(Long num);
  
  List<Note> findByMemberMno(Long mno);

  List<Note> findByMemberEmail(String email);
  
  @Query(
    "select n, count(distinct l) as likescnt, count(distinct a) as attachcnt \r\n" +
    "from tbl_note n left join tbl_likes l on l.note.num = n.num left join tbl_attach a on a.note.num = n.num \r\n" +
    "where n.member.email = :email group by n")
  // @Query(value =  "select n.*, count(DISTINCT l.note_num, l.member_mno) replycnt, count(DISTINCT uuid) attachcnt from tbl_note n \r\n" + //
  //       "left join tbl_member m on m.mno = n.member_mno and email = :email \r\n" + //
  //       "left join tbl_likes l on l.note_num = n.num\r\n" + //
  //       "left join tbl_attach a on a.note_num = n.num\r\n" + //
  //       "group by n.num", nativeQuery = true)
  List<Object[]> findNotesBy(@Param("email") String email);
  
  @Query(
    "select n, count(distinct l) as likescnt, count(distinct a) as attachcnt \r\n" +
    "from tbl_note n left join tbl_likes l on l.note.num = n.num left join tbl_attach a on a.note.num = n.num \r\n" +
    "group by n")
  // @Query(value =  "select n.*, count(DISTINCT l.note_num, l.member_mno) replycnt, count(DISTINCT uuid) attachcnt from tbl_note n \r\n" + //
  //       "left join tbl_member m on m.mno = n.member_mno and email = :email \r\n" + //
  //       "left join tbl_likes l on l.note_num = n.num\r\n" + //
  //       "left join tbl_attach a on a.note_num = n.num\r\n" + //
  //       "group by n.num", nativeQuery = true)
  List<Object[]> findNotes();
}
