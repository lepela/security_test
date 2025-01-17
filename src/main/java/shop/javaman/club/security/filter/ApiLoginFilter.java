package shop.javaman.club.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import shop.javaman.club.security.util.JWTUtil;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter{
  private JWTUtil jwtUtil;

  public ApiLoginFilter(String defaultFilterProcessUrl, JWTUtil jwtUtil) {
    super(defaultFilterProcessUrl);
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    log.info("======================== ApiLoginFilter.attemptAuthentication() ========================");    
    String email = request.getParameter("email");
    String pw = "1234";
    
    log.info("email : " +  email);
    log.info("pw : " + pw);
    // if(email == null) {
    //   throw new BadCredentialsException("email cannot be null");
    // }
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, pw);
    Authentication authentication = getAuthenticationManager().authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    log.info("적용된 principal확인 :: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    log.info(authentication.getPrincipal()); 
    return authentication;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    log.info(authResult.getName()); 
    log.info(authResult);  // principal
    String email = authResult.getName();
    // 기존 세션에 추가 등록
    SecurityContextHolder.getContext().setAuthentication(authResult);

    try {
      String token = jwtUtil.generateToken(email);
      response.setContentType("text/plain");
      response.getOutputStream().write(token.getBytes());
      response.getOutputStream().flush();
      response.getOutputStream().close();
      log.info("===================== api login success =====================");
      log.info("token info :: " + token);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
  
}
