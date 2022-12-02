<#list maps as m>
    ${m.id1}/${m.id2}
</#list>


<#list maps as m>
    <#list m?keys as k>
        ${m[k]}
    </#list>
</#list>