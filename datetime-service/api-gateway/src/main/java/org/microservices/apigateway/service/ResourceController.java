package org.microservices.apigateway.service;

import org.microservices.apigateway.entity.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

import java.text.SimpleDateFormat;

/**
 * Created by zeremit on 10/21/16.
 */
@Controller
public class ResourceController {

    @Value("${resource.ntp.addr}")
    String serverNtpUrl;

    @Value("${resource.timezone.addr}")
    String serverTimezoneUrl;

    @Autowired
    private RestOperations restOperations;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("time")
    public String getTime(Model model){
        Long time = restOperations.getForEntity(serverNtpUrl+ "/full_date", Long.class).getBody();
        String zone = restOperations.getForEntity(serverTimezoneUrl+ "/timezone", String.class).getBody();
        DateTime dateTime = new DateTime();
        dateTime.setTime(new SimpleDateFormat("hh:mm:ss").format(time));
        dateTime.setDate(new SimpleDateFormat("dd MMM yyyy").format(time));
        dateTime.setTimeZone(zone);
        model.addAttribute("datetime",dateTime);
        return "time";

    }
}
