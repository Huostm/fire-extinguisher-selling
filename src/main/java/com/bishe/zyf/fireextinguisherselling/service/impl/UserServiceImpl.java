package com.bishe.zyf.fireextinguisherselling.service.impl;
import java.util.Date;
import java.util.UUID;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bishe.zyf.fireextinguisherselling.dto.LoginRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.RegisterRequestDTO;
import com.bishe.zyf.fireextinguisherselling.dto.WechatLoginDTO;
import com.bishe.zyf.fireextinguisherselling.entity.User;
import com.bishe.zyf.fireextinguisherselling.service.UserService;
import com.bishe.zyf.fireextinguisherselling.mapper.UserMapper;
import com.bishe.zyf.fireextinguisherselling.vo.LoginVO;
import com.bishe.zyf.fireextinguisherselling.vo.ResultVO;
import com.bishe.zyf.fireextinguisherselling.vo.WechatLoginVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.appid}")
    private String wechatAppid;
    @Value("${wechat.secret}")
    private String wechatSecret;
    @Value("${wechat.default-avatar}")
    private String defaultAvatar;

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

    @Override
    public ResultVO<WechatLoginVO> wechatLogin(WechatLoginDTO wechatLoginDTO) {
        // 1. 用 code 调微信接口换 openid
        String url = "https://api.weixin.qq.com/sns/jscode2session"
                + "?appid=" + wechatAppid
                + "&secret=" + wechatSecret
                + "&js_code=" + wechatLoginDTO.getCode()
                + "&grant_type=authorization_code";
        String resp;
        try {
            resp = restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return ResultVO.error(500, "调用微信接口失败，请检查网络");
        }
        JSONObject json = JSON.parseObject(resp);
        String openid = json == null ? null : json.getString("openid");
        if (openid == null) {
            // 微信返回 errcode/errmsg，常见原因：code 已过期、appid/secret 配置错误
            String errmsg = json == null ? "未知错误" : json.getString("errmsg");
            return ResultVO.error(401, "微信登录失败：" + errmsg);
        }

        // 2. 用 openid 查库，没有则自动注册普通用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getWechatOpenid, openid);
        queryWrapper.eq(User::getIsDeleted, 0);
        User user = this.getOne(queryWrapper);
        if (user == null) {
            user = new User();
            user.setWechatOpenid(openid);
            user.setNickname("微信用户" + openid.substring(0, 6));
            user.setAvatarUrl(defaultAvatar);
            user.setUserType(0);   // 普通用户
            user.setStatus(0);     // 正常
            this.save(user);
        }
        if (user.getStatus() != null && user.getStatus() == 1) {
            return ResultVO.error(403, "账号已被封禁");
        }

        // 3. token 直接用 openid
        String token = openid;

        // 4. 返回 token + 用户信息
        WechatLoginVO vo = new WechatLoginVO();
        vo.setToken(token);
        vo.setId(user.getId());
        vo.setNickname(user.getNickname());
        vo.setAvatarUrl(user.getAvatarUrl());
        vo.setUserType(user.getUserType());
        return ResultVO.success(vo);
    }

    @Override
    public ResultVO<WechatLoginVO> updateNickname(Long userId, String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            return ResultVO.error("昵称不能为空");
        }
        User user = this.getById(userId);
        if (user == null) {
            return ResultVO.error(401, "用户不存在");
        }
        user.setNickname(nickname.trim());
        this.updateById(user);

        WechatLoginVO vo = new WechatLoginVO();
        vo.setToken(user.getWechatOpenid());
        vo.setId(user.getId());
        vo.setNickname(user.getNickname());
        vo.setAvatarUrl(user.getAvatarUrl());
        vo.setUserType(user.getUserType());
        return ResultVO.success(vo);
    }
}




