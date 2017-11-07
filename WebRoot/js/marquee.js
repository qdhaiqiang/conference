// JavaScript Document
var Marquee = function(id){
  function $(id) {
      return document.getElementById(id);
  }
  var m = this, div = $(id), inner, width, height, style, direct;
  inner = div.innerHTML; direct = 1;
  this.width = 400; this.height = 300;
  function resetCSS(){
      if(div.style.width == null || div.style.width == ""){
    	  div.style.width = m.width + "px";
      } else {
    	  m.width = parseInt(div.style.width);
      }
      if(div.style.height == null || div.style.height == ""){
    	  div.style.height = m.height + "px";
      } else {
    	  m.height = parseInt(div.style.height);
      }
      style = "width:" + m.width + "px; height:" + m.height + "px; overflow:hidden; float:left;";
  }
  
  this.interval = 100; this.step = 1; this.timer = null;
  this.stop = function() { 
	  window.clearTimeout(m.timer); 
	  m.timer = null;
  }
  this.MoveLeft=function() {
	  createScrollX();
	  m.stop();
	  m.timer=window.setTimeout(marquee_left,m.interval);
  }
  this.MoveRight=function() {
	  createScrollX();m.stop();
	  m.timer=window.setTimeout(marquee_right,m.interval);
  }
  this.MoveUp=function() {
	  createScrollY();
	  m.stop();
	  m.timer=window.setTimeout(marquee_up,m.interval);
  }
  this.MoveDown=function() {
	  createScrollY();
	  m.stop();
	  m.timer=window.setTimeout(marquee_down,m.interval);
  }
  this.Continue = function() {
	  switch(direct) {
	  	case 1:
	  		m.MoveLeft();
	  		break;
	  	case 2:
	  		m.MoveRight();
	  		break;
	  	case 3:
	  		m.MoveUp();
	  		break;
	  	case 4:
	  		m.MoveDown();
	  		break;
	  	default:
	  		m.MoveLeft();
	  		break;
	  	}
  }
  div.onmouseover = m.stop; div.onmouseout = m.Continue;
  function createScrollX(){
      resetCSS(); 
      div.style.overflowX="hidden"; 
      div.style.overflowY="auto"; 
      div.innerHTML="";
      var odiv=document.createElement("DIV"); 
      odiv.style.width=(m.width * 2) + "px";
      div.appendChild(odiv);
      createSubDiv(odiv);
  }
  function createScrollY(){
	  resetCSS(); 
	  div.style.overflowX="auto"; 
	  div.style.overflowY="hidden"; 
	  div.innerHTML="";
	  var odiv=document.createElement("DIV"); 
	  odiv.style.height=(m.height * 2) + "px";
	  div.appendChild(odiv);
	  createSubDiv(odiv);
  }
  function createSubDiv(parent){
	  var ldiv = document.createElement("DIV"); 
	  var rdiv = document.createElement("DIV");
	  ldiv.style.cssText = rdiv.style.cssText = style; 
	  ldiv.style.float = rdiv.style.float = "left";
	  ldiv.innerHTML = rdiv.innerHTML = inner;
	  parent.appendChild(ldiv); parent.appendChild(rdiv);
  }
  function marquee_left(){
	  if(div.scrollLeft - m.width >= 0) {
		  div.scrollLeft -= m.width;
	  } else { 
		  div.scrollLeft += m.step;
	  }
     direct=1; 
     m.timer = window.setTimeout(marquee_left, m.interval);
  }
  function marquee_right(){
	  if(div.scrollLeft <= 0) { 
		  div.scrollLeft += m.width;
	  } else { 
		  div.scrollLeft -= m.step;
	  }
      direct=2; 
      m.timer = window.setTimeout(marquee_right, m.interval);
  }
  function marquee_up(){
	  if(div.scrollTop - m.height >= 0) { 
		  div.scrollTop -= m.height;
	  } else { 
		  div.scrollTop += m.step;
	  }
      direct=3; 
      m.timer = window.setTimeout(marquee_up, m.interval);
  }
  function marquee_down(){
      if(div.scrollTop <= 0) { 
    	  div.scrollTop += m.height;
      } else {
    	  div.scrollTop -= m.step;
      }
      direct=4; 
      m.timer = window.setTimeout(marquee_down, m.interval);
  }
}