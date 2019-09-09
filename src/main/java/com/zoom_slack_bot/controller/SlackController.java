package com.zoom_slack_bot.controller;

import com.zoom_slack_bot.entity.MeetingsList;
import com.zoom_slack_bot.entity.User;
import com.zoom_slack_bot.util.JWTUtil;
import org.json.JSONObject;
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

    private JWTUtil jwt;
    private static final Logger LOGGER = LoggerFactory.getLogger(SlackController.class);

    /**
     * Date from and to is in format yyyy.mm.dd, if needed, can be added to request params
     */
    private static final String ZOOM_RECORDINGS = "https://api.zoom.us/v2/users/%s" +
            "/recordings?page_size=30&mc=false&trash=<boolean>&from=<date>&to=<date>";
    private static final String ZOOM_USER_INFO = "https://api.zoom.us/v2/users/%s" +
            "/?login_type=<string>";
    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/init/zoom")
    public ModelAndView setZoomParams(@RequestParam(value = "zoom_api_key") String zoomApiKey,
                                @RequestParam(value = "zoom_api_secret") String zoomApiSecret,
                                @RequestParam(value = "duration", defaultValue = "1") int days) {
        jwt = new JWTUtil(zoomApiKey, zoomApiSecret, days);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", "Token for Zoom set successfully");
        LOGGER.info("Token for Zoom set successfully");
        return modelAndView;
    }

    @PostMapping("/recordings")
    public String getRecordings(@RequestParam(value = "text") String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt.getJwt());//TODO make it form DB by email
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlString = String.format(ZOOM_RECORDINGS, email);
        try {
            ResponseEntity<MeetingsList> responseEntity =
                    restTemplate.exchange(urlString, HttpMethod.GET, entity, MeetingsList.class);
            MeetingsList meetings = responseEntity.getBody();
            if (meetings != null
                    && !meetings.getMeetings().isEmpty()
                    && !meetings.getMeetings().get(0).getRecordingFiles().isEmpty()) {
                String playUrl = meetings.getMeetings().get(0).getRecordingFiles().get(0).getPlayUrl();
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
    public JSONObject getUserSettings(@RequestParam(value = "text") String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt.getJwt());//TODO make it form DB by email
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String urlString = String.format(ZOOM_USER_INFO, email);
        JSONObject response = new JSONObject();
        try {
            ResponseEntity<User> responseEntity =
                    restTemplate.exchange(urlString, HttpMethod.GET, entity, User.class);
            if (responseEntity.getBody() != null) {
                String textResponse = responseEntity.getBody().toString();
                LOGGER.info("User email was used");
                response.put("response_type", "in_channel");
                response.put("text", textResponse);
                return response;
            } else {
                response.put("response_type", "in_channel");
                response.put("text", "Response was empty");
                return response;
            }
        } catch (HttpClientErrorException e){
            String exceptionMessage = "There were some problems with connection:\n" +
                    e.getResponseBodyAsString();
            LOGGER.warn(exceptionMessage);
            response.put("response_type", "in_channel");
            response.put("text", exceptionMessage);
        }
        return response;
    }

    @PostMapping("/test")
    public String getSlackSlashRequest(@RequestParam(value = "text") String string){
        LOGGER.info(string);
        return "{\"response_type\": \"in_channel\",\"text\":\"" + string + "\"}";
    }
}
