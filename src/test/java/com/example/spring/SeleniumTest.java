package com.example.spring;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

public class SeleniumTest {

    @Test
    public void testOpen() throws Exception {

        WebDriver driver = new ChromeDriver();
        // 사이트 에서 원하는데로 이동이 가능하다 그리고 데이터를 받아올수있다
        driver.get("http://gs25.gsretail.com/gscvs/ko/products/event-goods#");
        // url 화면 이동

        Thread.sleep(3000);
        // 3초간격

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,1000)");
        // 스크롤이동

        WebElement ele = driver.findElement(By.cssSelector("#TWO_TO_ONE"));
        // css 찾기

        ele.click();
        // 클릭

        Thread.sleep(2000);

        List<WebElement> list = driver.findElements(By.cssSelector(".prod_list li"));
        // 부모를찾아서 리스트로 담기
        list.forEach(webElement -> {
            WebElement imgEle = webElement.findElement(By.cssSelector("img"));
            WebElement cost = webElement.findElement(By.cssSelector("span"));
            // 이미지 찾기
            System.out.println(imgEle.getAttribute("alt"));
            System.out.println(imgEle.getAttribute("src"));
            System.out.println(cost.getText());
            // getText() text꺼내기
        });

    }

    @Test
    public void testSave2() throws Exception {

        String path = "https://image-comic.pstatic.net/webtoon/20853/50/20200221113351_d37ebf82b5f17380e3d763379f0b1a51_IMAG01_1.jpg";
        //이미지 절대경로
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        System.out.println(urlConnection);
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");
        // f12 network 파일클릭 user agent , "해당파일내용"
        InputStream in = urlConnection.getInputStream();

        File fos = new File("C:\\Users\\ehswn\\IdeaProjects\\lsg\\test.jpg");
        // 저장경로

        Files.copy(in, fos.toPath());
        // 카피
    }


    @Test
    //bad code
    public void testSave() throws Exception {
        // 보안이 없을경우 간단한 코드

        String path = "https://image-comic.pstatic.net/nas/user_contents_data/challenge_comic/2021/06/28/fa3030/upload_3473738102062789987.jpeg";

        URL url = new URL(path);

        //read
        InputStream in = url.openStream();

        File fos = new File("D:\\zzz\\sample3.jpg");


        Files.copy(in, fos.toPath());

    }

    //    @Test
//    public void test1()throws Exception {
//
//        System.out.println("Test1................");
//        Document doc = Jsoup.connect("https://comic.naver.com/bestChallenge/list.nhn?titleId=701701").get();
//
//        System.out.println(doc);
//
//        Elements tds = doc.select(".viewList td img");
//
//        tds.forEach(element -> {
//            String imgURL = element.attr("src");
//            System.out.println(imgURL);
//            String title = element.attr("title");
//            System.out.println(title);
//        });
//    }

}
