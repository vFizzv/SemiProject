	$(document).ready(function(){
		$('#loginBtn').click(function(){
			
			const id=$('#id').val();
			const pw=$('#pw').val();
			
			$.post(
				"loginById",
				{id,pw},
				function(data){
					data=JSON.parse(data);
					console.log(data);
					if(data.id){//ok data={"id":"a"}
						alert("Data: "+data.id+"님 login ok\nStatus: success");
						$.cookie("id",data.id,{path:'/'});
						document.getElementById("loginSpan").innerHTML=id+"님 환영합니다 <button id='logoutBtn'>로그아웃</button> <button id='memberDeleteBtn'>회원탈퇴</button>";
					}else{//fail
						alert(data.msg);
					}//end else						
					    
				}//end function
			
			);//end post			
			
		});//end click
		
	});//end ready
	
	
	
	
	
	
	
	