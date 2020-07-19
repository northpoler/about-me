package pro.jianbing.aboutme.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.jianbing.aboutme.common.controller.BaseController;
import pro.jianbing.aboutme.entity.HouseMoney;
import pro.jianbing.aboutme.service.HouseMoneyService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author DefaultAccount
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
    private final
    HouseMoneyService houseMoneyService;

    @Autowired
    public HouseController(HouseMoneyService houseMoneyService) {
        this.houseMoneyService = houseMoneyService;
    }
    @GetMapping("")
    public String search(Model model){
        List<HouseMoney> list =  houseMoneyService.getAll();
        BigDecimal plan = new BigDecimal(0);
        BigDecimal real = new BigDecimal(0);
        for (HouseMoney houseMoney : list) {
            plan = plan.add(new BigDecimal(houseMoney.getPlan()+""));
            real = real.add(new BigDecimal(houseMoney.getReal()+""));
        }
        model.addAttribute("process",real+"/"+plan);
        model.addAttribute("list",list);
        return "house";
    }
}
