package com.zoom_slack_bot.controller;

import com.zoom_slack_bot.entity.MeetingsList;
import com.zoom_slack_bot.entity.Token;
import com.zoom_slack_bot.entity.User;
import com.zoom_slack_bot.services.TokenService;
import com.zoom_slack_bot.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@RestController
public class SlackController {

    private JWTUtil jwt;
    private TokenService tokenService;
    /**
     * Date "from" and "to" is in format yyyy.mm.dd, if needed, can be added to request params
     */
    private static final String ZOOM_RECORDINGS = "https://api.zoom.us/v2/users/%s" +
            "/recordings?page_size=30&mc=false&trash=<boolean>&from=<date>&to=<date>";//TODO from and to dates logic
    private static final String ZOOM_USER_INFO = "https://api.zoom.us/v2/users/%s" +
            "/?login_type=<string>";
    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity<String> entity;
    private JSONObject responseBody;

    @Autowired
    public SlackController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/init/zoom")
    public ModelAndView setZoomParams(@RequestParam(value = "zoom_api_key") String zoomApiKey,
                                @RequestParam(value = "zoom_api_secret") String zoomApiSecret,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "duration", defaultValue = "1") int days) {
        Optional<Token> optionalToken = tokenService.getValidTokenByEmail(email, LocalDate.now());
        ModelAndView modelAndView = new ModelAndView("index");
        if (optionalToken.isPresent()){
            modelAndView.addObject("message", "Token is active until: "
                    + optionalToken.get().getExpDate());
            log.info("Token for Zoom already exists and valid until: "
                    + optionalToken.get().getExpDate());
        } else {
            JWTUtil jwt = new JWTUtil(zoomApiKey, zoomApiSecret, days);
            Token newToken = new Token(jwt.getJwt(), email, LocalDate.now().plusDays(days));
            tokenService.saveToken(newToken);
            modelAndView.addObject("message", "Token for Zoom set successfully");
            log.info("Token for Zoom set successfully");
        }
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
                log.info("Video URL " + playUrl + " was sent");
            } else {
                String textResponse = "There is no video's url yet";
                responseBody.put("response_type", "in_channel");
                responseBody.put("text", textResponse);
                log.info(textResponse);
            }
        } catch (HttpClientErrorException e){
            String exceptionMessage = "There were some problems with connection";
            responseBody.put("response_type", "in_channel");
            responseBody.put("text", exceptionMessage);
            log.warn(exceptionMessage + e.getResponseBodyAsString());
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
                log.info(responseBody.toString());
            } else {
                responseBody.put("response_type", "in_channel");
                responseBody.put("text", "Response was empty");
            }
        } catch (HttpClientErrorException e){
            String exceptionMessage = "There were some problems with connection";
            log.warn(exceptionMessage + e.getResponseBodyAsString());
            responseBody.put("response_type", "in_channel");
            responseBody.put("text", exceptionMessage);
        }
        return responseBody.toString();
    }

    @PostMapping("/test")
    public String getSlackSlashRequest(@RequestParam(value = "text") String string){
        responseBody = new JSONObject();
        log.info(string);
        responseBody.put("response_type", "in_channel");
        responseBody.put("text", string);
        return responseBody.toString();
    }
}
