package com.ruoyi.project.system.user.controller.wechat;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.module.payLevel.domain.PayLevel;
import com.ruoyi.project.module.payLevel.service.IPayLevelService;
import com.ruoyi.project.module.util.MD5Utils;
import com.ruoyi.project.module.wechat.service.IWeChatAppLoginService;
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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    IWeChatAppLoginService weChatAppLoginService;
    @Autowired
    private IPayLevelService payLevelService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
            return prefix + "/add";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(HttpServletRequest request,String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return ok("ok");
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



    @GetMapping("/user")
    public String userinfo(HttpServletRequest request)
    {
        // 取身份信息
        User user = getUser();
        request.setAttribute("user",user);
        List<PayLevel> payLevels =payLevelService.selectPayLevelList(null);
        request.setAttribute("payLevels",payLevels);

        return prefix + "/userinfo";
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
    public String register(HttpServletRequest request, User user,String vircode)
    {
        String localvircode = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
        if(!vircode.equals(localvircode)){
            request.setAttribute("msg","验证码不合法！");
            return prefix + "/regdit";
        }
       List<User>  userInfo =  userService.selectUserByLName(user.getLoginName());
       if(userInfo.size()>0){
           request.setAttribute("msg","商家登录名已存在！");
           return prefix + "/regdit";
       }
       try {
           //setUser(user);
           user.setPassword("123456");
           user.setIsPass(0);//待审额
           Long[] ary = new Long[2];
           ary[0] = 2L;
           user.setRoleIds(ary);
           user.setDeptId(new Long("103"));//代理商
           Long[] aryPost = new Long[1];
           aryPost[0] = 2L;
           user.setPostIds(aryPost);

           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
           String dateStr = df.format(System.currentTimeMillis());// new Date()为获取当前系统时间，也可使用当前时间戳
           String wechat_code = MD5Utils.encrypt16(dateStr);
           user.setWechatCode(wechat_code);

           int userId = userService.insertUserParam(user);
           String img = weChatAppLoginService.GetPostUrl(user.getUserId().intValue());
           user.setWechatIcon(img);
           userService.updateUserInfo(user);
           request.setAttribute("msg", "商家注册成功！");
       }catch(Exception e){
           return prefix + "/reg";
       }
           return prefix + "/add";
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
    @RequestMapping(value = "/reg")
    public String register() {
        return  "system/wechat/regdit";
    }

}
