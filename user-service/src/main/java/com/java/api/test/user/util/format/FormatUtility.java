package com.java.api.test.user.util.format;

import com.java.api.test.user.exception.BadRequestException;
import com.java.api.test.user.util.constants.AppConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class FormatUtility {

    public Boolean isValidDate(String rawDate) {


        try {
            LocalDate.parse(rawDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
