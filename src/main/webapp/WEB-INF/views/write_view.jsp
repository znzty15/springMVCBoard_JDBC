<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write_view.jsp</title>
</head>
<body>
	<h3>자유게시판 글쓰기</h3>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="write">
			<tr>
				<td>이 름</td>
				<td><input type="text" name=bName size="65"></td>
			</tr>
			<tr>
				<td>제 목</td>
				<td><input type="text" name=bTitle size="65"></td>
			</tr>				
			<tr>
				<td align="center" colspan="2">내 용</td>
			</tr>
				<td align="center" colspan="2"><textarea name="bContent" rows="10" cols="57"></textarea></td>
			<tr>			
				<td colspan="2"><a href="list">글 목록 보기</a></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="입력"> &nbsp;&nbsp; <input type="reset" value="취소"></td>
			</tr>
		</form>
	</table>
</body>
</html>