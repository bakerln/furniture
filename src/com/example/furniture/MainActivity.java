package com.example.furniture;

import java.text.SimpleDateFormat;
import java.util.List;

import com.example.util.HttpCallbackListener;
import com.example.util.CreateHttpGet;
import com.example.model.Furniture;
import com.example.model.Logistics;
import com.example.model.Pic;
import com.example.model.User;
import com.example.model.Vote;
import com.example.util.Json;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private String baseURL_USER = "http://furnituretrace.sinaapp.com/controller/UserInfo.php?action="; 
	private String baseURL_VOTE = "http://furnituretrace.sinaapp.com/controller/VoteInfo.php?action="; 
	private String baseURL_FUR = "http://furnituretrace.sinaapp.com/controller/FurnitureInfo.php?action="; 
	
	
	private Button button_user;
	private Button button_userRegister;
	private Button button_userModify;
	private Button button_Createvote;
	private Button button_Uservote;
	private Button button_VoteList;
	private Button button_fur;
	private Button button_furInfo;
	private Button button_furColl;
	
	
	private EditText username;
	private EditText password;
	private EditText email;
	//fur�ĵ�������������
	private EditText fur;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		username = (EditText)findViewById(R.id.username);
		email = (EditText)findViewById(R.id.useremail);
		password = (EditText)findViewById(R.id.password);
		button_user = (Button)findViewById(R.id.ButtonUser);
		/**
		 * �û���½��֤�ӿ�
		 * ��������û�����
		 * Usercheck
		 */
		
		button_user.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * �õ��û�����
				 * @parameter name
				 * @parameter pwd
				 */
				String name = username.getText().toString();
				String pwd = password.getText().toString();
				String url = baseURL_USER + "check" +"&"+ "username=" + name + "&" + "password=" + pwd;
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener(){

					@Override
					public void onFinish(String response) {
						if(response == "-1"){
							//��½ʧ��
						}else{
							//��½�ɹ�,�����û�
							User user = Json.parseUser(response);
							Log.e("userCheck",user.getUserid()+"");
							Log.e("userCheck",user.getUsername());
							Log.e("userCheck",user.getPassword());
							Log.e("userCheck",user.getEmail());
						}
					}
				});
			}
		});//one ClickListener
		
		/**
		 * �û�ע��ӿ�
		 * ��������û������Userid
		 * UserRegist
		 */
		button_userRegister = (Button)findViewById(R.id.Buttonregister);
		button_userRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * �õ��û�����
				 * @parameter name
				 * @parameter pwd
				 * @parameter email
				 */
				String name = username.getText().toString();
				String pwd = password.getText().toString();
				String mail = email.getText().toString();
				String url = baseURL_USER + "register" +"&"+ "username=" + name + "&" + "password=" + pwd + "&"+ "email=" + mail;
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener(){
					@Override
					public void onFinish(String response) {
						if(response == "-1"){
							//ע��ʧ��
						}else{
							//ע��ɹ�
//							User user = Json.parseUser(response);
							//only userid
							int userid = Integer.parseInt(response);
							Log.e("userRegit",userid+"");
							
						}
					}
					
				});
			}
		});//one ClickListener
		
		/**
		 * �û��޸Ľӿ�
		 * �޷���ֵ��Ĭ��ȫ���ɹ�
		 * UserModify
		 */
		button_userModify = (Button)findViewById(R.id.Buttonmodify);
		button_userModify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//ʵ���еõ���ǰ������user����
				User userEg = new User();
				userEg.setUserid(6);
				userEg.setUsername("username");;
				
				int userid = userEg.getUserid();
				String name = userEg.getUsername();
				String pwd = password.getText().toString();
				String url = baseURL_USER + "modify" + "&" + "userid=" + userid + "&" + "username=" + name + "&" + "password=" + pwd;
				Log.e("userModify", name);
				Log.e("userModify", pwd);
				Log.e("userModify", url);
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener() {
					
					@Override
					public void onFinish(String response) {
//						if(response == "true"){
//							//�޸ĳɹ�����true
//							Log.e("UserModif", "true");
//						}else{
//							//�޸�ʧ��
//							Log.e("UserModif", "false");
//						}
						//Ĭ���޸Ķ�����ȷ��
					}
				});
			}
		});//one ClickListener
		
		
		
		/**
		 * �û�ͶƱ�ӿ�
		 * ����һ���Ҿߵ�ͶƱ(�о���ǰ��ûʲô��)
		 * �������ͶƱ�����Vot_id
		 */
		button_Createvote = (Button)findViewById(R.id.ButtonCreateVote);
		button_Createvote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//ʵ���еõ���ǰ������user����
				User userEg = new User();
				userEg.setUserid(5);
				int userid = userEg.getUserid();
				//ʵ���еõ���ǰ������fur����
				Furniture furEg = new Furniture();
				furEg.setFur_id(1);
				int fur_id = furEg.getFur_id();
				String url = baseURL_VOTE + "create" +"&"+ "userid=" + userid + "&" + "fur_id=" + fur_id;
				Log.e("voteCreate", userid+"");
				Log.e("voteCreate", fur_id+"");
				Log.e("voteCreate", url);
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener(){

					@Override
					public void onFinish(String response) {
						//���ص���vote��voteID
						Log.e("voteCreate",response);
						int vot_id = Integer.parseInt(response);
					}
					
				});
			}
			
		});//one ClickListener
		
		/**
		 * �û�ͶƱ����
		 * ��vote_idͶƱ
		 * ����true/false
		 */
		button_Uservote = (Button)findViewById(R.id.ButtonUserVote);
		button_Uservote.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//ʵ���еõ���vote_id
				Vote voteEg = new Vote();
				voteEg.setVot_id(4);
				int vot_id = voteEg.getVot_id();
				//ʵ���и���value��True��false
				String value = "true";
				String url = baseURL_VOTE + "update" + "&" + "vot_id=" + vot_id + "&" + "value=" + value;
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener() {
					
					@Override
					public void onFinish(String response) {
						if(response == "true"){
							//ͶƱ�ɹ�
							Log.e("vote", "true");
						}else if(response == "false"){
							//ͶƱʧ��
							//TODO
						}else{
							//
						}
					}
				});
			}
		});//one clickListener
		
		/**
		 * ���û���ͶƱ�б�
		 * param userId
		 * return ͶƱ�б�
		 */
		button_VoteList = (Button)findViewById(R.id.ButtonVoteList);
		button_VoteList.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//ʵ���л�õ�ǰUser
				User userEg = new User();
				userEg.setUserid(5);
				int userid = userEg.getUserid();
				String url = baseURL_VOTE + "voteList" + "&" + "userid=" + userid;
				Log.e("votemess",url);
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener() {
					
					@Override
					public void onFinish(String response) {
						//����votelists
						List<Vote> voteLists = Json.parseVoteList(response);
						for(Vote vote:voteLists){
							Log.e("votemess", vote.getVot_id()+"");
							Log.e("votemess", vote.getUserid()+"");
							Log.e("votemess", vote.getFur_id()+"");
							Log.e("votemess", vote.getVot_approve()+"");
							Log.e("votemess", vote.getVot_oppose()+"");
							//Date Format
							String date = new SimpleDateFormat("yyyy-MM-dd").format(vote.getVot_date());
							Log.e("votemess", date);
							//�Ӳ����ƣ���������ڽ����Լ���ʾ
							//������Nullֵ�������������ʹ�ü���
//							Log.e("votemess", vote.getVot_des());
							
						}
					}
				});
			}
		});//one clickListener
		
		
		
		/**
		 * ͨ��fur�ĵ�������ֵ�����б�
		 * ���ص��Ƿ���������furList
		 */
		fur = (EditText)findViewById(R.id.fur_info);
		button_fur = (Button)findViewById(R.id.ButtonFur);
		button_fur.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//����û��鵽��һ������ֵ
				String fur_Info = fur.getText().toString();
				/**
				 * ���Ҳ���Ϊparam��������ѡ��˹̶����ͣ�һ��ֻ��һ��
				 * ������
				 * brand
				 * name
				 * type
				 * style
				 * 
				 * ������brand
				 */
				String param = "fur_style=" + fur_Info;
				
				String url = baseURL_FUR +"furnitureList" +"&"+ param;
				Log.e("fur", param);
				Log.e("fur",url);
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener(){

					@Override
					public void onFinish(String response) {
						List<Furniture> furList = Json.parseFurList(response);
						for(Furniture furn:furList){
							Log.e("fur", furn.getFur_brand());
							Log.e("fur", furn.getFur_material());
							Log.e("fur", furn.getFur_name());
							Log.e("fur", furn.getFur_style());
							Log.e("fur", furn.getFur_type());
							Log.e("fur", furn.getFur_id()+"");
							Log.e("fur", furn.getFur_price()+"");
							String date = new SimpleDateFormat("yyyy-MM-dd").format(furn.getFur_date());
							Log.e("fur", date);
							for(Pic pics:furn.getPic()){
								Log.e("fur", pics.getPic_add());
							}
						}
					}
				});
			}
		});//one ClickListener
		
		/**
		 * params fur_id
		 * return fur����
		 */
		button_furInfo = (Button)findViewById(R.id.ButtonFurInfo);
		button_furInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��ʵ�е�һ���Ҿ߶���
				Furniture furnEg = new Furniture();
				furnEg.setFur_id(1);
				int fur_id = furnEg.getFur_id();
				String url = baseURL_FUR + "detail" + "&" + "fur_id=" + fur_id;
				Log.e("fur", fur_id+"");
				Log.e("fur", url);
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener() {
					
					@Override
					public void onFinish(String response) {
						Furniture furn = Json.parseFur(response);
						Log.e("fur", furn.getFur_brand());
						Log.e("fur", furn.getFur_material());
						Log.e("fur", furn.getFur_name());
						Log.e("fur", furn.getFur_style());
						Log.e("fur", furn.getFur_type());
						Log.e("fur", furn.getFur_id()+"");
						Log.e("fur", furn.getFur_price()+"");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(furn.getFur_date());
						Log.e("fur", date);
						for(Pic pics:furn.getPic()){
							Log.e("fur", pics.getPic_add());
						}
						for(Logistics log:furn.getLog()){
							String dates = new  SimpleDateFormat("yyyy-MM-dd").format(log.getLog_date());
							Log.e("fur", log.getLog_des());
							Log.e("fur", dates);
						}
					}
				});
			}
		});//one ClickListener
		
		/**
		 * �Ƽ��Ҿ�
		 */
		button_furColl = (Button)findViewById(R.id.ButtonFurColl);
		button_furColl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO ���û����
				String type ="";
				String url = baseURL_FUR + "collocation" + "&" + "type=" + type;
				
				CreateHttpGet.sentHttpGet(url, new HttpCallbackListener() {
					
					@Override
					public void onFinish(String response) {
						//���ش����б�
					}
				});
			}
		});//one ClickListener
	}
	
}
