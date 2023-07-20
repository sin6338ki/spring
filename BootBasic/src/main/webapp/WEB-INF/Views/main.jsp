<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<button onClick="loadList()">멤버 리스트 가져오기</button>
	<div id="list">
	</div>
	<script>
	
		function loadList(){
			$.ajax({
				url : "member", //localhost:8089/myapp/member
				type : "get", //요청방식
				dataType : "json", //응답 받는 데이터의 형식 (view(html, jsp)를 받는게 아니라 데이터(모델)만 반환)
				success : function(res){ //요청-응답 성공했을 때
					console.log(res)
					let t = "<table border=1>"
					for(let i=0; i<res.length ; i++){
						t += "<tr>"
						t += "<td>" + res[i].id + "</td>"
						t += "<td>" + res[i].pw + "</td>"
						t += "<td>" + res[i].nick + "</td>"
						t += "</tr>"
					}
					t += "</table>"
					$("#list").append(t)
				},
				error : function(){
					console.log("통신실패!")
				}
			})
		}
	
	</script>
</body>
</html>