<#import "./common.ftlh" as c>

<@c.page>


 <#if user??>
<div class="datatable">
    <div class="caption">Users</div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Created At</th>
        </tr>
        </thead>
        <tbody>

    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.creationTime}</td>
    </tr>

        </tbody>
    </table>
</div>


<#else >
<p>No such User</p>

</#if>

</@c.page>