package com.stackroute.recommendations.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {
  private final RestTemplate restTemplate;

  @Autowired
  public BookService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String consumeApi(String api){
    return restTemplate.getForObject(api, String.class);
  }
}
