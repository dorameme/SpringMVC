package hello.thymeleaf.basic;


import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescape(Model model){
        model.addAttribute("data","<b>Hello </b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 12);
        User userB = new User("userB", 22);

        ArrayList<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);
        model.addAttribute("user", userA);
        model.addAttribute("userMap", map);
        model.addAttribute("users", list);
        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionDate", "hello session");
        return "basic/basic-objects";
    }
    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("LocalDateTime", LocalDateTime.now());
        return "basic/date";
    }
    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello"+data;
        }
    }

    @GetMapping("link")
    public String link(Model model)
    {
        model.addAttribute("param1","date1");
        model.addAttribute("param2","date2");
        return "basic/link";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute(){
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        return "basic/each";
    }

    @GetMapping("/condition")
    public String condition(Model model){
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model){
        model.addAttribute("data","Spring!");
        return "basic/comments";
    }
    @GetMapping("/block")
    public String block(Model model){
        addUsers(model);
        return "basic/block";
    }
    private void addUsers(Model model){
        List<User> list=new ArrayList<>();
        list.add(new User("UserA",10 ));
        list.add(new User("UserB",20 ));
        list.add(new User("UserC",30 ));
        model.addAttribute("users",list);
    }
    @Data
    static class User {
        private String username;
        private int age;


        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}
