package com.mh.web.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mh.service.WebTtgElectronicService;
@SuppressWarnings("all")
@Controller
@RequestMapping("/excel")
public class WebExcelController extends BaseController {
	
	
	@Autowired
	private WebTtgElectronicService webTtgElectronicService;
	
	@RequestMapping("/read")
	public void goList(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		String filePath = \"d:/excel/gds.xls\";
//		ExcelDataUtils2 util = new ExcelDataUtils2();
//		List<WebGdElectronic> list = util.importExcels(filePath);
//		//this.webTtgElectronicService.insertElectronic(list);
//		this.webTtgElectronicService.insertGdElectronic(list);
//		List<WebGdElectronic> list = this.webTtgElectronicService.getGdList();
//		for (WebGdElectronic webGdElectronic : list) {
//			String path = \"F:/pic/\";
//			File file = new File(path);
//			File[] tempList = file.listFiles();
//			String name = webGdElectronic.getEleGamePic();
//			for (int i = 0; i < tempList.length; i++) {
//				if(tempList[i].getName().indexOf(name) != -1){
//					ExcelDataUtils2.copyFile(path+tempList[i].getName(), filePath + tempList[i].getName());
//				}
//			}
//		}
		
//		String path = \"F:/gdpic/\";
//		File file = new File(path);
//		File[] tempList = file.listFiles();
//		List<WebGdElectronic> list = this.webTtgElectronicService.getGdList();
//		
//		for (File f : tempList) {
//			for (WebGdElectronic webGdElectronic : list) {
//				if(f.getName().indexOf(webGdElectronic.getEleGamePic()) != -1){
//					this.webTtgElectronicService.updateGdPic(f.getName(), webGdElectronic.getId());
//				}
//			}
//		}
//		List<WebTtgElectronic> list = this.webTtgElectronicService.getTtgList();
//		for (WebTtgElectronic webTtgElectronic : list) {
//			if(webTtgElectronic.getEleGameId().equals(\"455\")){
//				continue;
//			}
//	        URL url = new URL(\"https://ams-games.ttms.co/player/assets/images/games/\" + webTtgElectronic.getEleGameId() + \".png\");
//	        URLConnection con = url.openConnection();
//	        con.setConnectTimeout(5 * 1000);
//	        InputStream is = con.getInputStream();
//	        byte[] bs = new byte[1024];
//	        int len;
//	        OutputStream os = new FileOutputStream(\"E://ttgpic//\" + webTtgElectronic.getEleGameId() + \".png\");
//	        while ((len = is.read(bs)) != -1) {
//	            os.write(bs, 0, len);
//	        }
//	        os.close();
//	        is.close();
//		}
		
/*		List<WebNntElectronic> list = new ArrayList<WebNntElectronic>();
		String resXml = doGet("http://img.gsoft88.net/gamelist.aspx?provider=netent");
	//	String resXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><gamelist provider=\"netent\" thumbnails=\"http://img.gsoft88.net/netent.zip\"><game index=\"1\"><game_type>Classic</game_type><game_name>alienrobots</game_name><game_chinese>机器外星人</game_chinese><game_code>alienrobots</game_code><game_serial>200</game_serial><game_image>alienrobots.jpg</game_image><game_active>1</game_active></game><game index=\"2\"><game_type>Slots</game_type><game_name>attraction</game_name><game_chinese>引力</game_chinese><game_code>attraction</game_code><game_serial>201</game_serial><game_image>attraction.jpg</game_image><game_active>1</game_active></game><game index=\"3\"><game_type>Slots</game_type><game_name>BeachLife</game_name><game_chinese>阳光海滩</game_chinese><game_code>beach</game_code><game_serial>202</game_serial><game_image>beach.jpg</game_image><game_active>1</game_active></game><game index=\"4\"><game_type>Slots</game_type><game_name>bigbang</game_name><game_chinese>宇宙大爆</game_chinese><game_code>bigbang</game_code><game_serial>203</game_serial><game_image>big_bang.jpg</game_image><game_active>1</game_active></game><game index=\"5\"><game_type>Classic</game_type><game_name>bloodsuckers</game_name><game_chinese>吸血</game_chinese><game_code>bloodsuckers</game_code><game_serial>204</game_serial><game_image>blood_suckers.jpg</game_image><game_active>1</game_active></game><game index=\"6\"><game_type>Slots</game_type><game_name>demolitionsquad</game_name><game_chinese>破坏小组</game_chinese><game_code>demolitionsquad</game_code><game_serial>205</game_serial><game_image>demolition_squa.jpg</game_image><game_active>1</game_active></game><game index=\"7\"><game_type>Slots</game_type><game_name>Dragonisland</game_name><game_chinese>神龙</game_chinese><game_code>dragonisland</game_code><game_serial>206</game_serial><game_image>dragon_island.jpg</game_image><game_active>1</game_active></game><game index=\"8\"><game_type>Slots</game_type><game_name>Egyptianheroes</game_name><game_chinese>埃及英雄</game_chinese><game_code>egyptianheroes</game_code><game_serial>207</game_serial><game_image>egyptian_heroes.jpg</game_image><game_active>1</game_active></game><game index=\"9\"><game_type>Slots</game_type><game_name>Gonzosquest</game_name><game_chinese>贡左的探</game_chinese><game_code>eldorado</game_code><game_serial>208</game_serial><game_image>Gonzos_quest.jpg</game_image><game_active>1</game_active></game><game index=\"10\"><game_type>Slots</game_type><game_name>lights</game_name><game_chinese>萤光点点</game_chinese><game_code>fireflies</game_code><game_serial>209</game_serial><game_image>fireflies.jpg</game_image><game_active>1</game_active></game><game index=\"11\"><game_type>Slots</game_type><game_name>fisticuffs</game_name><game_chinese>狂热拳击</game_chinese><game_code>fisticuffs</game_code><game_serial>210</game_serial><game_image>fisticuffs.jpg</game_image><game_active>1</game_active></game><game index=\"12\"><game_type>Slots</game_type><game_name>flowers</game_name><game_chinese>能量霸王花</game_chinese><game_code>flowers</game_code><game_serial>211</game_serial><game_image>flowers.jpg</game_image><game_active>1</game_active></game><game index=\"13\"><game_type>Slots</game_type><game_name>fruit case</game_name><game_chinese>水果盘</game_chinese><game_code>fruits</game_code><game_serial>212</game_serial><game_image>fruit_case.jpg</game_image><game_active>1</game_active></game><game index=\"14\"><game_type>Slots</game_type><game_name>jackandbeanstalk</game_name><game_chinese>杰克与魔豆</game_chinese><game_code>jackandbeanstalk</game_code><game_serial>213</game_serial><game_image>jack_and_the_beanstalk.jpg</game_image><game_active>1</game_active></game><game index=\"15\"><game_type>Slots</game_type><game_name>jackhammer2</game_name><game_chinese>杰克哈默垂<game_chinese><game_code>jackhammer2</game_code><game_serial>214</game_serial><game_image>jack_hammer_2.jpg</game_image><game_active>1</game_active></game><game index=\"16\"><game_type>Slots</game_type><game_name>lostisland</game_name><game_chinese>失落岛<game_chinese><game_code>lostisland</game_code><game_serial>215</game_serial><game_image>lostisland.jpg</game_image><game_active>1</game_active></game><game index=\"17\"><game_type>Slots</game_type><game_name>luckyangler</game_name><game_chinese>幸运垂钓者</game_chinese><game_code>luckyangler</game_code><game_serial>216</game_serial><game_image>lucky_angler.jpg</game_image><game_active>1</game_active></game><game index=\"18\"><game_type>Slots</game_type><game_name>magicportal</game_name><game_chinese>魔法之门</game_chinese><game_code>magicportals</game_code><game_serial>217</game_serial><game_image>magic_portals.jpg</game_image><game_active>1</game_active></game><game index=\"19\"><game_type>Slots</game_type><game_name>go bananas</game_name><game_chinese>狂取香蕉</game_chinese><game_code>monkeys</game_code><game_serial>218</game_serial><game_image>go_bananas.jpg</game_image><game_active>1</game_active></game><game index=\"20\"><game_type>Slots</game_type><game_name>muse</game_name><game_chinese>女神谬斯</game_chinese><game_code>muse</game_code><game_serial>219</game_serial><game_image>muse.jpg</game_image><game_active>1</game_active></game><game index=\"21\"><game_type>Slots</game_type><game_name>mythicmaiden</game_name><game_chinese>神话少女</game_chinese><game_code>mythicmaiden</game_code><game_serial>220</game_serial><game_image>mythic_maiden.jpg</game_image><game_active>1</game_active></game><game index=\"22\"><game_type>Slots</game_type><game_name>Wonky wabbits</game_name><game_chinese>菜园觅食</game_chinese><game_code>rabbits</game_code><game_serial>221</game_serial><game_image>wonky_wabbits.jpg</game_image><game_active>1</game_active></game><game index=\"23\"><game_type>Slots</game_type><game_name>spacewars</game_name><game_chinese>星际大战</game_chinese><game_code>spacewars</game_code><game_serial>224</game_serial><game_image>spacewars.jpg</game_image><game_active>1</game_active></game><game index=\"24\"><game_type>Slots</game_type><game_name>south park reel chaos</game_name><game_chinese>南方公园2</game_chinese><game_code>southpark2</game_code><game_serial>225</game_serial><game_image>south_park_reel_chaos.jpg</game_image><game_active>1</game_active></game><game index=\"25\"><game_type>Slots</game_type><game_name>starburst</game_name><game_chinese>闪<星空</game_chinese><game_code>starburst</game_code><game_serial>226</game_serial><game_image>starburst.jpg</game_image><game_active>1</game_active></game><game index=\"26\"><game_type>Slots</game_type><game_name>subtopia</game_name><game_chinese>海底工业</game_chinese><game_code>subtopia</game_code><game_serial>227</game_serial><game_image>subtopia.jpg</game_image><game_active>1</game_active></game><game index=\"27\"><game_type>Slots</game_type><game_name>thief</game_name><game_chinese>神偷</game_chinese><game_code>thief</game_code><game_serial>228</game_serial><game_image>thief.jpg</game_image><game_active>1</game_active></game><game index=\"28\"><game_type>Slots</game_type><game_name>wildturkey</game_name><game_chinese>野生火鸡</game_chinese><game_code>wildturkey</game_code><game_serial>229</game_serial><game_image>wild_turkey.jpg</game_image><game_active>1</game_active></game><game index=\"29\"><game_type>Slots</game_type><game_name>wishmaster</game_name><game_chinese>神灯精灵</game_chinese><game_code>wishmaster</game_code><game_serial>230</game_serial><game_image>the_wish_master.jpg</game_image><game_active>1</game_active></game><game index=\"30\"><game_type>Slots</game_type><game_name>zombies</game_name><game_chinese>僵尸</game_chinese><game_code>zombies</game_code><game_serial>231</game_serial><game_image>zombies.jpg</game_image><game_active>1</game_active></game><game index=\"31\"><game_type>Slots</game_type><game_name>deadoralive</game_name><game_chinese>生或</game_chinese><game_code>deadoralive</game_code><game_serial>232</game_serial><game_image>dead_or_alive.jpg</game_image><game_active>1</game_active></game><game index=\"32\"><game_type>Slots</game_type><game_name>reelseal</game_name><game_chinese>卷偷</game_chinese><game_code>reelsteal</game_code><game_serial>233</game_serial><game_image>reel_steal.jpg</game_image><game_active>1</game_active></game><game index=\"33\"><game_type>Slots</game_type><game_name>piggyriches</game_name><game_chinese>富贵</game_chinese><game_code>piggyriches</game_code><game_serial>234</game_serial><game_image>piggy_riches.jpg</game_image><game_active>1</game_active></game><game index=\"34\"><game_type>Slots</game_type><game_name>victorious</game_name><game_chinese>辉煌胜利</game_chinese><game_code>victorious</game_code><game_serial>235</game_serial><game_image>victorious.jpg</game_image><game_active>1</game_active></game><game index=\"35\"><game_type>Slots</game_type><game_name>twinspin</game_name><game_chinese>双轴旋转</game_chinese><game_code>twinspin</game_code><game_serial>236</game_serial><game_image>twinspin.jpg</game_image><game_active>1</game_active></game><game index=\"36\"><game_type>Classic</game_type><game_name>kingofchicago</game_name><game_chinese>芝加哥之</game_chinese><game_code>reelpoker</game_code><game_serial>237</game_serial><game_image>king_of_chicago.jpg</game_image><game_active>1</game_active></game><game index=\"37\"><game_type>Slots</game_type><game_name>secretofthestones</game_name><game_chinese>神秘之石</game_chinese><game_code>secretofthestones</game_code><game_serial>223</game_serial><game_image>secretofthestones.jpg</game_image><game_active>1</game_active></game><game index=\"38\"><game_type>Slots</game_type><game_name>tornado</game_name><game_chinese>飓风</game_chinese><game_code>tornado</game_code><game_serial>239</game_serial><game_image>tornado.jpg</game_image><game_active>1</game_active></game><game index=\"39\"><game_type>Slots</game_type><game_name>hit2split</game_name><game_chinese>分裂</game_chinese><game_code>hit2split</game_code><game_serial>240</game_serial><game_image>hit2split.jpg</game_image><game_active>1</game_active></game><game index=\"40\"><game_type>Slots</game_type><game_name>steamtower</game_name><game_chinese>蒸汽巨塔</game_chinese><game_code>steamtower</game_code><game_serial>241</game_serial><game_image>steamtower.jpg</game_image><game_active>1</game_active></game><game index=\"41\"><game_type>Slots</game_type><game_name>fruitshop</game_name><game_chinese>水果</game_chinese><game_code>fruitshop</game_code><game_serial>242</game_serial><game_image>fruitshop.jpg</game_image><game_active>1</game_active></game><game index=\"42\"><game_type>Slots</game_type><game_name>ghostpirates</game_name><game_chinese>幽灵海盗</game_chinese><game_code>ghostpirates</game_code><game_serial>243</game_serial><game_image>ghostpirates.jpg</game_image><game_active>1</game_active></game><game index=\"43\"><game_type>Slots</game_type><game_name>southpark</game_name><game_chinese>南方公园 </game_chinese><game_code>southpark</game_code><game_serial>245</game_serial><game_image>southpark.jpg</game_image><game_active>1</game_active></game><game index=\"44\"><game_type>Slots</game_type><game_name>eggomatic</game_name><game_chinese>小鸡</game_chinese><game_code>eggomatic</game_code><game_serial>247</game_serial><game_image>eggomatic.jpg</game_image><game_active>1</game_active></game><game index=\"45\"><game_type>Slots</game_type><game_name>reelrush</game_name><game_chinese>狂热卷轴</game_chinese><game_code>reelrush</game_code><game_serial>248</game_serial><game_image>reelrush.jpg</game_image><game_active>1</game_active></game><game index=\"46\"><game_type>Slots</game_type><game_name>dracula</game_name><game_chinese>吸血僵尸</game_chinese><game_code>dracula</game_code><game_serial>186</game_serial><game_image>dracula.jpg</game_image><game_active>1</game_active></game><game index=\"47\"><game_type>Slots</game_type><game_name>vegasparty</game_name><game_chinese>维加斯派</game_chinese><game_code>vegasparty</game_code><game_serial>195</game_serial><game_image>vegasparty.jpg</game_image><game_active>1</game_active></game><game index=\"48\"><game_type>Slots</game_type><game_name>spinata grande</game_name><game_chinese>西班牙斗</game_chinese><game_code>colossalpinatas</game_code><game_serial>196</game_serial><game_image>colossalpinatas.jpg</game_image><game_active>1</game_active></game><game index=\"49\"><game_type>Progressive</game_type><game_name>Cosmic Fortune</game_name><game_chinese>宇宙财富</game_chinese><game_code>cosmicfortune</game_code><game_serial>238</game_serial><game_image>cosmicfortune.jpg</game_image><game_active>1</game_active></game><game index=\"50\"><game_type>Slots</game_type><game_name>crimescene</game_name><game_chinese>犯罪现场</game_chinese><game_code>crimescene</game_code><game_serial>191</game_serial><game_image>crimescene.jpg</game_image><game_active>1</game_active></game><game index=\"51\"><game_type>Slots</game_type><game_name>wildrockets</game_name><game_chinese>疯狂火箭</game_chinese><game_code>wildrockets</game_code><game_serial>193</game_serial><game_image>wildrockets.jpg</game_image><game_active>1</game_active></game><game index=\"52\"><game_type>Progressive</game_type><game_name>hallofgods</game_name><game_chinese>神殿</game_chinese><game_code>hallofgods</game_code><game_serial>174</game_serial><game_image>hallofgods.jpg</game_image><game_active>1</game_active></game><game index=\"53\"><game_type>Slots</game_type><game_name>frankenstein</game_name><game_chinese>科学怪人</game_chinese><game_code>frankenstein</game_code><game_serial>194</game_serial><game_image>frankenstein.jpg</game_image><game_active>1</game_active></game><game index=\"54\"><game_type>Slots</game_type><game_name>Creature of the Black Lagoon</game_name><game_chinese>黑湖怪人</game_chinese><game_code>blacklagoon</game_code><game_serial>188</game_serial><game_image>blacklagoon.jpg</game_image><game_active>1</game_active></game><game index=\"55\"><game_type>Classic</game_type><game_name>thunderfist</game_name><game_chinese>雷霆之拳</game_chinese><game_code>thunderfist</game_code><game_serial>190</game_serial><game_image>thunderfist.jpg</game_image><game_active>1</game_active></game><game index=\"56\"><game_type>Slots</game_type><game_name>wildwater</game_name><game_chinese>冲浪</game_chinese><game_code>wildwater</game_code><game_serial>164</game_serial><game_image>wildwater.jpg</game_image><game_active>1</game_active></game><game index=\"57\"><game_type>Classic</game_type><game_name>voodoo</game_name><game_chinese>巫术</game_chinese><game_code>voodoo</game_code><game_serial>163</game_serial><game_image>voodoo.jpg</game_image><game_active>1</game_active></game><game index=\"58\"><game_type>Progressive</game_type><game_name>Geisha Wonder</game_name><game_chinese>艺伎</game_chinese><game_code>geisha</game_code><game_serial>184</game_serial><game_image>geisha.jpg</game_image><game_active>1</game_active></game><game index=\"59\"><game_type>Classic</game_type><game_name>horus</game_name><game_chinese>荷鲁</game_chinese><game_code>horus</game_code><game_serial>169</game_serial><game_image>horus.jpg</game_image><game_active>1</game_active></game><game index=\"60\"><game_type>Progressive</game_type><game_name>icy wonder</game_name><game_chinese>冰冷的奇</game_chinese><game_code>ice</game_code><game_serial>182</game_serial><game_image>ice.jpg</game_image><game_active>1</game_active></game><game index=\"61\"><game_type>Progressive</game_type><game_name>Arabian Nights</game_name><game_chinese><<零一</game_chinese><game_code>arabian</game_code><game_serial>189</game_serial><game_image>arabian.jpg</game_image><game_active>1</game_active></game><game index=\"62\"><game_type>Classic</game_type><game_name>Excalibur</game_name><game_chinese>神剑</game_chinese><game_code>excalibur</game_code><game_serial>172</game_serial><game_image>Excalibur.jpg</game_image><game_active>1</game_active></game><game index=\"63\"><game_type>Progressive</game_type><game_name>Megafortune</game_name><game_chinese>超级财富</game_chinese><game_code>megajackpot</game_code><game_serial>160</game_serial><game_image>megajackpot.jpg</game_image><game_active>1</game_active></game><game index=\"64\"><game_type>Progressive</game_type><game_name>Super lucky frog</game_name><game_chinese>幸运青蛙</game_chinese><game_code>frog</game_code><game_serial>159</game_serial><game_image>frog.jpg</game_image><game_active>1</game_active></game><game index=\"65\"><game_type>Classic</game_type><game_name>Thrillspin</game_name><game_chinese>刺激旋转</game_chinese><game_code>thrillspin</game_code><game_serial>165</game_serial><game_image>thrillspin.jpg</game_image><game_active>1</game_active></game><game index=\"66\"><game_type>Classic</game_type><game_name>Groovy 60</game_name><game_chinese><<时代</game_chinese><game_code>retro-groovy60</game_code><game_serial>167</game_serial><game_image>retro-groovy60.jpg</game_image><game_active>1</game_active></game><game index=\"67\"><game_type>Classic</game_type><game_name>viking</game_name><game_chinese>维京时代</game_chinese><game_code>viking</game_code><game_serial>173</game_serial><game_image>viking.jpg</game_image><game_active>1</game_active></game><game index=\"68\"><game_type>Progressive</game_type><game_name>Tiki wonder</game_name><game_chinese>夏威夷风</game_chinese><game_code>tiki</game_code><game_serial>177</game_serial><game_image>tiki.jpg</game_image><game_active>1</game_active></game><game index=\"69\"><game_type>Classic</game_type><game_name>troll</game_name><game_chinese>巨魔</game_chinese><game_code>troll</game_code><game_serial>171</game_serial><game_image>troll.jpg</game_image><game_active>1</game_active></game><game index=\"70\"><game_type>Classic</game_type><game_name>simsalabim</game_name><game_chinese>simsalabim</game_chinese><game_code>simsalabim</game_code><game_serial>244</game_serial><game_image>simsalabim.jpg</game_image><game_active>1</game_active></game><game index=\"71\"><game_type>Classic</game_type><game_name>wildwitches</game_name><game_chinese>女巫</game_chinese><game_code>wildwitches</game_code><game_serial>170</game_serial><game_image>wildwitches.jpg</game_image><game_active>1</game_active></game><game index=\"72\"><game_type>Classic</game_type><game_name>goldenshamrock</game_name><game_chinese>金色三叶</game_chinese><game_code>goldenshamrock</game_code><game_serial>183</game_serial><game_image>goldenshamrock.jpg</game_image><game_active>1</game_active></game><game index=\"73\"><game_type>Classic</game_type><game_name>Tales of Krakow</game_name><game_chinese>克拉科夫的故</game_chinese><game_code>krakow</game_code><game_serial>161</game_serial><game_image>krakow.jpg</game_image><game_active>1</game_active></game><game index=\"74\"><game_type>Classic</game_type><game_name>fortuneteller</game_name><game_chinese>算命先生</game_chinese><game_code>fortuneteller</game_code><game_serial>162</game_serial><game_image>fortuneteller.jpg</game_image><game_active>1</game_active></game><game index=\"75\"><game_type>Classic</game_type><game_name>diamonddogs</game_name><game_chinese>钻石</game_chinese><game_code>diamonddogs</game_code><game_serial>246</game_serial><game_image>diamonddogs.jpg</game_image><game_active>1</game_active></game><game index=\"76\"><game_type>Classic</game_type><game_name>super80</game_name><game_chinese>超级80</game_chinese><game_code>retro-super80</game_code><game_serial>168</game_serial><game_image>retro-super80.jpg</game_image><game_active>1</game_active></game><game index=\"77\"><game_type>Classic</game_type><game_name>funky 70</game_name><game_chinese>时髦70</game_chinese><game_code>retro-funky70</game_code><game_serial>166</game_serial><game_image>retro-funky70.jpg</game_image><game_active>1</game_active></game><game index=\"78\"><game_type>Classic</game_type><game_name>spellcast</game_name><game_chinese>法师</game_chinese><game_code>wizards</game_code><game_serial>175</game_serial><game_image>wizards.jpg</game_image><game_active>1</game_active></game><game index=\"79\"><game_type>Mini</game_type><game_name>starburstmini</game_name><game_chinese>闪<星空迷你</game_chinese><game_code>starburstmini</game_code><game_serial>249</game_serial><game_image>starburstmini.jpg</game_image><game_active>1</game_active></game><game index=\"80\"><game_type>Slots</game_type><game_name>blackjackonedk</game_name><game_chinese>21点dk</game_chinese><game_code>blackjackonedk</game_code><game_serial>144</game_serial><game_image> blackjackonedk.jpg</game_image><game_active>1</game_active></game><game index=\"81\"><game_type>Slots</game_type><game_name>blackjackflash</game_name><game_chinese>21</game_chinese><game_code>blackjackflash</game_code><game_serial>145</game_serial><game_image> blackjackflash.jpg</game_image><game_active>1</game_active></game><game index=\"82\"><game_type>Slots</game_type><game_name>jacksorbettermultiplehand</game_name><game_chinese>jacksorbettermultiplehand</game_chinese><game_code>jacksorbettermultiplehand</game_code><game_serial>146</game_serial><game_image> jacksorbettermultiplehand.jpg</game_image><game_active>1</game_active></game><game index=\"83\"><game_type>Slots</game_type><game_name>jokerwildmultiplehand</game_name><game_chinese>jokerwildmultiplehand</game_chinese><game_code>jokerwildmultiplehand</game_code><game_serial>147</game_serial><game_image> jokerwildmultiplehand.jpg</game_image><game_active>1</game_active></game><game index=\"84\"><game_type>Slots</game_type><game_name>europeanroulette3</game_name><game_chinese>欧洲轮盘</game_chinese><game_code>europeanroulette3</game_code><game_serial>148</game_serial><game_image> europeanroulette3.jpg</game_image><game_active>1</game_active></game><game index=\"85\"><game_type>Slots</game_type><game_name>roulette2adv</game_name><game_chinese>高级轮盘</game_chinese><game_code>roulette2adv</game_code><game_serial>149</game_serial><game_image> roulette2adv.jpg</game_image><game_active>1</game_active></game><game index=\"86\"><game_type>Slots</game_type><game_name>dazzle_not_mobile</game_name><game_chinese>炫丽</game_chinese><game_code>dazzle_not_mobile</game_code><game_serial>150</game_serial><game_image> dazzle_not_mobile.jpg</game_image><game_active>1</game_active></game><game index=\"87\"><game_type>Slots</game_type><game_name>stickers_not_mobile</game_name><game_chinese>水果贴纸</game_chinese><game_code>stickers_not_mobile</game_code><game_serial>151</game_serial><game_image> stickers_not_mobile.jpg</game_image><game_active>1</game_active></game><game index=\"88\"><game_type>Slots</game_type><game_name>invisibleman_not_mobile</game_name><game_chinese>隐形</game_chinese><game_code>invisibleman_not_mobile</game_code><game_serial>181</game_serial><game_image> invisibleman_not_mobile.jpg</game_image><game_active>1</game_active></game><game index=\"89\"><game_type>Slots</game_type><game_name>robinhood</game_name><game_chinese>罗宾</game_chinese><game_code>robinhood</game_code><game_serial>185</game_serial><game_image> robinhood.jpg</game_image><game_active>1</game_active></game><game index=\"90\"><game_type>Slots</game_type><game_name>pyramid_not_mobile</game_name><game_chinese>金字<不死之路</game_chinese><game_code>pyramid_not_mobile</game_code><game_serial>192</game_serial><game_image> pyramid_not_mobile.jpg</game_image><game_active>1</game_active></game><game index=\"91\"><game_type>Slots</game_type><game_name>neonstaxx_not_mobile</game_name><game_chinese>霓虹</game_chinese><game_code>neonstaxx_not_mobile</game_code><game_serial>152</game_serial><game_image> neonstaxx_not_mobile.jpg</game_image><game_active>1</game_active></game><game index=\"92\"><game_type>Slots</game_type><game_name>alien</game_name><game_chinese>异形</game_chinese><game_code>alien</game_code><game_serial>153</game_serial><game_image> alien.jpg</game_image><game_active>1</game_active></game><game index=\"93\"><game_type>Slots</game_type><game_name>sparks_not_mobile</game_name><game_chinese>星火</game_chinese><game_code>sparks_not_mobile</game_code><game_serial>154</game_serial><game_image> sparks_not_mobile.jpg</game_image><game_active>1</game_active></game><game index=\"94\"><game_type>Slots</game_type><game_name>crusaders</game_name><game_chinese>十字</game_chinese><game_code>crusaders</game_code><game_serial>155</game_serial><game_image> crusaders.jpg</game_image><game_active>1</game_active></game><game index=\"95\"><game_type>Slots</game_type><game_name>jackhammer</game_name><game_chinese>杰克哈默</game_chinese><game_code>jackhammer</game_code><game_serial>157</game_serial><game_image> jackhammer.jpg</game_image><game_active>1</game_active></game><game index=\"96\"><game_type>Slots</game_type><game_name>evolution</game_name><game_chinese>进化</game_chinese><game_code>evolution</game_code><game_serial>158</game_serial><game_image> evolution.jpg</game_image><game_active>1</game_active></game><game index=\"97\"><game_type>Slots</game_type><game_name>devil</game_name><game_chinese>魔鬼</game_chinese><game_code>devil</game_code><game_serial>176</game_serial><game_image> devil   .jpg</game_image><game_active>1</game_active></game><game index=\"98\"><game_type>Slots</game_type><game_name>boombrothers</game_name><game_chinese>爆破兄弟</game_chinese><game_code>boombrothers</game_code><game_serial>178</game_serial><game_image> boombrothers.jpg</game_image><game_active>1</game_active></game><game index=\"99\"><game_type>Slots</game_type><game_name>pacific</game_name><game_chinese>太平</game_chinese><game_code>pacific</game_code><game_serial>179</game_serial><game_image> pacific  .jpg</game_image><game_active>1</game_active></game><game index=\"100\"><game_type>Slots</game_type><game_name>scarface</game_name><game_chinese>疤面煞星</game_chinese><game_code>scarface</game_code><game_serial>180</game_serial><game_image> scarface.jpg</game_image><game_active>1</game_active></game><game index=\"101\"><game_type>Slots</game_type><game_name>hooksheroes_not_mobile</game_name><game_chinese>铁钩英雄</game_chinese><game_code>hooksheroes_not_mobile</game_code><game_serial>197</game_serial><game_image> hooksheroes_not_mobile.jpg</game_image><game_active>1</game_active></game></gamelist>";
		InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(resXml.getBytes()),"utf-8");
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(reader);
		Element rootElm = doc.getRootElement();		//根节点
		List<Element> rootList = rootElm.elements();//根节点下的所有子节点
		JSONObject obj = new JSONObject();
		int i = 1;
		for (Element child : rootList) {
			List<Element> elements = child.elements();
			WebNntElectronic electronic = new WebNntElectronic();
			for (Element object : elements) {
				if(object.getName().equals("game_type")){
					electronic.setEleGameType1(object.getTextTrim());
				}
				if(object.getName().equals("game_name")){
					//electronic.setEleGameType1(object.getTextTrim());
					electronic.setEleGameEnname(object.getTextTrim());
				}
//				if(object.getName().equals("game_chinese")){
//					// electronic.setEleGameType1(object.getTextTrim());
//					electronic.setEleGameCnname(object.getTextTrim());
//					electronic.setRemark(object.getTextTrim());
//				}
				if(object.getName().equals("game_code")){
					//electronic.setEleGameType1(object.getTextTrim());
					electronic.setEleGameCode(object.getTextTrim());
				}
				if(object.getName().equals("game_serial")){
					//electronic.setEleGameType1(object.getTextTrim());
					electronic.setEleGameId(object.getTextTrim());
				}
				if(object.getName().equals("game_image")){
					//electronic.setEleGameType1(object.getTextTrim());
					electronic.setEleGamePic(object.getTextTrim());
				}
				if(object.getName().equals("game_active")){
					electronic.setEleGameActive(Integer.parseInt(object.getTextTrim()));
				}
			}
			electronic.setIsWater(1);
			electronic.setStatus(1);
			electronic.setEleIndex(i);
			++i;
			list.add(electronic);
		}
		this.webTtgElectronicService.insertNntElectronic(list);*/
		
		this.webTtgElectronicService.testRollBack();
		
	}
	
