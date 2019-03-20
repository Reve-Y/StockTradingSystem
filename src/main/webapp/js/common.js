
var script1 = document.createElement("script");
var script2 = document.createElement("script");
var script3 = document.createElement("script");
var script4 = document.createElement("script");
var script5 = document.createElement("script");
var script6 = document.createElement("script");
var script7 = document.createElement("script");
var script8 = document.createElement("script");

script1.language = "javascript";
script2.language = "javascript";
script3.language = "javascript";
script4.language = "javascript";
script5.language = "javascript";
script6.language = "javascript";
script7.language = "javascript";
script8.language = "javascript";

script1.src = "../../assets/js/core/vue.js";
script2.src = "../../assets/js/core/jquery.min.js";
script3.src = "../../assets/js/core/popper.min.js";
script4.src = "../../assets/js/core/bootstrap.min.js";
script5.src = "../../assets/js/plugins/bootstrap-switch.js";
script6.src = "../../assets/js/plugins/nouislider.min.js";
script7.src = "../../assets/js/plugins/bootstrap-datepicker.js";
script8.src = "../../assets/js/now-ui-kit.js?v=1.2.0";

//将js文件引入到head中
document.getElementsByTagName("head")[0].appendChild(script1);
document.getElementsByTagName("head")[0].appendChild(script2);
document.getElementsByTagName("head")[0].appendChild(script3);
document.getElementsByTagName("head")[0].appendChild(script4);
document.getElementsByTagName("head")[0].appendChild(script5);
document.getElementsByTagName("head")[0].appendChild(script6);
document.getElementsByTagName("head")[0].appendChild(script7);
document.getElementsByTagName("head")[0].appendChild(script8);
