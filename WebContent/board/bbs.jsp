<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
    
<%@ include file = "../include/header.jsp"%>    
   
    <section>
        
        <div class="container">
            <div class="row">
                
                <h2>게시판 목록</h2>
                <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="background-color: #9DCAFF; text-align: center;">번호</th>
                            <th style="background-color: #9DCAFF; text-align: center;">제목</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성자</th>
                            <th style="background-color: #9DCAFF; text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    
                    <c:forEach var = "vo" items = "${list }">
                    <tbody>
                        <tr>
                            <td>${vo.bno }</td>
                            <td><a>${vo.title }</a></td>
                            <td>${vo.writer }</td>
                            <td><fmt:formatDate value = "${vo.regdate }" pattern = "yyyy년MM월dd일"/></td>
                        </tr>
                        
                    </tbody>
                    </c:forEach>
                </table>
                <div class="text-center">
                    <ul class="pagination pagination-sm">
                        <li><a href="#">이전</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">다음</a></li>
                    </ul>
                    <button class="btn btn-info pull-right" onClick = "location.href = 'list.board'">글쓰기</button>
                </div>
                
            </div>
        </div>
    </section>
        
<%@ include file="../include/footer.jsp" %>
