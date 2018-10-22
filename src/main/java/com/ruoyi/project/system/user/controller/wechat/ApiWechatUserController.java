package com.ruoyi.project.system.user.controller.wechat;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.domain.UserForm;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *@Description: 代理商
 *@params:
 *@Auhtor:snailever
 *@Date: 2018/10/6_17:16
 */
@Controller
@RequestMapping("/api/wechat")
public class ApiWechatUserController extends BaseController
{
    private String prefix = "system/wechat";
    @Autowired
    private IUserService userService;


    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
            return prefix + "/add";
    }

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
            Long[] ary = new Long[1];
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
    public AjaxResult userInfo(String wechatCode)
    {
        UserForm users = userService.userInfo(wechatCode);
        if(users!=null){
            return  ok("0",users);
        }else {
            return  ok("999","商家信息获取失败！");
        }

    }

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/reg")
    public String register() {
        return  "system/wechat/regdit";
    }

}
