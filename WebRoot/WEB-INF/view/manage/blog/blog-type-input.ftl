<#include "/WEB-INF/view/inc/inc.ftl"/>

<form class="form-horizontal" role="form">
	<input type="hidden" name="row.id" value="${(row.id)!}"/>
	<@bsinput title='类型名称' name='row.blog_type_name' value='${(row.blog_type_name)!}'/>
</form>