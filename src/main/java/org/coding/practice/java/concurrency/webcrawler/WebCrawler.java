package org.coding.practice.java.concurrency.webcrawler;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class WebCrawler {

    Map<String, List<String>> links;
    int concurrency;
    Set<Thread> threadSet;
    Set<String> visited;
    BlockingQueueWithTimeout<Node> queue;
    String startUrl;

    public static final Logger LOGGER = Logger.getLogger(WebCrawler.class.getName());
    WebCrawler(Map<String, List<String>> graph, int c, String url)
    {
        links = graph;
        concurrency = c;
        threadSet = new HashSet<>();
        startUrl = url;
        queue = new BlockingQueueWithTimeout<>(500);
        visited = new HashSet<>();
    }

    private void instantiateThread()
    {
        for (int i = 0; i < concurrency; i++) {
            Thread t = new Thread(new Worker());
            t.setName("Thread - " + i);
            threadSet.add(t);
        }
    }

    public void crawl() throws InterruptedException {
        instantiateThread();
        queue.add(new Node(startUrl, null, 0));
        addToVisited(startUrl);
        for(Thread t : threadSet)
        {
            t.start();
        }
        for(Thread t : threadSet)
        {
            t.join();
        }
        LOGGER.info("Crawling completed");
    }

    private final class Worker implements Runnable{

        @Override
        public void run() {
            Node url;
            try{
                while ((url = queue.get()) != null)
                {
                    List<String> linked = links.get(url.url);
                    LOGGER.info("thread - " + Thread.currentThread().getName());
                    LOGGER.info("crawled - " + url);
                    if(linked != null && !linked.isEmpty())
                    {
                        for (String l : linked)
                        {
                            if (!isVisited(l))
                            {
                                addToVisited(l);
                                queue.add(new Node(l, url.url, url.level + 1));
                            }
                        }
                    }
                }
            }catch (InterruptedException e)
            {
                LOGGER.info("caught exception in crawling");
            }

        }
    }

    private static final class Node{
        String url;
        String parent;
        int level;

        Node(String u, String p, int l) {
            url = u;
            parent = p;
            level = l;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "url='" + url + '\'' +
                    ", parent='" + parent + '\'' +
                    ", level=" + level +
                    '}';
        }
    }

    private synchronized boolean isVisited(String url)
    {
        return visited.contains(url);
    }

    private synchronized void addToVisited(String url)
    {
        visited.add(url);
    }

}
