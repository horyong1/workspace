package com.ja.study.study1029.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.board.mapper.AticleSqlMapper;

@Service
public class AticleService {

    @Autowired
    private AticleSqlMapper aticleSqlMapper;
}
