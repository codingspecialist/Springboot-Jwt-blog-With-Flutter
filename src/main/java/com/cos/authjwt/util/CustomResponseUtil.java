package com.cos.authjwt.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.cos.authjwt.web.dto.CMRespDto;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomResponseUtil {
    public static void response(HttpServletResponse resp, CMRespDto cmRespDto) throws Exception {
        ObjectMapper om = new ObjectMapper();
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter out = resp.getWriter();
        resp.setStatus(400);

        String responseBody = om.writeValueAsString(cmRespDto);
        out.println(responseBody);
        out.flush();
    }
}
