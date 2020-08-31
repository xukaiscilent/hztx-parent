package com.hztx.admin.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hztx.core.entity.UserRecord;
import com.hztx.core.mapper.user.UserRecordMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xukai
 * @since 2020-08-25
 */
@RestController
@RequestMapping(value = "/user-record",method = RequestMethod.POST)
public class UserRecordController {

    @Resource
    private UserRecordMapper userRecordMapper;


    @RequestMapping("/test")
    public String test(@RequestParam("id")Integer id){
        UserRecord record=userRecordMapper.selectById(id);
        System.out.println(record.getCreateTime());
        return "hah";

    }

    @RequestMapping("/testList")
    public PageInfo<UserRecord> testList(){
        PageHelper.startPage(1,10);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.last("where id <100");
        List<UserRecord> list=userRecordMapper.selectList(wrapper);
        return new PageInfo<UserRecord>(list);

    }


}
