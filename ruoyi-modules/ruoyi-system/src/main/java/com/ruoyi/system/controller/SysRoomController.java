package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.file.FileTypeUtils;
import com.ruoyi.common.core.utils.file.MimeTypeUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.RemoteFileService;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.system.domain.SysRoom;
import com.ruoyi.system.domain.dto.SysRoomDto;
import com.ruoyi.system.service.ISysRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@RestController
@RequestMapping("/room")
public class SysRoomController extends BaseController
{
    @Autowired
    private ISysRoomService sysRoomService;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:room:list")
    @GetMapping("/list")
    public TableDataInfo list(SysRoom sysRoom)
    {
        startPage();
        List<SysRoom> list = sysRoomService.selectSysRoomList(sysRoom);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:room:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRoom sysRoom)
    {
        List<SysRoom> list = sysRoomService.selectSysRoomList(sysRoom);
        ExcelUtil<SysRoom> util = new ExcelUtil<SysRoom>(SysRoom.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:room:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(sysRoomService.selectSysRoomById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:room:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@ModelAttribute SysRoomDto sysRoom)
    {
        MultipartFile file = sysRoom.getImg();
        String extension = FileTypeUtils.getExtension(file);
        if (!sysRoom.getImg().isEmpty()) {
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION))
            {
                return error("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
            }
        }

        R<SysFile> fileResult = remoteFileService.upload(file);
        if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData()))
        {
            return error("文件服务异常，请联系管理员");
        }
        //todo
        String url = fileResult.getData().getUrl();
        sysRoom.setImgUrl(url);
        return toAjax(sysRoomService.insertSysRoom(sysRoom));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:room:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysRoom sysRoom)
    {

        return toAjax(sysRoomService.updateSysRoom(sysRoom));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:room:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysRoomService.deleteSysRoomByIds(ids));
    }
}
