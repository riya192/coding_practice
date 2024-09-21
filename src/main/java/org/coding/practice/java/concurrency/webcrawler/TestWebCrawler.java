package org.coding.practice.java.concurrency.webcrawler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestWebCrawler {

    public static void main(String[] args) throws InterruptedException {
        Map<String, List<String>> links = new HashMap<>();
        links.put("Riya", List.of("Riya -1", "Riya -2", "Riya -4", "Riya -5", "Riya -6"));
        links.put("Riya -1", List.of("Awinash"));
        links.put("Riya -2", List.of("Riya -2 child"));
        links.put("Awinash", List.of("Awinash -1", "Harsh", "Harsh-1", "Harsh-2"));
        links.put("Awinash -1", List.of());
        links.put("Harsh", List.of("Riya -1", "Manju"));
        links.put("Manju", List.of("Manoj -1"));
        links.put("Manoj -1", List.of("Manju"));

        WebCrawler webCrawler = new WebCrawler(links, 3, "Riya");
        webCrawler.crawl();
        WebCrawler.LOGGER.info("Crawl completed");
    }
}
