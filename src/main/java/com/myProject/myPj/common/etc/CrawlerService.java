package com.myProject.myPj.common.etc;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {
    public String getNaverDiv(String param) throws IOException {
        String url = "https://search.shopping.naver.com/search/category/100000010?NaPm=ct%3Dlnd97pxq%7Cci%3Dshoppingwindow%7Ctr%3Dnsh%7Chk%3Daee193d83942f9898455d31763d77a65f1fb408f%7Ctrx%3D";
        
        Document doc = null;
        
        
            doc = Jsoup.connect(url).get();
            
            Elements els =  doc.select("#content");
            String contents = els.toString();
       
        
        
        
        
        return contents;
    }
}
