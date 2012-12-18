/*
 * Mad-Advertisement
 * Copyright (C) 2011 Thorsten Marx <thmarx@gmx.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
"undefined"==typeof madApi&&(madApi={onload:function(b){var a=window.onload;window.onload="function"!=typeof window.onload?b:function(){a();b()}},delegate:function(b,a,c){var e=c||arguments,g=function(){return b.apply(arguments.callee.target,e)};g.target=a;g.func=this;return g},position:function(b){var a=0,c=0;if(b.offsetParent){do a+=b.offsetLeft,c+=b.offsetTop;while(b=b.offsetParent)}return{left:a,top:c}},size:function(b){return{height:b.offsetHeight,width:b.offsetWidth}},isIE:function(){return 0<
window.navigator.userAgent.indexOf("MSIE ")?!0:!1},isBrowser:function(b,a){versionOk=browserOk=!1;browserOk=-1!=navigator.appName.indexOf(b);versionOk=0==a?!0:a<=parseInt(navigator.appVersion);return browserOk&&versionOk},getWindowSize:function(){var b=0,a=0;if("number"==typeof window.innerWidth)b=window.innerWidth,a=window.innerHeight;else if(document.documentElement&&(document.documentElement.clientWidth||document.documentElement.clientHeight))b=document.documentElement.clientWidth,a=document.documentElement.clientHeight;
else if(document.body&&(document.body.clientWidth||document.body.clientHeight))b=document.body.clientWidth,a=document.body.clientHeight;return{width:b,height:a}},getScrollXY:function(){var b=0,a=0;if("number"==typeof window.pageYOffset)a=window.pageYOffset,b=window.pageXOffset;else if(document.body&&(document.body.scrollLeft||document.body.scrollTop))a=document.body.scrollTop,b=document.body.scrollLeft;else if(document.documentElement&&(document.documentElement.scrollLeft||document.documentElement.scrollTop))a=
document.documentElement.scrollTop,b=document.documentElement.scrollLeft;return{x:b,y:a}},addEvent:function(b,a,c,e){b.attachEvent?b.attachEvent("on"+a,c):(e||(e=!1),b.addEventListener(a,c,e))},flash:function(b,a){var c=0;if("object"==typeof navigator.plugins["Shockwave Flash"])c=navigator.plugins["Shockwave Flash"].description,c=c.substr(16,c.indexOf(".",16)-16);else if("function"==typeof ActiveXObject)for(var e=2;e<a+1;e++)try{"object"==typeof new ActiveXObject("ShockwaveFlash.ShockwaveFlash."+
e)&&(c=e)}catch(g){}return{available:c,required:b}},fx:function(b){var a=null;if(b.nodeType&&1==b.nodeType)a=b;else if((""+b).match(/^#([^$]+)$/i)){if(a=document.getElementById(RegExp.$1+""),!a)return null}else return null;if("undefined"!=typeof a._fx&&a._fx)return a._fx._addSet(),a;a.fxVersion=0.1;a._fx={};a._fx.sets=[];a._fx._currSet=0;if("undefined"!=typeof a._fxTerminated)try{delete a._fxTerminated}catch(c){a._fxTerminated=null}var e={"left|top|right|bottom|width|height|margin|padding|spacing|backgroundx|backgroundy":"px",
font:"pt",opacity:""},g=!!navigator.userAgent.match(/MSIE/ig),f={delay:100,step:5,unit:""},h={opacity:function(d){d=parseInt(d);if(isNaN(d))return g?(d=/alpha\s*\(opacity\s*=\s*(\d+)\)/.exec(a.style.filter+""))?parseInt(d[1]):1:Math.round(100*(a.style.opacity?parseFloat(a.style.opacity):1));d=Math.min(100,Math.max(0,d));g?(a.style.zoom=1,a.style.filter="alpha(opacity="+d+");"):a.style.opacity=d/100},backgroundx:function(d,b){var d=parseInt(d),i=0,c=0,e=/^(-?\d+)[^\d\-]+(-?\d+)/.exec(a.style.backgroundPosition+
"");e&&(i=parseInt(e[1]),c=parseInt(e[2]));if(isNaN(d))return i;a.style.backgroundPosition=d+b+" "+c+b},backgroundy:function(d,b){var d=parseInt(d),c=0,e=0,f=/^(-?\d+)[^\d\-]+(-?\d+)/.exec(a.style.backgroundPosition+"");f&&(c=parseInt(f[1]),e=parseInt(f[2]));if(isNaN(d))return e;a.style.backgroundPosition=c+b+" "+d+b}},j={width:function(){return parseInt(a.offsetWidth)},height:function(){return parseInt(a.offsetHeight)},left:function(){for(var d=0,b=a;b;b=b.offsetParent)d+=parseInt(b.offsetLeft);
return d},top:function(){for(var d=0,b=a;b;b=b.offsetParent)d+=parseInt(b.offsetTop);return d}};a.fxAddSet=function(){this._fx._addSet();return this};a.fxHold=function(d,b){if(a._fx.sets[this._fx._currSet]._isrunning)return this;b=parseInt(b);this._fx.sets[isNaN(b)?this._fx._currSet:b]._holdTime=d;return this};a.fxAdd=function(d){var a=this._fx._currSet;if(this._fx.sets[a]._isrunning)return this;for(var b in f)d[b]||(d[b]=f[b]);if(!d.unit)for(var c in e)if(RegExp(c,"i").test(d.type)){d.unit=e[c];
break}d.onstart=d.onstart&&d.onstart.call?d.onstart:function(){};d.onfinish=d.onfinish&&d.onfinish.call?d.onfinish:function(){};if(!this._fx[d.type])if(h[d.type])this._fx[d.type]=h[d.type];else{var g=this;this._fx[d.type]=function(a,b){if("undefined"==typeof a)return parseInt(g.style[d.type]);g.style[d.type]=parseInt(a)+b}}if(isNaN(d.from))d.from=isNaN(this._fx[d.type]())?j[d.type]?j[d.type]():0:this._fx[d.type]();d._initial=d.from;this._fx[d.type](d.from,d.unit);this._fx.sets[a]._queue.push(d);return this};
a.fxRun=function(d,b,c){var e=a._fx._currSet;if(this._fx.sets[e]._isrunning)return this;setTimeout(function(){if(a._fx.sets[e]._isrunning)return a;a._fx.sets[e]._isrunning=!0;if(0<a._fx.sets[e]._effectsDone)return a;a._fx.sets[e]._onfinal=d&&d.call?d:function(){};a._fx.sets[e]._onloop=c&&c.call?c:function(){};if(!isNaN(b))a._fx.sets[e]._loops=b;for(var f=0;f<a._fx.sets[e]._queue.length;f++)a._fx.sets[e]._queue[f].onstart.call(a),a._fx._process(e,f)},a._fx.sets[e]._holdTime);return this};a.fxPause=
function(a,b){this._fx.sets[!isNaN(b)?b:this._fx._currSet]._paused=a;return this};a.fxStop=function(a){this._fx.sets[!isNaN(a)?a:this._fx._currSet]._stoped=!0;return this};a.fxReset=function(){for(var a=0;a<this._fx.sets.length;a++)for(var b=0;b<this._fx.sets[a]._queue.length;b++){var e=this._fx.sets[a]._queue[b];if(isNaN(e._initial))this._fx[e.type]("","");else this._fx[e.type](e._initial,e.unit)}b="_fx,fxHold,fxAdd,fxAddSet,fxRun,fxPause,fxStop,fxReset".split(",");for(a=0;a<b.length;a++)try{delete this[b[a]]}catch(c){this[b[a]]=
null}this._fxTerminated=!0};a._fx._addSet=function(){var a=this.sets.length;this._currSet=a;this.sets[a]={};this.sets[a]._loops=1;this.sets[a]._stoped=!1;this.sets[a]._queue=[];this.sets[a]._effectsDone=0;this.sets[a]._loopsDone=0;this.sets[a]._holdTime=0;this.sets[a]._paused=!1;this.sets[a]._isrunning=!1;this.sets[a]._onfinal=function(){};return this};a._fx._process=function(b,e){if(this.sets[b]&&!this.sets[b]._stoped&&!a._fxTerminated){var c=this.sets[b]._queue[e],f=this[c.type]();if(0<c.step&&
f+c.step<=c.to||0>c.step&&f+c.step>=c.to){if(!this.sets[b]._paused)this[c.type](f+c.step,c.unit);var g=this;setTimeout(function(){g._process&&g._process(b,e)},c.delay)}else if(this[c.type](c.to,c.unit),this.sets[b]._effectsDone++,c.onfinish.call(a),this.sets[b]._queue.length==this.sets[b]._effectsDone)if(this.sets[b]._effectsDone=0,this.sets[b]._loopsDone++,this.sets[b]._onloop.call(a,this.sets[b]._loopsDone),this.sets[b]._loopsDone<this.sets[b]._loops||-1==this.sets[b]._loops)for(f=0;f<this.sets[b]._queue.length;f++)this[c.type](c.from,
this.sets[b]._queue[f].unit),this.sets[b]._queue[f].onstart.call(a,this.sets[b]._loopsDone),this._process(b,f);else this.sets[b]._onfinal.call(a)}};a._fx._addSet();return a},ajax:{getHTTPObject:function(){var b=!1;if("undefined"!=typeof ActiveXObject)try{b=new ActiveXObject("Msxml2.XMLHTTP")}catch(a){try{b=new ActiveXObject("Microsoft.XMLHTTP")}catch(c){b=!1}}else if(window.XMLHttpRequest)try{b=new XMLHttpRequest}catch(e){b=!1}return b},load:function(b,a,c,e,g){var f=this.init();if(f&&b){f.overrideMimeType&&
f.overrideMimeType("text/xml");e||(e="GET");c||(c="text");g||(g={});var c=c.toLowerCase(),e=e.toUpperCase(),h="uid="+(new Date).getTime(),b=b+(b.indexOf("?")+1?"&":"?"),b=b+h,h=null;"POST"==e&&(h=b.split("?"),b=h[0],h=h[1]);f.open(e,b,!0);"POST"==e&&(f.setRequestHeader("Content-type","application/x-www-form-urlencoded"),f.setRequestHeader("Content-length",h.length),f.setRequestHeader("Connection","close"));f.onreadystatechange=g.handler?function(){g.handler(f)}:function(){if(4==f.readyState)if(200==
f.status){var b="";if(f.responseText)b=f.responseText;if("j"==c.charAt(0))b=b.replace(/[\n\r]/g,""),b=eval("("+b+")");else if("x"==c.charAt(0))b=f.responseXML;a&&a(b)}else{g.loadingIndicator&&document.getElementsByTagName("body")[0].removeChild(g.loadingIndicator);if(g.loading)document.getElementById(g.loading).style.display="none";error&&error(f.status)}};f.send(h)}},bind:function(b){var a={url:"",onSuccess:!1,onError:!1,format:"text",method:"GET",update:"",loading:"",loadingIndicator:""},c;for(c in a)b[c]&&
(a[c]=b[c]);if(a.url){var e=!1;if(a.loadingIndicator)e=document.createElement("div"),e.setAttribute("style","position:absolute;top:0px;left:0px;"),e.setAttribute("class","loading-indicator"),e.innerHTML=a.loadingIndicator,document.getElementsByTagName("body")[0].appendChild(e),this.opt.loadingIndicator=e;if(a.loading)document.getElementById(a.loading).style.display="block";this.load(a.url,function(b){if(a.onSuccess)a.onSuccess(b);if(a.update)document.getElementById(a.update).innerHTML=b;e&&document.getElementsByTagName("body")[0].removeChild(e);
if(a.loading)document.getElementById(a.loading).style.display="none"},a.format,a.method,a)}},init:function(){return this.getHTTPObject()}}});
function mad_expandableImage_open(b,a){if(0==document.getElementById(a).style.opacity){var c=madApi.position(document.getElementById(b)),e=madApi.size(document.getElementById(b)),g=madApi.getWindowSize(),f,h;f=c.top;h=c.left+e.width;if(300>g.width-(c.left+e.width))f=c.top,h=c.left-300;document.getElementById(a).style.left=h+"px";document.getElementById(a).style.top=f+"px";madApi.isIE(0)?document.getElementById(a).style.opacity=1:madApi.fx(document.getElementById(a)).fxAdd({type:"opacity",from:0,to:100,
step:10,delay:10}).fxRun(null,1)}}function mad_expandableImage_close(b){if(0!=document.getElementById(b).style.opacity)madApi.isIE(0)?document.getElementById(b).style.opacity=0:madApi.fx(document.getElementById(b)).fxAdd({type:"opacity",from:100,to:0,step:10,delay:10}).fxRun(null,1)};