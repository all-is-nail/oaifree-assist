package org.example.oaifreeassist.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.example.oaifreeassist.entity.OaiAccount;
import org.example.oaifreeassist.service.OaiAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * OAI 账号信息(OaiAccount)表控制层
 *
 * @author makejava
 * @since 2024-06-15 19:19:55
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/oaiAccount")
public class OaiAccountController {

    /**
     * 服务对象
     */
    private final OaiAccountService oaiAccountService;

    /**
     * 分页查询
     *
     * @param pageNum  页码
     * @param pageSize 分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<OaiAccount>> queryByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(this.oaiAccountService.page(new Page<>(pageNum, pageSize)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<OaiAccount> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.oaiAccountService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param oaiAccount 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<String> add(@RequestBody OaiAccount oaiAccount) {
        // 先判断待添加的账号是否已存在
        Long count = oaiAccountService.query().eq("email", oaiAccount.getEmail()).count();
        if (count > 0) {
            return ResponseEntity.badRequest().body("当前账户已存在！");
        }

        boolean save = this.oaiAccountService.save(oaiAccount);
        String result;
        if (save) {
            result = "保存成功！";
        } else {
            result = "保存失败！";
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 编辑数据
     *
     * @param oaiAccount 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<String> edit(@RequestBody OaiAccount oaiAccount) {
        boolean update = this.oaiAccountService.updateById(oaiAccount);
        String result;
        if (update) {
            result = "更新成功！";
        } else {
            result = "更新失败！";
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        boolean ifDelete = this.oaiAccountService.removeById(id);
        String msg;
        if (ifDelete) {
            msg = "删除成功！";
        } else {
            msg = "删除失败！";
        }

        return ResponseEntity.ok(msg);
    }

}

