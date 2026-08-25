package com.bishe.zyf.fireextinguisherselling.service.impl;
import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.zyf.fireextinguisherselling.dto.LoginRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.RegisterRequestDTO;
import com.bishe.zyf.fireextinguisherselling.entity.User;
import com.bishe.zyf.fireextinguisherselling.service.UserService;
import com.bishe.zyf.fireextinguisherselling.mapper.UserMapper;
import com.bishe.zyf.fireextinguisherselling.vo.LoginVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2026-08-24 20:36:43
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment env;

    @Override
    public ResultVO<LoginVO> login(LoginRequestDTO loginRequestDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,loginRequestDTO.getUsername());
        queryWrapper.eq(User::getUserType,1);
        queryWrapper.eq(User::getIsDeleted,0);
        User user = this.getOne(queryWrapper);
        if (user == null){
            return ResultVO.error(401,"用户不存在");
        }
        if (user.getStatus()==1){
            return ResultVO.error(401,"账号已被封禁");
        }
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(),user.getPassword())){
            return ResultVO.error(401,"密码错误");
        }
        httpSession.setAttribute("userId",user.getId());
        httpSession.setAttribute("userType",user.getUserType());
        LoginVO loginVO = new LoginVO();
        loginVO.setId(user.getId());
        loginVO.setUsername(user.getUsername());
        loginVO.setNickname(user.getNickname());
        loginVO.setUserType(user.getUserType());
        return ResultVO.success(loginVO);
    }

    @Override
    public ResultVO<String> register(RegisterRequestDTO registerRequestDTO) {
        String checkKey = env.getProperty("fire-extinguisher-selling.checkKey");
        if (!checkKey.equals(registerRequestDTO.getCheckKey())){
            return ResultVO.error("你没有资格注册管理员身份");
        }
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        String uuid = UUID.randomUUID().toString();
        String avatarUrl = registerRequestDTO.getAvatarUrl();
        String nickname = registerRequestDTO.getNickname();
        if (nickname==null){
            user.setNickname("管理员"+uuid);
        }else{
            user.setNickname(nickname);
        }
        if (avatarUrl==null){
            user.setAvatarUrl("https://pixnio.com/free-images/2024/09/12/2024-09-12-09-12-03-1152x768.jpg");
        }else {
            user.setAvatarUrl(avatarUrl);
        }
        user.setUserType(1);
        user.setStatus(0);
        boolean saved = this.save(user);
        if (saved){
            return ResultVO.success("注册成功");
        }else{
            return ResultVO.success("注册失败");
        }
    }
}




