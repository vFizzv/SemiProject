$(document).ready(function(){

	let id=$.cookie('id');
	if(id){
		document.getElementById("loginSpan").innerHTML=id+"님 환영합니다 <button id='logoutBtn'>로그아웃</button> <button id='memberDeleteBtn'>회원탈퇴</button>";
	}
	
	//탈퇴
	$(document).on('click','#memberDeleteBtn',function(){
		const r=confirm("정말 탈퇴하실 건가요?");
		  if (r == true) {
		    $.post('deleteMember',
		    	{},
		    	function(data){
		    		alert(data);
		    		$.removeCookie('id',{path:'/'});
		    		location.reload();
		    	}
		    );
		  }
	});
	
	//로그아웃
	$(document).on('click','#logoutBtn',function(){
		    $.post('logOut',
		    	{},
		    	function(data){	
		    		alert(data);
		    		$.removeCookie('id',{path:'/'}); 	
		    		//$.removeCookie('JSESSIONID',{path:'/'});	    		  		
		    		location.reload();
		    	}
		    );
		
	});
});




