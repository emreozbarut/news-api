package com.news.api.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.news.api.config.FeignClientConfiguration;
import com.news.api.enums.SearchAttributes;
import com.news.api.model.GNewsAPIResponse;

@FeignClient(
        value = "gNewsClient",
        url = "${gnews.url}",
        configuration = FeignClientConfiguration.class
)
public interface GNewsClient
{
    @GetMapping("/search")
    ResponseEntity<GNewsAPIResponse> search(@RequestParam("token") String token);
    
    @GetMapping("/search")
    ResponseEntity<GNewsAPIResponse> searchByKeyword(@RequestParam("q") String keyword, @RequestParam("token") String token);

    @GetMapping("/top-headlines")
    ResponseEntity<GNewsAPIResponse> fetchNewArticles(@RequestParam("max") Integer max, @RequestParam("token") String token);

    @GetMapping("/search")
    ResponseEntity<GNewsAPIResponse> searchArticlesWithAttributes(@RequestParam("q") String title, 
                                                                  @RequestParam("in") List<SearchAttributes> searchAttributes, 
                                                                  @RequestParam("token") String token);
}
