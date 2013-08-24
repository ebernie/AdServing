<#--

    Mad-Advertisement
    Copyright (C) 2011-2013 Thorsten Marx <thmarx@gmx.net>

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
    the License. You may obtain a copy of the License at

    	http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
    specific language governing permissions and limitations under the License.

-->
<#--  

Extern-Banner

-->
var c_ad_date = new Date().getTime();
var c_ad_node = "c_ad_" + c_ad_date;
var style = "width:${banner.format.width}px; height:${banner.format.height}px; position:relative;";
document.write("<div id='" + c_ad_node + "' style='" + style + "'>");
document.write("${banner.externContent}");
document.write("</div>");