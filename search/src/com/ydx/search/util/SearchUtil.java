package com.ydx.search.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ydx.search.bean.Image;



/**
 * 抓取图片
 * @author ydx
 *
 */

public class SearchUtil {
	private static Map<String,String> urls = new HashMap<String,String>();
	static{
		
			
			urls.put("all", "http://www.dbmeinv.com/dbgroup/show.htm"); 					    //所有
			urls.put("ditch", "http://www.dbmeinv.com/dbgroup/show.htm?cid=2");					//大胸妹
			urls.put("hip", "http://www.dbmeinv.com/dbgroup/show.htm?cid=6");					//小翘臀
			urls.put("black", "http://www.dbmeinv.com/dbgroup/show.htm?cid=7");					//黑丝袜
			urls.put("leg", "http://www.dbmeinv.com/dbgroup/show.htm?cid=3");					//大长腿
			urls.put("sex", "http://www.dbmeinv.com/dbgroup/show.htm?cid=1");					//性感妹
			urls.put("art", "http://www.dbmeinv.com/dbgroup/show.htm?cid=5");					//文艺范
			urls.put("fresh", "http://www.dbmeinv.com/dbgroup/show.htm?cid=4");					//小清新

	}
	/**
	 * 根据图片类型和图片显示页码查询图片
	 * @param category 图片类型
	 * @param pageNum 图片显示页码
	 */
	public static List<Image> queryImageList(String category,String pageNum){
		String url = urls.get(category);
		// String urlValue = "";
		// for(int i=1;i<=10;i++){
		// 	urlValue = url+"?pager_offset="+i;
		// 	i++;
		// }
		System.out.print(url);
		List<Image> images = new ArrayList<Image>();
		try{
		Document doc = Jsoup.connect(url)
				 .data("cid",pageNum)		//请求参数
				 .timeout(10000)			//设置超时时间
				 .get();				//get方式得到url
		Elements imgs =doc.getElementsByTag("img");
		
		Image image = null;
		for(Element img : imgs){
			
			String surl = img.attr("src");		//小图片url
			String title = img.attr("title");	//图片标题
			String ourl = img.attr("src");		//原始图片url
			System.out.println(surl+", "+title+","+ourl); //测试使用
			if(surl != null && !"".equals(surl)){
				image = new Image();
				image.setSurl(surl);
				image.setTitle(title);
				image.setOurl(ourl);
				images.add(image);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return images;
	}
	public static void main(String[] args){
		queryImageList("ditch","2");
	}

}
