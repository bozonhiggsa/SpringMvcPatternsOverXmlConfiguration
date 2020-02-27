package com.application.springMvc.controller;

import com.application.springMvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for requests handling
 * @author Ihor Savchenko
 * @version 1.0
 */
@Controller
public class MvcController {

    @Autowired
    ServletConfig servletConfig;

    @Autowired
    ServletContext servletContext;

    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    HttpSession httpSession;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/reference/{ref}")
    public String choosePage(@PathVariable("ref") int ref){
        if(ref == 1){
            return "redirect:/page1";
        } else if(ref == 2){
            return "redirect:/page2";
        } else if(ref == 3){
            return "redirect:/page3";
        }
        return null;
    }

    @RequestMapping(value = "page1", method = RequestMethod.GET)
    public String renderingPage1(Model model) {
        User user = new User();
        user.setLogin("default");
        user.setPassword("default");
        user.setLevel(0);
        model.addAttribute("user", user);
        return "user1";
    }

    @RequestMapping(value = "/addUser1", method = RequestMethod.POST)
    public String createUser1(@ModelAttribute("user") User user,
                             Model model) {
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("level", user.getLevel());

        return "/result1";
    }

    @RequestMapping(value = "page2", method = RequestMethod.GET)
    public String renderingPage2() {
        return "user2";
    }

    @RequestMapping(value = "/addUser2", method = RequestMethod.POST)
    public String createUser2(@RequestParam(value = "login") String login,
                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "level") int level,
                              Model model) {
        model.addAttribute("login", login);
        model.addAttribute("password", password);
        model.addAttribute("level", level);

        return "/result2";    }

    @RequestMapping(value = "page3", method = RequestMethod.GET)
    public ModelAndView renderingPage3() {
        return new ModelAndView("user3", "user", new User());
    }

    @RequestMapping(value = "/addUser3", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("user") User user,
                             ModelMap model) {
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("level", user.getLevel());

        return "/result3";
    }

    @RequestMapping("/handle1")
    public HttpEntity<String> handle1() {
        HttpHeaders responseHeaders = new HttpHeaders();
        System.out.println("CacheControl: " + responseHeaders.getCacheControl());
        System.out.println("Accept: " + responseHeaders.getAccept());
        System.out.println("Connection: " + responseHeaders.getConnection());
        System.out.println("Date: " + responseHeaders.getDate());
        System.out.println("LastModified: " + responseHeaders.getLastModified());
        System.out.println("ETag: " + responseHeaders.getETag());
        System.out.println("Pragma: " + responseHeaders.getPragma());
        System.out.println("Expires: " + responseHeaders.getExpires());
        System.out.println("Allow: " + responseHeaders.getAllow());
        System.out.println("Current time: " + new Date(System.currentTimeMillis()));
        responseHeaders.set("MyResponseHeader", "MyValue");
        responseHeaders.setCacheControl("private, max-age=0, must-revalidate");
        responseHeaders.setExpires(System.currentTimeMillis() + 3600000);
        responseHeaders.setPragma("must-revalidate, max-age=1000");
        responseHeaders.setLastModified(System.currentTimeMillis());

        return new HttpEntity<String>("Hello World", responseHeaders);
    }

    @RequestMapping("/handle2")
    public ResponseEntity<String> handle2() {
        URI uri = URI.create("/found");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        responseHeaders.setLocation(uri);
        return new ResponseEntity<String>("Hello World", responseHeaders,
                HttpStatus.FOUND);
    }

    @RequestMapping(value = "/found", method = RequestMethod.GET)
    public String handler() {
        return "/result4";
    }

    @RequestMapping("/handle3")
    public String handle3() {
        System.out.println("got access to ServletConfig. ServletName retrieved: " + servletConfig.getServletName());
        servletConfig.getServletContext();
        System.out.println("got access to ServletContext from ServletConfig");
        System.out.println("got access to ServletContext. ContextPath retrieved: " + servletContext.getContextPath());
        System.out.println("got access to HttpServletRequest. Request method retrieved: " + httpRequest.getMethod().toString());
        System.out.println("got access to HttpSession. idSession retrieved: " + httpSession.getId());
        return "/result5";
    }

    @RequestMapping(value = "/companyEmployee/{company}/employeeData/{employee}",
            method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, String>> getEmployeeAndCompany(
            @MatrixVariable(pathVar = "company") Map<String, String> company,
            @MatrixVariable(pathVar = "employee") Map<String, String> employee) {
        Map<String, String> map = new HashMap<String, String>(company);
        map.putAll(employee);
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }

}
