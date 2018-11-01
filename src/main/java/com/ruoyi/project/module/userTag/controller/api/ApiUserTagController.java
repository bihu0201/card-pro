package com.ruoyi.project.module.userTag.controller.api;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.module.userTag.domain.UserTag;
import com.ruoyi.project.module.userTag.service.IUserTagService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 用户标签 信息操作处理
 *
 * @author snailever
 * @date 2018-10-28
 */
@Controller
@RequestMapping("/api/userTag")
@RestController
public class ApiUserTagController extends BaseController
{
    private String prefix = "api/userTag";

    @Autowired
    private IUserTagService userTagService;

    /**
     * 查询用户标签
     */
    @GetMapping("/getBean")
    public AjaxResult getBean(UserTag userTag)
    {
        return ok(userTagService.selectUserTagList(userTag));
    }
    /**
     * 按参数用户标签
     */
    @PostMapping("/getId")
    public AjaxResult get( String id)
    {
        return ok(userTagService.selectUserTagById(id));
    }
    /**
     * 新增保存用户标签
     */
    @Log(title = "用户标签", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addSave(UserTag userTag)
    {
        int tag = userTagService.insertUserTag(userTag);
        if(tag>0){
            return ok("新增成功！");
        }else {
            return ok("新增失败！");
        }

    }
    /**
     * 根据userTag Id修改tag字段 0 或1
     */
    @PostMapping("/updateUserTag")
    public AjaxResult updateUserTag(UserTag userTag)
    {
        int tag = userTagService.updateUserTag(userTag);
        if(tag>0){
            return ok("修改成功！");
        }else {
            return ok("修改失败！");
        }
    }

    /**
     * 删除用户标签
     */
    @Log(title = "用户标签", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    public AjaxResult remove(String ids)
    {
        return ok(userTagService.deleteUserTagByIds(ids));
    }

}