	public static void main(String[] args) throws Exception {
		String resXml = doGet("http://img.gsoft88.net/gamelist.aspx?provider=netent");
		InputStreamReader reader = new InputStreamReader(new ByteArrayInputStream(resXml.getBytes()));
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(reader);
		Element rootElm = doc.getRootElement();		//根节点
		List<Element> rootList = rootElm.elements();//根节点下的所有子节点
		JSONObject obj = new JSONObject();
	}
	
	
	private static String doGet(String url) throws Exception {
		InputStream inputStream = null;
	    InputStreamReader inputStreamReader = null;
	    BufferedReader reader = null;
		try {
			java.net.URL reqUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.108 Safari/537.36");
			conn.setRequestMethod("GET");  
			conn.setDoInput(true);  
			conn.setDoOutput(true);  
			conn.setInstanceFollowRedirects(true);  
			conn.connect();
			int status = conn.getResponseCode();
			if(status == 200){
				StringBuffer resXml = new StringBuffer();
			    String tempLine = null;
			    inputStream = conn.getInputStream();
			    inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			    reader = new BufferedReader(inputStreamReader);
			    while ((tempLine = reader.readLine()) != null) {
			    	resXml.append(tempLine);
			    }
			    String xml  = new String(resXml.toString().getBytes(), "utf-8");
			    return resXml.toString();
			}
		} finally{
			if(null != reader){
				reader.close();
			}
			if(null != inputStreamReader){
				inputStreamReader.close();
			}
			if(null != inputStream){
				inputStream.close();
			}
		}
		return "";
	}
}
