package com.controller;

        import com.entity.User;
        import com.mapper.LoginMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.*;
        import javax.servlet.http.HttpSession;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    LoginMapper loginMapper;

    //登入接口
    @ResponseBody
    @PostMapping("/login.html")
    public String login(HttpSession session, @RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
            System.out.println(username + " "+ password);
            user = loginMapper.getUserBy(username);
        if (user != null) {
            session.setAttribute("user",user);
            return "1";
        }else{
            return "0";
        }
    }

    //注册接口
    @ResponseBody
    @PostMapping("/register.html")
    public String register(@RequestBody User user){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        user.setRegistrationDate(df.format(new Date()));
        user.setProperty(0);
        loginMapper.register(user);
        return "1";
    }

    //用户注销接口
    @GetMapping("/cancellation")
    public String cancellation(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }

    //获取用户信息接口
    @ResponseBody
    @GetMapping("/getUserInfo")
    public Map getUserInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        Map res = new HashMap();
        if( user !=null){
            res.put("u_id",user.getU_id());
            res.put("username",user.getUsername());
            res.put("status",1);
        }else{
            res.put("status",0);
        }

        return res;
    }
}
