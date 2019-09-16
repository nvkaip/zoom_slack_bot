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
     * Date "from" and "to" is in format yyyy.mm.dd, if needed, can be added to request params
     */
    private static final String ZOOM_RECORDINGS = "https://api.zoom.us/v2/users/%s" +
            "/recordings?page_size=30&mc=false&trash=<boolean>&from=<date>&to=<date>";
    private static final String ZOOM_USER_INFO = "https://api.zoom.us/v2/users/%s" +
            "/?login_type=<string>";
    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity<String> entity;
    private JSONObject responseBody;

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
        entity = new HttpEntity<>(headers);
        String urlString = String.format(ZOOM_RECORDINGS, email);
        responseBody = new JSONObject();
        try {
            ResponseEntity<MeetingsList> responseEntity =
                    restTemplate.exchange(urlString, HttpMethod.GET, entity, MeetingsList.class);
            MeetingsList meetings = responseEntity.getBody();
            if (meetings != null
                    && !meetings.getMeetings().isEmpty()
                    && !meetings.getMeetings().get(0).getRecordingFiles().isEmpty()) {
                String playUrl = meetings.getMeetings().get(0).getRecordingFiles().get(0).getPlayUrl();
                responseBody.put("response_type", "in_channel");
                responseBody.put("text", playUrl);
                LOGGER.info("Video URL " + playUrl + " was sent");
            } else {
                String textResponse = "There is no video's url yet";
                responseBody.put("response_type", "in_channel");
                responseBody.put("text", textResponse);
                LOGGER.info(textResponse);
            }
        } catch (HttpClientErrorException e){
            String exceptionMessage = "There were some problems with connection";
            responseBody.put("response_type", "in_channel");
            responseBody.put("text", exceptionMessage);
            LOGGER.warn(exceptionMessage + e.getResponseBodyAsString());
        }
        return responseBody.toString();
    }

    @PostMapping("/user")
    public String getUserSettings(@RequestParam(value = "text") String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt.getJwt());//TODO make it form DB by email
        entity = new HttpEntity<>(headers);
        String urlString = String.format(ZOOM_USER_INFO, email);
        responseBody = new JSONObject();
        try {
            ResponseEntity<User> responseEntity =
                    restTemplate.exchange(urlString, HttpMethod.GET, entity, User.class);
            if (responseEntity.getBody() != null) {
                String textResponse = responseEntity.getBody().toString();
                responseBody.put("response_type", "in_channel");
                responseBody.put("text", textResponse);
                LOGGER.info(responseBody.toString());
            } else {
                responseBody.put("response_type", "in_channel");
                responseBody.put("text", "Response was empty");
            }
        } catch (HttpClientErrorException e){
            String exceptionMessage = "There were some problems with connection";
            LOGGER.warn(exceptionMessage + e.getResponseBodyAsString());
            responseBody.put("response_type", "in_channel");
            responseBody.put("text", exceptionMessage);
        }
        return responseBody.toString();
    }

    @PostMapping("/test")
    public String getSlackSlashRequest(@RequestParam(value = "text") String string){
        responseBody = new JSONObject();
        LOGGER.info(string);
        responseBody.put("response_type", "in_channel");
        responseBody.put("text", string);
        return responseBody.toString();
    }
}
