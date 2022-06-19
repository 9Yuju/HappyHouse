package com.ssafy.happyhouse.component;

import com.ssafy.happyhouse.model.NewsDto;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.text.SimpleDateFormat;

// 네이버 뉴스 '부동산' 검색
@Component
public class JsoupComponent {
	
	// ===================== 처음 뉴스 목록 가져올 때 =========================
	
	public List<NewsDto> getNewsList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		List<NewsDto> list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			final String newsList = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&date="
					+ strToday + "&page=" + i;
			Connection conn = Jsoup.connect(newsList);
			try {
				Document document = conn.get();
				list.addAll(getNewsList(document));
			} catch (IOException ignored) {

			}
		}
		return list;
	}
	
	public List<NewsDto> getNewsList(Document document) {
		Elements newsElement = document.select("div.list_body");
		List<NewsDto> list = new ArrayList<>();
		for (Element element : newsElement.select("dl")) {
			list.add(createNewsDto(element));
		}
		return list;
	}

	public NewsDto createNewsDto(Element dt) {
		NewsDto newsDto = new NewsDto();
		newsDto.setUrl(dt.select("a").attr("href"));
		newsDto.setImgUrl(dt.select("img").attr("src").equals("")
				? "https://ssl.pstatic.net/static.news/image/news/2009/noimage_106x72.png"
				: dt.select("img").attr("src"));
		newsDto.setTitle(dt.select("dt").text());
		String contents = dt.select(".lede").text();
		if (contents.length() > 57)
			contents = contents.substring(0, 57) + "...";
		newsDto.setContents(contents);
		newsDto.setTime(dt.select(".date").text());
		newsDto.setWriting(dt.select(".writing").text());
		return newsDto;
	}

	// ====================== 검색할 때 사용 ==============================================

	public List<NewsDto> SearchNewsList(String search) {
		System.out.println("public List<NewsDto> SearchNewsList(String search)");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		List<NewsDto> list = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			final String newsList = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&date="
					+ strToday + "&page=" + i;
			Connection conn = Jsoup.connect(newsList);

			try {
				Document document = conn.get();
				list.addAll(SearchNewsList(document, search));
			} catch (IOException ignored) {
			}
		}
		return list;
	}

	public List<NewsDto> SearchNewsList(Document document, String search) {
		System.out.println("public List<NewsDto> SearchNewsList(Document document, String search");
		Elements newsElement = document.select("div.list_body");
		List<NewsDto> list = new ArrayList<>();
		for (Element element : newsElement.select("dl")) {
			NewsDto news = createNewsDtoSearch(element, search);
			if (news != null)
				list.add(news);
		}
		return list;
	}

	public NewsDto createNewsDtoSearch(Element dt, String search) {
		System.out.println("public NewsDto createNewsDtoS(Element dt, String search)");
		NewsDto newsDto = new NewsDto();
		newsDto.setUrl(dt.select("a").attr("href"));
		newsDto.setImgUrl(dt.select("img").attr("src").equals("")
				? "https://ssl.pstatic.net/static.news/image/news/2009/noimage_106x72.png"
				: dt.select("img").attr("src"));
		newsDto.setTitle(dt.select("dt").text());
		String contents = dt.select(".lede").text();
		if (contents.length() > 57)
			contents = contents.substring(0, 57) + "...";
		newsDto.setContents(contents);
		newsDto.setTime(dt.select(".date").text());
		newsDto.setWriting(dt.select(".writing").text());
		if (!(newsDto.getTitle().contains(search) || newsDto.getContents().contains(search)))
			return null;
		return newsDto;
	}
}