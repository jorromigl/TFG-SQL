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


<display:table name="classifications" id="row" requestURI="${requestURI}" pagesize="20" class="table table-hover">


	<spring:message code="classification.info" var="info" />
	<display:column property="info" title="${info}" sortable="${true}" />
	
	<spring:message code="classification.point" var="point" />
	<display:column property="point" title="${point}" />
	
	<spring:message code="classification.played" var="played" />
	<display:column property="played" title="${played}"  />
	
	<spring:message code="classification.won" var="won" />
	<display:column property="won" title="${won}"  />
	
	<spring:message code="classification.lost" var="lost" />
	<display:column property="lost" title="${lost}"  />
	
	<spring:message code="classification.tied" var="tied" />
	<display:column property="tied" title="${tied}"  />
			
<!-- <div class="col-md-12 col-sm-12 col-xs-12"> -->
               
               
<!--                   <div class="x_content"> -->
<!--                     <div class="table-responsive"> -->
<!--                       <table class="table table-striped jambo_table bulk_action"> -->
<!--                         <thead> -->
<!--                           <tr class="headings"> -->
<!--                             <th> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" id="check-all" class="flat" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </th> -->
<!--                             <th class="column-title">Invoice </th> -->
<!--                             <th class="column-title">Invoice Date </th> -->
<!--                             <th class="column-title">Order </th> -->
<!--                             <th class="column-title">Bill to Name </th> -->
<!--                             <th class="column-title">Status </th> -->
<!--                             <th class="column-title">Amount </th> -->
<!--                             <th class="column-title no-link last"><span class="nobr">Action</span> -->
<!--                             </th> -->
<!--                             <th class="bulk-actions" colspan="7"> -->
<!--                               <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a> -->
<!--                             </th> -->
<!--                           </tr> -->
<!--                         </thead> -->

<!--                         <tbody> -->
<!--                           <tr class="even pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000040</td> -->
<!--                             <td class=" ">May 23, 2014 11:47:56 PM </td> -->
<!--                             <td class=" ">121000210 <i class="success fa fa-long-arrow-up"></i></td> -->
<!--                             <td class=" ">John Blank L</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$7.45</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                           <tr class="odd pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000039</td> -->
<!--                             <td class=" ">May 23, 2014 11:30:12 PM</td> -->
<!--                             <td class=" ">121000208 <i class="success fa fa-long-arrow-up"></i> -->
<!--                             </td> -->
<!--                             <td class=" ">John Blank L</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$741.20</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                           <tr class="even pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000038</td> -->
<!--                             <td class=" ">May 24, 2014 10:55:33 PM</td> -->
<!--                             <td class=" ">121000203 <i class="success fa fa-long-arrow-up"></i> -->
<!--                             </td> -->
<!--                             <td class=" ">Mike Smith</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$432.26</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                           <tr class="odd pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000037</td> -->
<!--                             <td class=" ">May 24, 2014 10:52:44 PM</td> -->
<!--                             <td class=" ">121000204</td> -->
<!--                             <td class=" ">Mike Smith</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$333.21</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                           <tr class="even pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000040</td> -->
<!--                             <td class=" ">May 24, 2014 11:47:56 PM </td> -->
<!--                             <td class=" ">121000210</td> -->
<!--                             <td class=" ">John Blank L</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$7.45</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                           <tr class="odd pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000039</td> -->
<!--                             <td class=" ">May 26, 2014 11:30:12 PM</td> -->
<!--                             <td class=" ">121000208 <i class="error fa fa-long-arrow-down"></i> -->
<!--                             </td> -->
<!--                             <td class=" ">John Blank L</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$741.20</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                           <tr class="even pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000038</td> -->
<!--                             <td class=" ">May 26, 2014 10:55:33 PM</td> -->
<!--                             <td class=" ">121000203</td> -->
<!--                             <td class=" ">Mike Smith</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$432.26</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                           <tr class="odd pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000037</td> -->
<!--                             <td class=" ">May 26, 2014 10:52:44 PM</td> -->
<!--                             <td class=" ">121000204</td> -->
<!--                             <td class=" ">Mike Smith</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$333.21</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->

<!--                           <tr class="even pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000040</td> -->
<!--                             <td class=" ">May 27, 2014 11:47:56 PM </td> -->
<!--                             <td class=" ">121000210</td> -->
<!--                             <td class=" ">John Blank L</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$7.45</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                           <tr class="odd pointer"> -->
<!--                             <td class="a-center "> -->
<!--                               <div class="icheckbox_flat-green" style="position: relative;"><input type="checkbox" class="flat" name="table_records" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> -->
<!--                             </td> -->
<!--                             <td class=" ">121000039</td> -->
<!--                             <td class=" ">May 28, 2014 11:30:12 PM</td> -->
<!--                             <td class=" ">121000208</td> -->
<!--                             <td class=" ">John Blank L</td> -->
<!--                             <td class=" ">Paid</td> -->
<!--                             <td class="a-right a-right ">$741.20</td> -->
<!--                             <td class=" last"><a href="#">View</a> -->
<!--                             </td> -->
<!--                           </tr> -->
<!--                         </tbody> -->
<!--                       </table> -->
<!--                     </div> -->
<!--                   </div> -->
<!--                 </div> -->
              
</display:table>