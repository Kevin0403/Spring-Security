package org.example.signupwithsecurity.repository;

import org.example.signupwithsecurity.entity.User;
import org.example.signupwithsecurity.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query("SELECT uinfo from UserInfo uinfo where uinfo.user.username = :userName ")
    public UserInfo findUserInfoByUserName(@Param("userName")String username);

}
