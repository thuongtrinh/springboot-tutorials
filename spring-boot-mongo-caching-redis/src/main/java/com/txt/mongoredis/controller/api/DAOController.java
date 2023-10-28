package com.txt.mongoredis.controller.api;

import com.txt.mongoredis.repositories.mongodb.dao.UserInfoDao;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "DAO Controller")
@RequiredArgsConstructor
@RequestMapping("/api/dao")
@Slf4j
public class DAOController {

    private final UserInfoDao userInfoDao;

    @GetMapping(value = "/get-list-user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListUserInfo(@RequestParam(value = "phone") String phone) {
        log.info("getListUserInfo by phone: {}", phone);
        try {
            return ResponseEntity.ok(userInfoDao.getListUserInfo(phone));
        } catch (Exception e) {
            log.error("ERROR while using getListUserInfo with message: {}", e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/get-list-user-short", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getListUserShort() {
        try {
            return ResponseEntity.ok(userInfoDao.getListUserInfoGroupUserPhone());
        } catch (Exception e) {
            log.error("ERROR while using getListUserShort with message: {}", e.getLocalizedMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
