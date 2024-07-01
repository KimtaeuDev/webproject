package com.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.mapper.UserMapper;
import com.community.vo.UserVO;

@Service
public class UserService {
	
	private UserMapper usermapper;
	
	@Autowired
	public UserService(UserMapper usermapper) {
        this.usermapper = usermapper;
    }

	// 로그인
	public int login(String user_id, String user_pw) throws Exception {
		
		//String encryptedUserPw = usermapper.readEncryptedUserPw(user_id); 
		return 0;
		
	}
	
	public int createUser(UserVO userVO) throws Exception {
		return usermapper.createUser(userVO);
	}

	public UserVO readUser(String user_id, String user_pw) throws Exception {
		return usermapper.readUser(user_id, user_pw);
	}
	
	public int updateUserPw(String user_pw) throws Exception {
		return usermapper.updateUserPw(user_pw);
		
	};

	public int updateUserChr(String user_chr) throws Exception {
		return usermapper.updateUserChr(user_chr);
		
	};
	
	public int updateUserEmail(String user_email) throws Exception {
		return usermapper.updateUserEmail(user_email);
		
	};
	
	public int updateUserImage(String user_image) throws Exception {
		return usermapper.updateUserImage(user_image);
		
	};

	public int deleteUser(String id, String pw) throws Exception {
		return usermapper.deleteUser(id, pw);
	}



}
