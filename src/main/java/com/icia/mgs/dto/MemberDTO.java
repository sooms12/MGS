package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("member")
public class MemberDTO {
    private String mId;         // 아이디
    private String mName;       // 이름
    private String mPw;         // 비밀번호
    private String mGender;     // 성별
    private String mBirth;      // 생일
    private String mEmail;      // 이메일
    private String mPhone;      // 연락처
    private String mAddr;       // 주소
    private String mkId;        // 카카오아이디

    public static MemberDTO toDTO(MemberEntity entity){
        MemberDTO member = new MemberDTO();

        member.setMId(entity.getMId());
        member.setMName(entity.getMName());
        member.setMPw(entity.getMPw());
        member.setMGender(entity.getMGender());
        member.setMBirth(entity.getMBirth());
        member.setMEmail(entity.getMEmail());
        member.setMPhone(entity.getMPhone());
        member.setMAddr(entity.getMAddr());
        member.setMkId(entity.getMkId());

        return member;
    }
}
