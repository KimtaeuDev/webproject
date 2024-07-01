package com.community.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.community.vo.UserVO;

@Mapper
public interface UserMapper {

	// 사용자 정보 중복 확인
	// 아이디 중복 확인
	public Integer isExistUserId(String user_id) throws Exception;
	
	// 별명 중복 확인
	public Integer isExistUserChr(String user_chr) throws Exception;
		
	// 이메일 중복 확인
	public Integer isExistUserEmail(String user_email) throws Exception;

	// 암호화된 비밀번호 확인
	public String readEncryptedUserPw(String user_id) throws Exception;
	
	// 사용자 CRUD
	// 계정 생성
	public int createUser(UserVO userVO) throws Exception;

	// 계정 조회
	public UserVO readUser(String user_id, String user_pw) throws Exception;
	
	// 비밀번호 수정
	public int updateUserPw(String user_pw) throws Exception;

	// 별명 수정
	public int updateUserChr(String user_chr) throws Exception;
	
	// 이메일 수정
	public int updateUserEmail(String user_email) throws Exception;
	
	// 사진 경로 수정
	public int updateUserImage(String user_image) throws Exception;

	// 계정 삭제
	public int deleteUser(String user_id, String user_pw) throws Exception;

}
