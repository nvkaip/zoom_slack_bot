package com.nvkaip.zoom_slack_bot.controller;

import com.nvkaip.zoom_slack_bot.entity.MeetingsList;
import com.nvkaip.zoom_slack_bot.entity.User;
import com.nvkaip.zoom_slack_bot.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SlackController {

    private static String SLACK_HOOK_URL;
    private static JWTUtil JWT;
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackController.class);

    @PostMapping("/init/slack")
    public ModelAndView setSlackParams(@RequestParam(value = "slack_hook") String slackHook) {
        SLACK_HOOK_URL = slackHook;
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", "Web hook URL for Slack set successfully");
        LOGGER.info("Webhook URL for Slack set successfully");
        return modelAndView;
    }

    @PostMapping("/init/zoom")
    public ModelAndView setZoomParams(@RequestParam(value = "zoom_api_key") String zoomApiKey,
                                @RequestParam(value = "zoom_api_secret") String zoomApiSecret,
                                @RequestParam(value = "duration", defaultValue = "1") int days) {
        JWT = new JWTUtil(zoomApiKey, zoomApiSecret, days);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", "Token for Zoom set successfully");
        LOGGER.info("Token for Zoom set successfully");
        return modelAndView;
    }

    @PostMapping("/recordings")
    public String getRecordings(@RequestParam(value = "text") String email) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + JWT.getJwt());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlString = "https://api.zoom.us/v2/users/" + email +
                "/recordings?page_size=30&mc=false&trash=<boolean>&from=<date>" +//date in format yyyy.mm.dd
                "&to=<date>";//date in format yyyy.mm.dd
        try {
            ResponseEntity<MeetingsList> responseEntity =
                    restTemplate.exchange(urlString, HttpMethod.GET, entity, MeetingsList.class);
            MeetingsList meetings = responseEntity.getBody();
            if (meetings != null
                    && !meetings.getMeetings().isEmpty()
                    && !meetings.getMeetings().get(0).getRecording_files().isEmpty()) {
                String playUrl = meetings.getMeetings().get(0).getRecording_files().get(0).getPlay_url();
                LOGGER.info("Video URL " + playUrl + " was sent");
                return "{\"response_type\": \"in_channel\"," +
                        "\"text\":\"" + playUrl + "\"}";
            } else {
                LOGGER.info("There is no video's url yet");
                return "{\"response_type\": \"in_channel\"," +
                        "\"text\":\"There is no video's url yet\"}";
            }
        } catch (HttpClientErrorException e){
            return "There were some problems with connection:\n" +
                    e.getResponseBodyAsString();
        }
    }

    @PostMapping("/user")
    public String getUserSettings(@RequestParam(value = "text") String email) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + JWT.getJwt());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlString = "https://api.zoom.us/v2/users/" +
                email + "/?login_type=<string>";
        try {
            ResponseEntity<User> responseEntity =
                    restTemplate.exchange(urlString, HttpMethod.GET, entity, User.class);
            if (responseEntity.getBody() != null) {
                String response = responseEntity.getBody().toString();
                LOGGER.info("User email was used");
                return "{\"response_type\": \"in_channel\"," +
                        "\"text\":\"" + response + "\"}";
            } else {
                return "{\"response_type\": \"in_channel\"," +
                        "\"text\":\"Response was empty\"}";
            }
        } catch (HttpClientErrorException e){
            return "There were some problems with connection:\n" +
                    e.getResponseBodyAsString();
        }
    }

    @PostMapping("/test")
    public String getSlackSlashRequest(@RequestParam(value = "text") String string){
        LOGGER.info(string);
        return "{\"response_type\": \"in_channel\",\"text\":\"" + string + "\"}";
    }
}
