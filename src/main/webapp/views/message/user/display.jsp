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

	<div class="content">
		<div class="col-md-12">
			<div class="x_panel">

<%-- 				<tag:textbox code="msg.moment" path="moment" readonly="true" /> --%>
				
				
				<h4> <spring:message code="msg.moment"/>  <jstl:out value="${messageDisplay.moment}"></jstl:out> </h4>
						
						<br>

				<tag:textbox code="msg.sender" path="sender.name" readonly="true" />

				<tag:textbox code="msg.recipient" path="recipient.name"
					readonly="true" />

				<tag:textbox code="msg.subject" path="subject" readonly="true" />

				<tag:textarea code="msg.body" path="body" readonly="true" />

				<security:authentication var="user" property="principal.id" />

				<jstl:if test="${messageDisplay.recipient.userAccount.id==user}">
					<input type="button"
						value="<spring:message code="msg.reply.link" />"
						onclick="javascript: location.replace('message/user/reply.do?messageId=${messageDisplay.id}')" />
				</jstl:if>





				<tag:cancel code="message.return"
					url='/message/user/list.do?folderId=${messageDisplay.folder.id}' />


				<jstl:if
					test="${messageDisplay.recipient.userAccount.id==user || messageDisplay.sender.userAccount.id==user}">

					<input type="button" value="<spring:message code="msg.delete"  />"
						onclick="if(confirm('<spring:message code="msg.delete.del" />'))
				javascript: location.replace('message/user/delete.do?messageId=${messageDisplay.id}');
				return false" />

				</jstl:if>

			</div>
		</div>

	</div>
	
	<div class="col-md-12">
              <div class="x_panel">
                <div class="x_title">
                  <h2> Inbox Design<small>User Mail</small></h2>
                  <ul class="nav navbar-right panel_toolbox">
                    <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    </li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Settings 1</a>
                        </li>
                        <li><a href="#">Settings 2</a>
                        </li>
                      </ul>
                    </li>
                    <li><a class="close-link"><i class="fa fa-close"></i></a>
                    </li>
                  </ul>
                  <div class="clearfix"></div>
                </div>
               

                    <!-- /MAIL LIST -->


                    <!-- CONTENT MAIL -->
                    <div class="col-sm-9 mail_view">
                      <div class="inbox-body">
                        <div class="mail_heading row">
                          <div class="col-md-8">
                            <div class="compose-btn">
                              <a class="btn btn-sm btn-primary" href="mail_compose.html"><i class="fa fa-reply"></i> Reply</a>
                              <button title="" data-placement="top" data-toggle="tooltip" type="button" data-original-title="Print" class="btn  btn-sm tooltips"><i class="fa fa-print"></i> </button>
                              <button title="" data-placement="top" data-toggle="tooltip" data-original-title="Trash" class="btn btn-sm tooltips"><i class="fa fa-trash-o"></i>
                              </button>
                            </div>

                          </div>
                          <div class="col-md-4 text-right">
                            <p class="date"> <jstl:out value="${messageDisplay.moment}"></jstl:out></p>
                          </div>
                          <div class="col-md-12">
                            <h4><jstl:out value="${messageDisplay.sender.subject}"></jstl:out></h4>
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
                          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. </p>
                          <p>Riusmod tempor incididunt ut labor erem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
                            nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                            mollit anim id est laborum.</p>
                          <p>Modesed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                            velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                        </div>
                        <div class="attachment">
                          <p>
                            <span><i class="fa fa-paperclip"></i> 3 attachments — </span>
                            <a href="#">Download all attachments</a> |
                            <a href="#">View all images</a>
                          </p>
                          <ul>
                            <li>
                              <a href="#" class="atch-thumb">
                                <img src="images/1.png" alt="img">
                              </a>

                              <div class="file-name">
                                image-name.jpg
                              </div>
                              <span>12KB</span>


                              <div class="links">
                                <a href="#">View</a> -
                                <a href="#">Download</a>
                              </div>
                            </li>

                            <li>
                              <a href="#" class="atch-thumb">
                                <img src="images/1.png" alt="img">
                              </a>

                              <div class="file-name">
                                img_name.jpg
                              </div>
                              <span>40KB</span>

                              <div class="links">
                                <a href="#">View</a> -
                                <a href="#">Download</a>
                              </div>
                            </li>
                            <li>
                              <a href="#" class="atch-thumb">
                                <img src="images/1.png" alt="img">
                              </a>

                              <div class="file-name">
                                img_name.jpg
                              </div>
                              <span>30KB</span>

                              <div class="links">
                                <a href="#">View</a> -
                                <a href="#">Download</a>
                              </div>
                            </li>

                          </ul>
                        </div>
                        <div class="compose-btn pull-left">
                          <a class="btn btn-sm btn-primary" href="mail_compose.html"><i class="fa fa-reply"></i> Reply</a>
                          <button class="btn btn-sm "><i class="fa fa-arrow-right"></i> Forward</button>
                          <button title="" data-placement="top" data-toggle="tooltip" type="button" data-original-title="Print" class="btn  btn-sm tooltips"><i class="fa fa-print"></i> </button>
                          <button title="" data-placement="top" data-toggle="tooltip" data-original-title="Trash" class="btn btn-sm tooltips"><i class="fa fa-trash-o"></i>
                          </button>
                        </div>
                      </div>

                    </div>
                    <!-- /CONTENT MAIL -->
                  </div>
                </div>
              </div>
            </div>
</form:form>
