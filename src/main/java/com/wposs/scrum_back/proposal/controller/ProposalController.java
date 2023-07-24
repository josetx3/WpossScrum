package com.wposs.scrum_back.proposal.controller;

import com.wposs.scrum_back.proposal.service.ProposalService;
import com.wposs.scrum_back.utils.JWTUtil;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityScheme(
        name = "barerAuth",
        type= SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@RestController
@RequestMapping("proposal")
public class ProposalController {
    @Autowired
    private ProposalService proposalService;

    @Autowired
    private JWTUtil jwtUtil;
}
