package fun.tianrui.blog.controller;

import fun.tianrui.blog.entity.Setting;
import fun.tianrui.blog.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/setting")
public class SettingController {
    SettingRepository settingRepository;

    @Autowired
    public SettingController(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Setting setting) {
        if (setting != null) {
            setting.setId(1L);
            settingRepository.save(setting);
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/get")
    public Setting getSetting() {
        return settingRepository.findById(1L).get();
    }
}
