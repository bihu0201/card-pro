package com.ruoyi.project.system.user.controller.api;

import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.module.memberAward.domain.MemberAward;
import com.ruoyi.project.module.userPay.domain.UserPay;
import com.ruoyi.project.module.userPay.service.IUserPayService;
import com.ruoyi.project.module.util.RandomValidateCodeUtil;
import com.ruoyi.project.system.user.controller.ProfileController;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserForm;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *@Description: 登录验证
 *@params:
 *@Auhtor:snailever
 *@Date: 2018/10/6_17:16
 */
@Controller
@RequestMapping("/api/user")
@RestController
public class ApiUserController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return ok("zz");
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "/error/unauth";
    }

    /**
     * 商户注册
     */
    @PostMapping("/register")
    public AjaxResult register(User user)
    {
            //setUser(user);
            user.setPassword("123456");
            user.setIsPass(0);//待审额
            Long[] ary = new Long[2]; //商家
            ary[0]=100L;
            user.setRoleIds(ary);
            user.setDeptId(new Long("103"));//代理商
           Long[] aryPost = new Long[1];
           aryPost[0]=2L;
           user.setPostIds(aryPost);
           userService.insertUserParam(user);
           return  ok("0","商家注册成功！");
    }


    /**
     * 商户注册
     */
    @GetMapping("/fetchUser")
    public AjaxResult fetchUser(User user)
    {
        List<User> users = userService.selectUserListApi(user);
        if(users.size()>0){
            return  ok("0",users.get(0));
        }else {
            return  ok("999","商家信息获取失败！");
        }

    }



    /**
     * 根据openId判断是否商户，同时获取商户信息
     */
    @GetMapping("/userInfo")
    public AjaxResult userInfo(Long userId)
    {
        UserForm users = userService.userInfo(userId);
        if(users!=null){
            return  ok("0",users);
        }else {
            return  ok("999","商家信息获取失败！");
        }

    }
    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);

            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
           log.error("获取验证码失败>>>> ", e);
        }
    }


}
