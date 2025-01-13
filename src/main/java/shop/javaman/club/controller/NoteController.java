package shop.javaman.club.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.javaman.club.entity.dto.NoteDto;
import shop.javaman.club.service.NoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {
  private final NoteService service;

  @PostMapping
  public Long postMethodName(@RequestBody NoteDto dto) {
    return service.write(dto);
  }

  @GetMapping("list")
  public List<NoteDto> list(String email) {
    return service.list(email);
  }
  @GetMapping("{num}")
  public NoteDto get(@PathVariable Long num) {
    return service.get(num);
  }

  @PutMapping("{num}")
  public String modify(@PathVariable Long num, @RequestBody NoteDto dto) {
    return service.modify(dto) > 0 ? "success" : "failure" ;
  }

  @DeleteMapping("{num}")
  public String remove(@PathVariable Long num) {
    return service.remove(num) > 0 ? "success" : "failure";
  }
}
