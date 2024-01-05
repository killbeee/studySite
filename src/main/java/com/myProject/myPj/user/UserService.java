package com.myProject.myPj.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myProject.myPj.common.mapper.CommonMapper;
import com.myProject.myPj.vo.UserVo;

@Service
public class UserService {
	 @Autowired
	 private UserMapper userMapper;
	 @Autowired
	 private CommonMapper commonMapper;

	 
	public String getKaKaoAccessToken(String code){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=824cabc1a0ddf96e8395db33ea0399fd"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://www.brightdev.info/kakao/callback"); // TODO 인가코드 받은 redirect_uri 입력
            //sb.append("&redirect_uri=http://rong3531.synology.me:4700/kakao/callback"); // TODO 인가코드 받은 redirect_uri 입력
           // sb.append("&redirect_uri=http://localhost:8081/kakao/callback"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            sb.append("&client_secret=" + "s5q6VO8kXD6ZAfKbP6OrQDVZhogrAQV3");;
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }
    
	public UserVo createKakaoUser(String token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        Long id = null ;
        UserVo userVO = new UserVo();
        String nick = "";
        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

             id = element.getAsJsonObject().get("id").getAsLong();
             nick = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();
         //   boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
         //   String email = "";
          //  if (hasEmail) {
         //       email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
          //  }

            System.out.println("id : " + id);
          //  System.out.println("email : " + email);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        	userVO.setSocialUserId(id);
        	userVO.setUserNick(nick);
        
        return userVO;
    }

	
	
	
	/**
	 * NAVER AccessToken 처리
	 * @param authorize_code
	 * @return
	 */
	public String getNaverAccessToken (String code) {
			String access_Token = "";
			String reqURL = "https://nid.naver.com/oauth2.0/token";
			
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
	            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=CK6or9NNWc_e97qwwQbg");
	            sb.append("&client_secret=EpWoy0kqy0");
	            sb.append("&redirect_uri=http://localhost:8080/naver/callback");
	          //  sb.append("&redirect_uri=http://rong3531.synology.me:4700/naver/callback"); 
	            sb.append("&code="+code);
//	            sb.append("&state=url_parameter");
	            bw.write(sb.toString());
	            bw.flush();
	            
	            //결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            if(responseCode==200){
		            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
		            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		            String line = "";
		            String result = "";
		            
		            while ((line = br.readLine()) != null) {
		                result += line;
		            }
		            
		            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
		            JsonParser parser = new JsonParser();
		            JsonElement element = parser.parse(result);
		            
		            access_Token = element.getAsJsonObject().get("access_token").getAsString();
		          //refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
		            br.close();
		            bw.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        
	        return access_Token;
	    }
	
	/**
	 * NAVER USER INFO
	 * @param access_Token
	 */
	public UserVo getNaverUserInfo (String access_Token,String code) {
	    //요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
	    String reqURL = "https://openapi.naver.com/v1/nid/me";
	    UserVo userVO = new UserVo();
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        //요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        if(responseCode == 200){
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);
		        
		        JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
		        
		        String name = response.getAsJsonObject().get("name").getAsString();
		        String email = response.getAsJsonObject().get("email").getAsString();
		        String id = response.getAsJsonObject().get("id").getAsString();
		        
		        userVO.setSocialUserId(id);
		        userVO.setUserName(name);
		        userVO.setUserEmail(email);
		        
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return userVO;
	}
	
	
	
	public UserVo getUser(Object id) {
        UserVo userVo = userMapper.getUser(id);

        return userVo;
    }
	public void regRegisteration(UserVo userVo) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("alias", "PUI");
		paramMap.put("tableName", "PJ_USER_INFO");
		String userId = commonMapper.getNextPK(paramMap);
		userVo.setUserId(userId);
        userMapper.regRegisteration(userVo);

    }
}