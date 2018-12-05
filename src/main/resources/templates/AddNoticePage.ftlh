<#import "/spring.ftl" as spring>
<#import "./common.ftlh" as c>

<#macro error field>
    <@spring.bind field/>
    <#list spring.status.errorMessages as error>
        <div class="error">${error}</div>
        <#break/>
    </#list>
</#macro>

<@c.page>

<div class="notice form-box">
    <div class="header">Add Notice</div>
    <div class="body">
        <form method="post" action="/notice">
            <input type="hidden" name="action" value="notice">
            <textarea name="content" value="${noticeForm.content!}" placeholder="Type something.."></textarea>
            <div class="button-field">
                <input type="submit" value="Add">
            </div>
            <@error 'noticeForm.content'/>
        </form>
    </div>
</div>
</@c.page>