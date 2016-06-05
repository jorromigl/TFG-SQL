<?xml version="1.0" encoding="UTF-8" ?>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>



<form:form modelAttribute="messageDisplay">

	<div class="col-md-12">
              <div class="x_panel">
                
		          <!-- CONTENT MAIL -->
                    <div class="col-sm-9 mail_view">
                      <div class="inbox-body">
                        <div class="mail_heading row">
                          <div class="col-md-8">
                   
                          </div>
                          <div class="col-md-4 text-right">
                            <p class="date"> <jstl:out value="${messageDisplay.moment}"></jstl:out></p>
                          </div>
                          <div class="col-md-12">
                            <h4><jstl:out value="${messageDisplay.subject}"></jstl:out></h4>
                          </div>
                        </div>
                        <div class="sender-info">
                          <div class="row">
                            <div class="col-md-12">
                              <strong><jstl:out value="${messageDisplay.sender.name}"></jstl:out></strong>
                              <strong><jstl:out value="${messageDisplay.sender.surname}"></jstl:out></strong>
                              <span>(<jstl:out value="${messageDisplay.sender.email}"></jstl:out>)</span> to
                              <strong><jstl:out value="${messageDisplay.recipient.name}"></jstl:out></strong>
                              <a class="sender-dropdown"><i class="fa fa-chevron-down"></i></a>
                            </div>
                          </div>
                        </div>
                        <div class="view-mail">
                          <p> <jstl:out value="${messageDisplay.body}"></jstl:out></p>
                          
                        </div>
                  
                    </div>
                    <!-- /CONTENT MAIL -->
                  </div>
                </div>
              </div>
            
</form:form>
