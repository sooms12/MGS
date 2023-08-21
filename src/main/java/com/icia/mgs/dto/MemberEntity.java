package com.icia.mgs.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "MEMBERS")
public class MemberEntity {

    @Id
    @Column
    private String mId;         // 아이디
    @Column
    private String mName;       // 이름
    @Column
    private String mPw;         // 비밀번호
    @Column
    private String mGender;     // 성별
    @Column
    private String mBirth;      // 생일
    @Column
    private String mEmail;      // 이메일
    @Column
    private String mPhone;      // 연락처
    @Column
    private String mAddr;       // 주소
    @Column
    private String mkId;        // 카카오아이디

    public static MemberEntity toEntity(MemberDTO member){
        MemberEntity entity = new MemberEntity();

        entity.setMId(member.getMId());
        entity.setMName(member.getMName());
        entity.setMPw(member.getMPw());
        entity.setMGender(member.getMGender());
        entity.setMBirth(member.getMBirth());
        entity.setMEmail(member.getMEmail());
        entity.setMPhone(member.getMPhone());
        entity.setMAddr(member.getMAddr());
        entity.setMkId(member.getMkId());

        return entity;
    }
}
