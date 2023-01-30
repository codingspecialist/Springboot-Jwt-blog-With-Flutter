package com.cos.authjwt.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.cos.authjwt.config.filter.jwt.JwtProps;
import com.cos.authjwt.web.dto.CMRespDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomResponseUtil {
    public static void response(HttpServletResponse resp, CMRespDto cmRespDto, String token) throws Exception {
        ObjectMapper om = new ObjectMapper();
        resp.setContentType("application/json; charset=utf-8");
        resp.setHeader("Authorization", "Bearer " + token);
        PrintWriter out = resp.getWriter();
        resp.setStatus(200);
        String responseBody = om.writeValueAsString(cmRespDto);
        out.println(responseBody);
        out.flush();
    }
}
