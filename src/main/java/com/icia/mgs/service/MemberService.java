package com.icia.mgs.service;

import com.icia.mgs.dao.MemberDAO;
import com.icia.mgs.dao.MemberRepository;
import com.icia.mgs.dto.MemberDTO;
import com.icia.mgs.dto.MemberEntity;
import com.icia.mgs.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private ModelAndView mav;

    private final MemberRepository mrepo;

    private final JavaMailSender mailSender;

    private final PasswordEncoder pwEnc;

    private final MemberDAO mdao;

    private final HttpSession session;

    private Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profile");


    // 아이디 중복 확인
    public String idCheck(String mId) {
        String result = "NO";

        Optional<MemberEntity> entity = mrepo.findById(mId);

        if (entity.isEmpty()) {
            result = "OK";
        }

        return result;
    }

    // 카카오 로그인 아이디체크
    public String idCheck2(String mId) {
        String result = "NO";

        Optional<MemberEntity> entity = mrepo.findById(mId);

        if (entity.isEmpty()) {
            result = "OK";
        }

        return result;
    }

    // 이메일 인증
    public String emailCheck(String mEmail) {
        MimeMessage mail = mailSender.createMimeMessage();

        String uuid = UUID.randomUUID().toString().substring(0, 8);

        String str = "<h2>안녕하세요. 인천일보 아카데미 입니다.</h2>"
                + "<p>인증번호는 <b>" + uuid + "</b> 입니다.</p>";

        try {
            mail.setSubject("인천일보 아카데미 회원가입 인증번호");
            mail.setText(str, "UTF-8", "html");
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(mEmail));

            mailSender.send(mail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return uuid;
    }

    // 회원가입
    public ModelAndView mJoin(MemberDTO member) {
        mav = new ModelAndView();

        // [2] 비밀번호 암호화
        member.setMPw(pwEnc.encode(member.getMPw()));

        // DTO -> Entity로 변형
        MemberEntity entity = MemberEntity.toEntity(member);

        // 가입
        mrepo.save(entity);

        mav.setViewName("index");
        return mav;
    }

    // 회원목록(페이징)
    public List<MemberDTO> pagingList() {
        List<MemberDTO> memberList = new ArrayList<>();

        List<MemberEntity> entityList = mrepo.findAll();

        // entityList의 갯수만큼 반복문 실행
        for (MemberEntity entity : entityList) {
            // 한 사람의 정보 : entity
            // entity에서 dto로 변형 : MemberDTO.toDTO()
            // memberList에 추가 : memberList.add()
            memberList.add(MemberDTO.toDTO(entity));
        }

        return memberList;
    }

    // 회원 검색
    public List<MemberDTO> searchList(SearchDTO search) {
        return mdao.searchList(search);
    }

    // 로그인
    public ModelAndView mLogin(MemberDTO member) {
        mav = new ModelAndView();

        Optional<MemberEntity> entity = mrepo.findById(member.getMId());

        if (entity.isEmpty()) {
            mav.setViewName("member/login");
            mav.addObject("msg","아이디 또는 비밀번호를 확인해주세요.");

            } else if(entity.isPresent()){

            if(pwEnc.matches(member.getMPw(), entity.get().getMPw())){
                member = new MemberDTO();
                member = MemberDTO.toDTO(entity.get());

                session.setAttribute("loginId", member.getMId());
                mav.setViewName("index");

            } else if(!pwEnc.matches(member.getMPw(), entity.get().getMPw())){

                mav.setViewName("member/login");
                mav.addObject("msg","아이디 또는 비밀번호를 확인해주세요.");
            }
        }

        return mav;
    }

    // 회원정보 상세보기
    public ModelAndView mView(String mId) {
        mav = new ModelAndView();

        Optional<MemberEntity> entity = mrepo.findById(mId);

        if(entity.isPresent()){
            MemberDTO member = new MemberDTO();
            member = MemberDTO.toDTO(entity.get());

            mav.setViewName("member/view");
            mav.addObject("view", member);
        } else {
            mav.setViewName("index");
        }

        return mav;
    }

    // 회원정보 수정 페이지 이동
    public ModelAndView mModiForm(String mId) {
        mav = new ModelAndView();

        MemberDTO member = (MemberDTO)mView(mId).getModel().get("view");

        mav.setViewName("member/modify");
        mav.addObject("modify", member);

        return mav;
    }

    // 회원정보 수정
    public ModelAndView mModify(MemberDTO member) {
        mav = new ModelAndView();


        // [2] 비밀번호 암호화
        member.setMPw(pwEnc.encode(member.getMPw()));

        // DTO → Entity로 변형
        MemberEntity entity = MemberEntity.toEntity(member);

        // 수정
        mrepo.save(entity);

        mav.setViewName("redirect:/mView/" + member.getMId());
        return mav;
    }

    // 회원정보 삭제
    public ModelAndView mDelete(String mId) {
        mav = new ModelAndView();
        MemberDTO member = (MemberDTO)mView(mId).getModel().get("view");


        mrepo.deleteById(mId);
        session.removeAttribute("login");
        session.invalidate();
        mav.setViewName("index");

        return mav;
    }

    // 이메일 중복확인
    public String emailCheck2(String mEmail) {
        String result = "NO";

        Optional<MemberEntity> entity = mdao.emailCheck2(mEmail);
        System.out.println("Service : " + entity);
        if (entity.isEmpty()) {
            result = "OK";
        }

        return result;
    }

    public String emailCheck3(String mEmail) {
        String result = "NO";

        Optional<MemberEntity> entity = mdao.emailCheck3(mEmail);
        System.out.println("Service : " + entity);
        if (entity.isEmpty()) {
            result = "OK";
        }

        return result;
    }
}
